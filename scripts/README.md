# Development Scripts

This directory contains utility scripts for building, deploying, and testing Fantasy Armor during development.

## install-dev.sh

Installs locally built Fantasy Armor JARs from `dist/` into existing Minecraft development profiles.

The script:

- Discovers profiles matching `<minecraft-version>-<loader>-DevTest`.
- Matches each profile to its corresponding build in `dist/`.
- Verifies required dependencies before installation:
    - **Forge / NeoForge:** GeckoLib.
    - **Fabric:** GeckoLib, Fabric API, Cloth Config.
- Replaces outdated Fantasy Armor JARs while preserving other mods.
- Skips profiles without matching builds.
- Reports errors independently for each profile.

Dependency checks use JAR filenames and do not guarantee version compatibility. Missing dependencies must be installed manually.

### Usage

Run from the repository root:

```bash
./scripts/install-dev.sh
```

Preview changes without modifying files:

```bash
./scripts/install-dev.sh --dry-run
```

Build and install:

```bash
./gradlew build && ./scripts/install-dev.sh
```

### Options

| Option | Description |
|---|---|
| `--dry-run` | Preview changes without modifying files |
| `--dist DIR` | Override the build distribution directory |
| `--minecraft DIR` | Override the Minecraft directory |
| `--help` | Display usage information |

By default, builds are read from `<repository>/dist/`, and profiles are discovered in `~/.minecraft/versions/`.

The Minecraft directory can also be configured using `MINECRAFT_DIR`.

### Requirements

- Bash
- Existing `*-DevTest` profiles with dedicated `mods/` directories
- Prebuilt Fantasy Armor JARs in `dist/`
- Required dependency JARs installed in each profile

The script does not install Minecraft, mod loaders, or dependencies.

Exit codes: `0` on success, `1` if any profile fails, and `2` for invalid arguments.