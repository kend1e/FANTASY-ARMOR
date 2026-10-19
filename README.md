# Fantasy Armor

A Minecraft mod with armor sets and effects for Forge, NeoForge, and Fabric.

| Loader   | Minecraft                       |
|----------|---------------------------------|
| Forge    | 1.19.4, 1.20.1, 1.21.1, 1.21.11 |
| NeoForge | 1.21.1                          |
| Fabric   | 1.20.1, 1.21.1, 1.21.10         |

Project needs JDK 21 to run Gradle. Toolchains select Java 17 or 21 for each target.

```bash
# Build all platforms
./gradlew assemble

# Build one platform
./gradlew :platforms:fabric-1.21.1:assemble

# Run a development client or server
./gradlew :platforms:fabric-1.21.1:runClient
./gradlew :platforms:fabric-1.21.1:runServer
```

Result JARs go to `dist/`.
`./gradlew clean` removes build outputs and `dist/`.

Content structure:

- [definitions/armor.kt](definitions/armor.kt) - armor sets, attributes, effects, and recipe ingredients.
- [definitions/translations.kt](definitions/translations.kt) - localized names and descriptions.
- [definitions/recipes.kt](definitions/recipes.kt) - shared crafting recipes.
- [definitions/minecraft.kt](definitions/minecraft.kt) - Minecraft resource formats and Java versions.
- [definitions/targets.kt](definitions/targets.kt) - release versions and typed Forge, NeoForge, and Fabric dependency settings.
- [gradle/libs.versions.toml](gradle/libs.versions.toml) - Gradle plugin versions.
- `resources/` - shared textures, geometry, and logo.
- `core/` - logic without Minecraft APIs.
- `minecraft/<version>/` - shared armor modifiers, set effects, stack components, and rendering for each Minecraft version.
- `loaders/` - shared loader initialization and configuration code.
- `platforms/` - code for individual targets.
- `build-logic/` - Gradle plugins, content DSL, and resource generators.

Resources are generated automatically into each module's `build/generated/` during builds and Gradle game launches. 
To generate content separately `./gradlew generateAll` exists.
