# How This Repo Works

Quick orientation for navigating the refactored structure.

## Project Structure

It's a multi-project Gradle build — each loader-version combo is its own subproject, and shared code lives in `appCommon`.

```
FANTASY-ARMOR/
  appCommon/           Shared Java code (FAArmorAttributes, CapePhysics, etc.)
  appForge/            Forge-specific code (1.19.4, 1.20.1, 1.21.1)
  appFabric/           Fabric-specific code (1.20.1, 1.21.1, 1.21.10)
  appNeoForge/         NeoForge-specific code (1.21.1)
  shared/resources/    Shared resources (textures, models, lang, tags)
  resourcesGeneration/ Code generation system
  README/              Documentation
```

## appCommon

This is the heart of the refactor — a plain Java 17 library with no loader dependencies, so it's safe to include everywhere. Contains:
- `shared/armor/FAArmorAttributes.java`
- `shared/config/FAArmorDefaults.java`, `FAEffectDefaults.java`, `EffectEntry.java`
- `shared/client/CapePhysics.java`
- `armor_defaults.json`, `effect_defaults.json`

Each loader's `build.gradle` includes:
```gradle
implementation project(':appCommon')
jar { from project(':appCommon').sourceSets.main.output }
```

## Loader-Specific Modules

Each subproject has `build.gradle`, `gradle.properties`, and `src/main/` with Java code and resources.

### Factory Pattern for Armor Sets

Factory signature varies by version:
- **1.19.4 through 1.21.1**: `TriFunction<FAArmorSet, ArmorItem.Type, Supplier<FAArmorAttributes>, FAArmorItem>`
- **1.21.10+**: `QuadFunction<FAArmorSet, ArmorType, Supplier<FAArmorAttributes>, Item.Properties, FAArmorItem>`

## shared/resources/

Copied to each loader during `processResources`:

```gradle
from("${rootProject.projectDir}/shared/resources/item_models") { into 'assets/fantasy_armor/models/item' }
from("${rootProject.projectDir}/shared/resources/items")       { into 'assets/fantasy_armor/items' }
from("${rootProject.projectDir}/shared/resources/icons")       { into 'assets/fantasy_armor/textures/item' }
from("${rootProject.projectDir}/shared/resources/armor")       { into 'assets/fantasy_armor/textures/armor' }
from("${rootProject.projectDir}/shared/resources/geo")         { into 'assets/fantasy_armor/geo' }  # or geckolib/models for 1.21.10
from("${rootProject.projectDir}/shared/resources/lang_forge")  { into 'assets/fantasy_armor/lang' }
from("${rootProject.projectDir}/shared/resources/tags")        { into 'data/minecraft/tags/item' }
```

| Directory | Contents |
|-----------|----------|
| `armor/` | Full-body armor textures (~62 files) |
| `geo/` | GeckoLib 3D models (~31 files) |
| `icons/` | Inventory icon textures (~240 files) |
| `items/` | Component-based item definitions (~124 files) |
| `item_models/` | Item model JSONs (~240 files) |
| `lang_forge/` | Localization files (5 files) |
| `tags/` | Dyeable + enchantable tags (5 files) |

### Resources That Stay Local
- Recipes (format differs per MC version)
- `logo.png`, `moon_crystal.png + .mcmeta`
- Mod metadata files

## Generation System

### Gradle Tasks

| Task | Output |
|------|--------|
| `generateRecipesData` | Smithing recipes per version |
| `generateTagsData` | dyeable.json tag |
| `generateEnchantableArmorTags` | head/chest/leg/foot enchantable tags |
| `generateSharedItemDefinitions` | Item JSONs with dye conditional rendering |
| `generateSharedItemModels` | Item model JSONs (base + dyed) |
| `generateJavaSources` | FAArmorSet.java + FAArmorSets.java per version |
| `generateAll` | All of the above |

## Build Commands

```bash
# One version
./gradlew :appForge:1.21.1:build
./gradlew :appFabric:1.21.10:build

# Run client
./gradlew :appForge:1.21.1:runClient

# Regenerate all
./gradlew generateAll

# Build everything
./gradlew build
```

## Key gradle.properties

### Root
```properties
mod_id=fantasy_armor
mod_name=Fantasy Armor
mod_license=DSMSLv2
mod_group_id=net.kenddie.fantasyarmor
mod_authors=Kenddie, mon1tor
```

### Per Version
- `minecraft_version`, `loader_version`/`forge_version`/`neo_version`
- `geckolib_version`, `mod_version`
- `parchment_mappings_version`, `parchment_minecraft_version`
