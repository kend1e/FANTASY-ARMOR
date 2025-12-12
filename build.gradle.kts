import groovy.json.JsonSlurper
import kotlin.text.replace

plugins {
    id("idea")
    alias(libs.plugins.forgeGradle) apply false
    alias(libs.plugins.parchmentLibrarian) apply false
    alias(libs.plugins.spongePoweredMixin) apply false
}

tasks.register("generateRecipesData") {
    doLast {
        val armorRecipes = getArmorRecipesMap() ?: return@doLast;

        val templateVersions = getTemplateVersions();
        val projectVersions = getProjectVersions();
        val toGenVersions = templateVersions.intersect(projectVersions)

        toGenVersions.forEach { genRecipesByVersion(it, armorRecipes) }
    }
}

val recipesInfoDir = "recipesGenInfo"
val recipesFile = "$recipesInfoDir${File.separator}armor_recipes.json"
val mcVersionsInfoFile = "$recipesInfoDir${File.separator}mc_versions_info.json"
val templatesDir = "$recipesInfoDir${File.separator}templates"

val templateItemKey = "TEMPLATE_ITEM"
val baseItemKey = "BASE_ITEM"
val additionItemKey = "ADDITION_ITEM"
val resultItemKey = "RESULT_ITEM"
val armorParts = listOf("helmet", "chestplate", "leggings", "boots")
val baseItemPrefix = "minecraft:netherite"

val dataDirPath = "src${File.separator}main${File.separator}resources${File.separator}data${File.separator}${providers.gradleProperty("mod_id").get()}"

val modID: String = providers.gradleProperty("mod_id").get()

/**
 * Generates all available recipes from the armor info list and templates for the given Minecraft version.
 *
 * @param version Minecraft version (e.g. "1.20.1")
 * @param armorRecipes map of armor sets names and their recipes ingredients
 */
fun genRecipesByVersion(version: String, armorRecipes: Map<String, Map<String, String>>) {
    val template = getTemplateByVersion(version) ?: return

    val versionRecipesDirName = getRecipesDirNameByVersion(version) ?: return
    val recipesDirPath = "$dataDirPath${File.separator}$versionRecipesDirName"
    val versionDirs = getVersionDirsByVersion(version)
    versionDirs.forEach { projectDir ->
        val recipesDir = File("$projectDir${File.separator}$recipesDirPath")
        println("Generating recipes for [$version] in [$recipesDir]")
        if (!recipesDir.exists()) recipesDir.mkdirs()

        armorRecipes.forEach { (armor, recipeItems) ->
            genRecipeByArmorSet(template, armor, recipeItems, "$recipesDir") }
    }
}

/**
 * Generates a recipe for the given armor set using the given template.
 * @param template armor set recipe template
 * @param armorSetName armor set name
 * @param recipeItems map of ingredients for the recipe
 * @param recipesDir path to the recipes directory
 */
fun genRecipeByArmorSet(template: String, armorSetName: String, recipeItems: Map<String, String>, recipesDir: String) {
    armorParts.forEach { part ->
        val currentFileName = "${armorSetName}_$part"

        val filledTemplate = template.let {
            recipeItems.get("template")?.let {
                armorSetTemplate -> template.replace(templateItemKey, armorSetTemplate)
            }
            recipeItems.get("addition")?.let {
                armorSetAddition -> template.replace(additionItemKey, armorSetAddition)
            }
            template.replace(resultItemKey, "$modID:$currentFileName")
            template.replace(baseItemKey, "${baseItemPrefix}_$part")
        }

        File("$recipesDir${File.separator}$currentFileName.json").writeText(filledTemplate)
    }
}

/**
 * @return map of armor sets names and their recipes ingredients
 */
@Suppress("UNCHECKED_CAST")
fun getArmorRecipesMap(): Map<String, Map<String, String>>? {
    val recipesGenDataFile = File(recipesFile)
    if (!recipesGenDataFile.exists()) {
        println("Recipes file not found.")
        return null
    }

    return JsonSlurper().parse(recipesGenDataFile) as Map<String, Map<String, String>>
}

/**
 * @return set of available template versions (e.g. "1.20.1", "1.21.1"...)
 */
fun getTemplateVersions(): Set<String> {
    val versions: MutableSet<String> = mutableSetOf()
    for (file in File(templatesDir).listFiles()) {
        versions.add(file.nameWithoutExtension)
    }

    return versions
}

/**
 * @return set of Minecraft versions used in the project (e.g. "1.20.1", "1.21.1"...)
 */
fun getProjectVersions(): Set<String> {
    val versions: MutableSet<String> = mutableSetOf()
    subprojects.forEach { modLoader ->
        modLoader.subprojects.forEach { mcVersion ->
            mcVersion.path.split(":")[2].let {
                versions.add(it) }
        }
    }

    return versions
}

/**
 * Finds all subprojects directories for the given Minecraft version.
 *
 * @param version Minecraft version (e.g. "1.20.1")
 *
 * @return set of subprojects paths
 */
fun getVersionDirsByVersion(version: String): Set<String> {
    val mcVersionDirs: MutableSet<String> = mutableSetOf()
    subprojects.forEach { modLoader ->
        modLoader.subprojects.forEach { mcVersion ->
            if (mcVersion.projectDir.path.contains(version)) {
                mcVersionDirs.add(mcVersion.projectDir.path)
            }
    } }

    return mcVersionDirs
}

/**
 * Finds the template recipe file for the given Minecraft version.
 *
 * @param version Minecraft version (e.g. "1.20.1")
 *
 * @return path to the template file
 */
fun getTemplateByVersion(version: String): String? {
    val templateFile = File(templatesDir, "$version.json")

    return if (templateFile.exists()) templateFile.readText() else null
}

/**
 * Finds the name of the recipes directory for the given Minecraft version.
 *
 * @param version Minecraft version (e.g. "1.20.1")
 *
 * @return directory name (e.g. "recipes" or "recipe")
 */
@Suppress("UNCHECKED_CAST")
fun getRecipesDirNameByVersion(version: String): String? {
    val infoFile = File(mcVersionsInfoFile)
    if (!infoFile.exists()) {
        println("Minecraft versions info file not found.")
        return null
    }

    val infoJson = JsonSlurper().parse(infoFile) as Map<String, Map<String, String>>
    val recipesDirName = infoJson.get(version)?.get("recipes_dir") ?: return null
    return recipesDirName
}