# How to Add a New Armor Set

Adding a new set is mostly "put files in the right places" — the generation system handles the tedious parts.

## Required Assets

For a new set (let's call it `crystal_guardian`):

| Asset | File |
|-------|------|
| GeckoLib model | `crystal_guardian_armor.geo.json` |
| Body texture | `crystal_guardian_armor.png` |
| Body overlay | `crystal_guardian_armor_overlay.png` |
| Icon textures | `crystal_guardian_[helmet/chestplate/leggings/boots].png` |
| Icon overlays | `crystal_guardian_[helmet/chestplate/leggings/boots]_overlay.png` |

11 files total.

## Step 1: Place Assets

| File | Destination |
|------|------------|
| `*.geo.json` | `shared/resources/geo/` |
| `*_armor*.png` | `shared/resources/armor/` |
| Icon PNGs | `shared/resources/icons/` |

## Step 2: Register in armor_sets.json

```json
// resourcesGeneration/armor_sets.json
{ "armor_sets": ["chess_board_knight", "crucible_knight", "crystal_guardian", ...] }
```

## Step 3: Add Recipe

```json
// resourcesGeneration/recipes/armor_recipes.json
{
  "crystal_guardian": {
    "template": "minecraft:gold_nugget",
    "addition": "fantasy_armor:moon_crystal"
  }
}
```

## Step 4: Add Stats

```json
// appCommon/src/main/resources/armor_defaults.json
{
  "crystal_guardian": {
    "helmet": { "armor": 3.0, "armorToughness": 3.0, "knockbackResistance": 0.1, ... },
    "chestplate": { ... },
    "leggings": { ... },
    "boots": { ... }
  }
}
```

## Step 5: Add Effects (Optional)

```json
// appCommon/src/main/resources/effect_defaults.json
{
  "crystal_guardian": [
    { "id": "minecraft:regeneration", "duration": 200, "amplifier": 0 }
  ]
}
```

## Step 6: Add Translations

```json
// shared/resources/lang_forge/en_us.json
{
  "item.fantasy_armor.crystal_guardian_helmet": "Crystal Guardian Helmet",
  "item.fantasy_armor.crystal_guardian_helmet.tooltip": "...",
  // ... other pieces
}
```

## Step 7: Generate

This is where the magic happens:

```bash
./gradlew generateAll
```

One command creates:
- Smithing recipes for all versions
- Updated dyeable/enchantable tags
- Item definitions and models
- Updated `FAArmorSet.java` and `FAArmorSets.java`

## Step 8: Build and Test

```bash
./gradlew :appForge:1.21.1:build
./gradlew :appForge:1.21.1:runClient
```

## Extra Bones

If your geo model includes these bone names, they're handled automatically:
- `cape`, `frontCape` - physics applied via `CapePhysics`
- `braid` - synced with head rotation
- `leftLegCloth`, `rightLegCloth` - parented to leg bones
- `epicFightCape` - Epic Fight compatibility

## Checklist

- [ ] Geo model + 2 body textures + 8 icon textures in `shared/resources/`
- [ ] Set name in `armor_sets.json`
- [ ] Recipe in `armor_recipes.json`
- [ ] Stats in `armor_defaults.json`
- [ ] (Optional) Effects in `effect_defaults.json`
- [ ] Translations in `lang_forge/en_us.json`
- [ ] Run `./gradlew generateAll`
