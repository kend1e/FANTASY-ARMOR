package fantasy.build

import fantasy.definitions.armor
import fantasy.definitions.languages
import groovy.json.JsonOutput
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.provider.MapProperty
import org.gradle.api.tasks.*
import javax.inject.Inject

fun json(value: Any): String = JsonOutput.prettyPrint(JsonOutput.toJson(value)) + "\n"

@CacheableTask
abstract class GenerateResources : DefaultTask() {
    @get:Input abstract val contents: MapProperty<String, String>
    @get:OutputDirectory abstract val outputDirectory: DirectoryProperty
    @get:Inject abstract val fileSystem: FileSystemOperations

    @TaskAction fun generate() {
        val root = outputDirectory.get().asFile.toPath()
        val files = contents.get()
        files.keys.forEach { name ->
            require(!java.nio.file.Path.of(name).isAbsolute && root.resolve(name).normalize().startsWith(root)) {
                "Resource path escapes output directory: $name"
            }
        }
        fileSystem.delete { delete(root) }
        for ((name, text) in files.toSortedMap()) {
            val file = root.resolve(name).toFile()
            file.parentFile.mkdirs()
            file.writeText(text, Charsets.UTF_8)
        }
    }
}

fun defaultResources(): Map<String, String> = mapOf(
    "armor_defaults.json" to json(armor.associate { set ->
        set.id to set.pieces.mapKeys { it.key.id }.mapValues { it.value.json() }
    }),
    "effect_defaults.json" to json(armor.associate { set -> set.id to set.effects.map { it.json() } }),
)

fun contentResources(profile: ResourceProfile): Map<String, String> = buildMap {
    val namespace = "fantasy_armor"
    val assets = "assets/$namespace"
    val modern = profile == ResourceProfile.ITEM_DEFINITIONS
    fun ingredient(id: String): Any = if (modern) id else mapOf("item" to id)
    fun model(id: String) = mapOf("type" to "minecraft:model", "model" to "$namespace:item/$id")

    for ((locale, entries) in languages) put("$assets/lang/$locale.json", json(entries))
    for (set in armor) for (piece in Piece.entries) {
        val id = "${set.id}_${piece.id}"
        put("data/$namespace/${profile.recipeDirectory}/$id.json", json(linkedMapOf(
            "type" to "minecraft:smithing_transform", "category" to "equipment",
            "template" to ingredient(set.template), "base" to ingredient("minecraft:netherite_${piece.id}"),
            "addition" to ingredient(set.addition),
            "result" to if (modern) "$namespace:$id" else mapOf(profile.resultKey to "$namespace:$id"),
        )))
        val baseModel = linkedMapOf<String, Any>(
            "parent" to "item/generated", "textures" to mapOf("layer0" to "$namespace:item/$id"),
        )
        if (!modern) baseModel["overrides"] = listOf(mapOf(
            "predicate" to mapOf("$namespace:dyed" to 1.0), "model" to "$namespace:item/${id}_dyed",
        ))
        put("$assets/models/item/$id.json", json(baseModel))
        put("$assets/models/item/${id}_dyed.json", json(mapOf(
            "parent" to "item/generated", "textures" to mapOf(
                "layer0" to "$namespace:item/$id", "layer1" to "$namespace:item/${id}_overlay",
            ),
        )))
        if (modern) put("$assets/items/$id.json", json(mapOf("model" to mapOf(
            "type" to "minecraft:condition", "property" to "minecraft:has_component",
            "component" to "minecraft:dyed_color",
            "on_true" to (model("${id}_dyed") + mapOf("tints" to listOf(
                mapOf("type" to "minecraft:constant", "value" to -1),
                mapOf("type" to "minecraft:dye", "default" to -1),
            ))),
            "on_false" to model(id),
        ))))
    }
    put("data/$namespace/${profile.recipeDirectory}/moon_crystal.json", json(mapOf(
        "type" to "minecraft:crafting_shaped", "pattern" to listOf("###", "#X#", "###"),
        "key" to mapOf("#" to ingredient("minecraft:glow_ink_sac"), "X" to ingredient("minecraft:amethyst_shard")),
        "result" to mapOf(profile.resultKey to "$namespace:moon_crystal"),
    )))
    if (modern) put("$assets/items/moon_crystal.json", json(mapOf("model" to model("moon_crystal"))))
    if (profile != ResourceProfile.LEGACY) {
        fun tag(values: List<String>) = json(mapOf("replace" to false, "values" to values))
        put("data/minecraft/tags/item/dyeable.json", tag(armor.flatMap { set -> Piece.entries.map { "$namespace:${set.id}_${it.id}" } }))
        for ((piece, tag) in mapOf(Piece.HELMET to "head_armor", Piece.CHESTPLATE to "chest_armor", Piece.LEGGINGS to "leg_armor", Piece.BOOTS to "foot_armor")) {
            put("data/minecraft/tags/item/enchantable/$tag.json", tag(armor.map { "$namespace:${it.id}_${piece.id}" }))
        }
    }
}
