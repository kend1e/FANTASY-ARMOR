# How to Add a New Minecraft Version

So a new Minecraft version dropped and you want to add support? Here's the process.

## Centralized Gradle build

All loader/version modules are included in the root `settings.gradle` and use
one Gradle 9.5.0 wrapper. Import the root project in IntelliJ IDEA and choose
JDK 21 as the Gradle JVM. Java toolchains select Java 17 or 21 per module.
Plugin versions are centralized in `gradle/libs.versions.toml`.

Forge modules use ForgeGradle 7. Parchment mappings are supported directly;
do not apply the old Librarian or MixinGradle plugins. Forge 1.19.4 and 1.20.1
also use `net.minecraftforge.renamer` to remap GeckoLib for development and
produce SRG release jars with the `srg` classifier. Their `assemble` tasks
include `renameJar`; distribute the `-srg.jar`, not the development jar.
Forge 1.21.1 and newer use official names at runtime and need no Renamer.

```bash
./gradlew projects
./gradlew :appForge:1.20.1:build
./gradlew :appForge:1.21.11:processResources
./gradlew :appForge:1.21.11:compileJava
./gradlew :appForge:1.21.11:runClient
```

Forge builds compile shared `appCommon` sources into each mod's source set so
both development runs and packaged jars contain the shared code/resources.
Fabric and NeoForge continue to depend on `appCommon` as a Java library.
The Forge 1.21.11 source port is still in progress: configuring the build and
processing resources do not imply that its old Java APIs compile successfully.

## Prerequisites

Before diving in, gather these:

1. **Loader**: Forge, Fabric, or NeoForge
2. **Loader version**: Check loader's website for MC version support
3. **GeckoLib version**: Check [GeckoLib releases](https://github.com/bertsmod/geckolib)
4. **Parchment mappings**: Check [ParchmentMC](https://parchmentmc.org/)
5. **API changes**: Read MC, loader, and GeckoLib changelogs

## Step 1: Create Directory Structure

```
appNeoForge/
  1.22.0/
    build.gradle
    gradle.properties
    src/main/
      java/net/kenddie/fantasyarmor/
      resources/
      templates/META-INF/neoforge.mods.toml  # or fabric.mod.json for Fabric
```

## Step 2: gradle.properties

```properties
# NeoForge example
minecraft_version=1.22.0
neo_version=22.0.XX
geckolib_version=X.X.X
mod_version=1.X.X-1.22.0
parchment_mappings_version=YYYY.MM.DD
parchment_minecraft_version=1.22.0
java_version=21
```

## Step 3: build.gradle

Start from a module using the same build-plugin generation as the target MDK.
Copying an old ForgeGradle 6 script into this ForgeGradle 7 build is not supported.
Key sections:

### NeoForge (ModDevGradle)
```gradle
plugins {
    id 'net.neoforged.moddev' version 'X.X.X'
}
neoForge {
    version = project.neo_version
    parchment { ... }
    mods { "${mod_id}" { sourceSet(sourceSets.main); sourceSet(project(':appCommon').sourceSets.main) } }
}
```

### Fabric (Fabric Loom)
```gradle
plugins { id 'fabric-loom' version 'X.X' }
dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings loom.layered() { officialMojangMappings(); parchment(...) }
    modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
}
```

### Shared code and resources:

For Fabric and NeoForge:
```gradle
implementation project(':appCommon')
jar { from project(':appCommon').sourceSets.main.output }

tasks.named('processResources', ProcessResources).configure {
    from("${rootProject.projectDir}/shared/resources/item_models") { into 'assets/fantasy_armor/models/item' }
    from("${rootProject.projectDir}/shared/resources/items")       { into 'assets/fantasy_armor/items' }
    from("${rootProject.projectDir}/shared/resources/icons")       { into 'assets/fantasy_armor/textures/item' }
    from("${rootProject.projectDir}/shared/resources/armor")       { into 'assets/fantasy_armor/textures/armor' }
    from("${rootProject.projectDir}/shared/resources/geo")         { into 'assets/fantasy_armor/geo' }  # geckolib/models for 1.21.10+
    from("${rootProject.projectDir}/shared/resources/lang_forge")  { into 'assets/fantasy_armor/lang' }
    from("${rootProject.projectDir}/shared/resources/tags")        { into 'data/minecraft/tags/item' }
}
```

## Step 4: settings.gradle

```gradle
include(':appNeoForge:1.22.0')
```

## Step 5: mc_versions_info.json

```json
{
  "1.22.0": {
    "recipes_dir": "recipe",
    "armor_type_class": "ArmorType",
    "armor_type_import": "net.minecraft.world.item.equipment.ArmorType",
    "factory_type": "QuadFunction",
    "factory_type_params": "A, B, C, D, R",
    "factory_apply_params": "A a, B b, C c, D d",
    "factory_field_generics": "FAArmorSet, ArmorType, Supplier<FAArmorAttributes>, Item.Properties, FAArmorItem",
    "create_params": "ArmorType type, Supplier<FAArmorAttributes> attributesSupplier, Item.Properties properties",
    "create_call": "factory.apply(this, type, attributesSupplier, properties)",
    "geo_path_expr": "name + \"_armor\"",
    "has_name_constructor": false
  }
}
```

Key differences by version:
- `recipes_dir`: `"recipes"` for 1.19.4/1.20.1, `"recipe"` for 1.21.1+
- `armor_type_class`: `"ArmorItem.Type"` for ≤1.21.1, `"ArmorType"` for 1.21.10+
- `factory_type`: `"TriFunction"` for ≤1.21.1, `"QuadFunction"` for 1.21.10+

## Step 6: Recipe Template (if format changed)

Create `resourcesGeneration/recipes/templates/1.22.0.json` if needed.

## Step 7: Java Source Files

Copy from closest version and adapt APIs.

| File | Notes |
|------|-------|
| `FAArmorSet.java`, `FAArmorSets.java` | Generated by `generateJavaSources` |
| `FAArmorItem.java` | Major changes between versions (ArmorItem vs Item) |
| `FAArmorRenderer.java` | GeckoLib version-specific |
| `FARenderMixin.java` | Fabric only |

## Step 8: Local Resources

```
src/main/resources/assets/fantasy_armor/
  textures/item/moon_crystal.png
  textures/item/moon_crystal.png.mcmeta
  models/item/moon_crystal.json
```

Plus mod metadata file.

## Step 9: Generate and Build

```bash
./gradlew generateAll
./gradlew :appNeoForge:1.22.0:compileJava
./gradlew :appNeoForge:1.22.0:runClient
```

## Common Issues

- **"Cannot get config value before config is loaded"**: Items registered before config. Add safe accessors with try-catch fallback to `getDefault()`.
- **Missing textures/geo**: Check `processResources` destination paths.
- **GeckoLib mismatch**: Check renderer against current GeckoLib API.

## API Breakpoints

### 1.21.1 → 1.21.10 (major)
- `ArmorItem.Type` → `ArmorType` (new package)
- `FAArmorItem extends ArmorItem` → `extends Item`
- `TriFunction` → `QuadFunction` factory
- GeckoLib 4 → 5 (new rendering architecture, DataTickets, render state)

See docs 5 and 6 for full API change details.
