import groovy.json.JsonSlurper
import kotlin.text.replace

plugins {
    id("idea")
}

// TODO : Cleanup

// TODO : SEPARATE SHARED AND PROJECTS
tasks.register("generateRecipesData") {
    doLast {
        val armorRecipes = getArmorRecipesMap() ?: return@doLast;

        val templateVersions = getRecipesTemplateVersions();
        val projectVersions = getProjectVersions();
        val toGenVersions = templateVersions.intersect(projectVersions)

        toGenVersions.forEach { genRecipesByVersion(it, armorRecipes) }
    }
}

tasks.register("generateTagsData") {
    doLast {
        val armorSets = getArmorSets() ?: return@doLast;

        var setsText = mutableListOf<String>()
        armorSets.forEach { armorSet ->
            armorParts.forEach { armorPart ->
                setsText.add("\"${modID}:${armorSet}_${armorPart}\"")
            }
            println("Generated dyeable tag for [$armorSet]")
        }

        val jsonText = "{\n" +
                "  \"replace\": false,\n" +
                "  \"values\": [\n" +
                setsText.joinToString(",\n") { it } +
        "]\n" +
                "}"
        File(dyeableTagFile).writeText(jsonText)
    }
}

//----------------------------------------------------------------------------------------------------------------------

tasks.register("generateSharedItemModels") {
    doLast {
        val armorSets = getArmorSets() ?: return@doLast;
        val itemModelTemplate = File("$itemModelsTemplatesDir${File.separator}armor.json").readText()
        val itemDyedModelTemplate = File("$itemModelsTemplatesDir${File.separator}armor_dyed.json").readText()
        armorSets.forEach { armorSet ->
            armorParts.forEach { armorPart ->
                val filledItemModel = itemModelTemplate.replace(itemIdKey, "${armorSet}_${armorPart}")
                val filledItemDyedModel = itemDyedModelTemplate.replace(itemIdKey, "${armorSet}_${armorPart}")
                File("${itemModelsOutputDir}${File.separator}${armorSet}_${armorPart}.json").writeText(filledItemModel)
                File("${itemModelsOutputDir}${File.separator}${armorSet}_${armorPart}_dyed.json").writeText(filledItemDyedModel)
            }
            println("Generated item models for [$armorSet]")
        }
    }
}

fun getArmorSets(): List<String>? {
    val armorSetsFile = File(armorSetsFile)
    if (!armorSetsFile.exists()) {
        println("Armor sets file not found.")
        return null
    }

    val jsonParsed = JsonSlurper().parse(armorSetsFile) as Map<String, List<String>>
    return jsonParsed.get("armor_sets")
}

//----------------------------------------------------------------------------------------------------------------------
val genInfoDir = "resourcesGeneration"
val armorSetsFile = "${genInfoDir}${File.separator}armor_sets.json"
val recipesInfoDir = "${genInfoDir}${File.separator}recipes"
val itemModelsInfoDir = "${genInfoDir}${File.separator}item_models"
val recipesFile = "$recipesInfoDir${File.separator}armor_recipes.json"
val mcVersionsInfoFile = "$genInfoDir${File.separator}mc_versions_info.json"
val recipesTemplatesDir = "$recipesInfoDir${File.separator}templates"
val itemModelsTemplatesDir = "$itemModelsInfoDir${File.separator}templates"

val itemModelsOutputDir = "shared${File.separator}resources${File.separator}item_models"
val tagsOutputDir = "shared${File.separator}resources${File.separator}tags"
val dyeableTagFile = "${tagsOutputDir}${File.separator}dyeable.json"

val itemIdKey = "ITEM_ID"
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
    val template = getRecipesTemplateByVersion(version) ?: return

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
 *
 * @throws Exception if the template or an ingredient is not found
 */
fun genRecipeByArmorSet(template: String, armorSetName: String, recipeItems: Map<String, String>, recipesDir: String) {
    armorParts.forEach { part ->
        val currentFileName = "${armorSetName}_$part"

        val templateItem = recipeItems["template"] ?: throw Exception("Template item not found for [$armorSetName]")
        val additionItem = recipeItems["addition"] ?: throw Exception("Addition item not found for [$armorSetName]")

        var filled = template

        filled = filled.replace(templateItemKey, templateItem)
        filled = filled.replace(additionItemKey, additionItem)
        filled = filled.replace(resultItemKey, "$modID:$currentFileName")
        filled = filled.replace(baseItemKey, "${baseItemPrefix}_$part")

        File("$recipesDir${File.separator}$currentFileName.json").writeText(filled)
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
fun getRecipesTemplateVersions(): Set<String> {
    val versions: MutableSet<String> = mutableSetOf()
    for (file in File(recipesTemplatesDir).listFiles()) {
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
fun getRecipesTemplateByVersion(version: String): String? {
    val templateFile = File(recipesTemplatesDir, "$version.json")

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