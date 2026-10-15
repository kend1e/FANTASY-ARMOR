# What Changed From the Original Repo

If you're looking at this and thinking "wait, this is different" — you're right! The big change is `appCommon`, a shared Java module that eliminates all that duplicated code across loaders and versions. No more fixing the same bug in seven places.

## Structure Changes

### Before
Each version directory was self-contained with its own copies of `FAArmorAttributes`, `FAArmorDefaults`, `FAEffectDefaults`, `EffectEntry`, `CapePhysics`, and all resources.

### After
```
appCommon/                     (NEW - shared Java module)
  src/main/java/net/kenddie/fantasyarmor/shared/
    armor/FAArmorAttributes.java
    config/EffectEntry.java, FAArmorDefaults.java, FAEffectDefaults.java
    client/CapePhysics.java
  src/main/resources/
    armor_defaults.json, effect_defaults.json

appForge/1.19.4, 1.20.1, 1.21.1   (loader-specific code only)
appFabric/1.20.1, 1.21.1, 1.21.10
appNeoForge/1.21.1
shared/resources/                 (expanded - all shared resources)
resourcesGeneration/              (+ Java templates for code generation)
```

## Build Integration

Each loader's `build.gradle` includes:
```gradle
implementation project(':appCommon')

mods {
    "${mod_id}" {
        sourceSet(sourceSets.main)
        sourceSet(project(':appCommon').sourceSets.main)
    }
}
jar {
    from project(':appCommon').sourceSets.main.output
}
```

## Generation System Additions

New templates in `resourcesGeneration/`:
- `FAArmorSet.java.template` - generates the enum with version-specific factory types and imports
- `FAArmorSets.java.template` - generates inner classes container

New task `generateJavaSources` reads `mc_versions_info.json` to produce version-correct source files.

## What Stayed Loader-Specific

Config classes (`FAConfig`, `FAArmorConfig`, `FAArmorAttributesConfig`, `FAArmorEffectsConfig`) stay where they are — each loader has its own config API, so there's no good way to share them. They do pull their default values from `FAArmorDefaults` in appCommon now, though.

## Additional Changes in This Branch

**Durability System**: Configurable via `FAConfig.enableDurability`, values from `FAArmorDefaults`.

**Dyeable Armor (1.21.10)**: Fixed missing `dyeable` tag, dye render layer, and `appendHoverText` threading crash.

## The Payoff

To give you a sense of how much this helps:
- **Before**: Around 60+ duplicated files across versions
- **After**: 5 shared Java files + 2 shared JSON resources in appCommon
- **Net result**: About 1,100 fewer lines of code across the repo while maintaining identical functionality
