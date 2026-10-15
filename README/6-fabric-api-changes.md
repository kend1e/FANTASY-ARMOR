# Fabric API Changes: 1.21.1 → 1.21.10

A lot of changes mirror NeoForge (same MC version, same GeckoLib version), with some Fabric-specific differences around registration, mixins, and dye colors.

## Dependencies

| Dependency | 1.21.1 | 1.21.10 |
|---|---|---|
| `minecraft_version` | `1.21.1` | `1.21.10` |
| `loader_version` | `0.18.2` | `0.18.4` |
| `fabric_api_version` | `0.116.7+1.21.1` | `0.138.4+1.21.10` |
| `geckolib_version` | **`4.8.2`** | **`5.3-alpha-3`** |
| `cloth-config` | `15.0.140` | `20.0.149` |

## Recurring Import Changes

| 1.21.1 | 1.21.10 |
|---|---|
| `ArmorItem.Type` | `ArmorType` from `net.minecraft.world.item.equipment.ArmorType` |
| `net.minecraft.world.item.ArmorMaterials` | `net.minecraft.world.item.equipment.ArmorMaterials` |
| `software.bernie.geckolib.animation.AnimatableManager` | `software.bernie.geckolib.animatable.manager.AnimatableManager` |

---

## FAItems.java — Registration Overhaul

### 1.21.1: Direct Registration
```java
public static final Item MOON_CRYSTAL = register("moon_crystal", new Item(new Item.Properties()));
private static Item register(String name, Item item) {
    return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(..., name), item);
}
```

### 1.21.10: ResourceKey + Properties Factory
```java
public static Item MOON_CRYSTAL;  // non-final
public static Item register(String name, Function<Item.Properties, Item> factory) {
    ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(..., name));
    Item.Properties props = new Item.Properties().setId(key);
    return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(props));
}
```

Items must be created with `ResourceKey` set on properties before construction.

---

## FAArmorItems.java

```java
// 1.21.1
ResourceLocation id = ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, name);
FAArmorItem item = set.create(type, attributesSupplier);
Registry.register(BuiltInRegistries.ITEM, id, item);

// 1.21.10
ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(..., name));
Item.Properties props = new Item.Properties().setId(key);
FAArmorItem item = set.create(type, attributesSupplier, props);
Registry.register(BuiltInRegistries.ITEM, key, item);
```

---

## FAArmorItem.java — Same Major Rewrite as NeoForge

This file went through the same fundamental changes as the NeoForge version.

### Base Class
- 1.21.1: `extends ArmorItem`
- 1.21.10: `extends Item` with `.humanoidArmor()` in properties

### Constructor: 3 → 4 params
```java
// 1.21.1
protected FAArmorItem(FAArmorSet armorSet, ArmorItem.Type type, Supplier<FAArmorAttributes> attributesSupplier)

// 1.21.10
protected FAArmorItem(FAArmorSet armorSet, ArmorType type, Supplier<FAArmorAttributes> attributesSupplier, Item.Properties properties)
```

### buildProperties Now Takes Properties
```java
// 1.21.1
private static Properties buildProperties(Supplier<FAArmorAttributes> attributesSupplier)

// 1.21.10
private static Item.Properties buildProperties(Item.Properties properties, ArmorType armorType, FAArmorAttributes armorAttributes)
```

### Attribute Modifiers: Instance → Static
`buildModifiers()` → `buildAttributeModifiers(ArmorType, FAArmorAttributes)` static method.

### New Field
`private final ArmorType armorType;` with getter `getArmorType()`.

### Removed Fields
`attributesSupplier`, `cachedModifiers`

### DyedItemColor
```java
// 1.21.1: new DyedItemColor(color, false)
// 1.21.10: new DyedItemColor(color)
```

### Tooltip
```java
// 1.21.1: void appendHoverText(ItemStack, Item.TooltipContext, List<Component>, TooltipFlag)
// 1.21.10: void appendHoverText(ItemStack, TooltipContext, TooltipDisplay, Consumer<Component>, TooltipFlag)
```

1.21.10 adds guard: `if (!RenderSystem.isOnRenderThread()) return;`

### GeoRenderProvider
```java
// 1.21.1: getGeoArmorRenderer(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel) → HumanoidModel<?>
// 1.21.10: getGeoArmorRenderer(ItemStack, EquipmentSlot) → GeoArmorRenderer<?, ?>
```

---

## FAArmorSet.java

```java
// 1.21.1
TriFunction<FAArmorSet, ArmorItem.Type, Supplier<FAArmorAttributes>, FAArmorItem> factory;

// 1.21.10
QuadFunction<FAArmorSet, ArmorType, Supplier<FAArmorAttributes>, Item.Properties, FAArmorItem> factory;
```

`getGeoPath()`: `"geo/..." → `name + "_armor"`

Fabric 1.21.1 had two constructors (default + custom name). 1.21.10 keeps only default.

---

## FAArmorSets.java

All classes: `(FAArmorSet, Type, Supplier)` → `(FAArmorSet, ArmorType, Supplier, Item.Properties)`

---

## FARenderMixin.java — Major Rewrite

### 1.21.1: Mixin into PlayerRenderer.render()
```java
@Mixin(PlayerRenderer.class)
@Inject(method = "render(...)", at = @At(value = "INVOKE", target = "...LivingEntityRenderer;render(...)V"))
private void onRender(AbstractClientPlayer player, ...) {
    if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof FAArmorItem) {
        FARenderUtils.setArmsVisibility(((PlayerRenderer)this).getModel(), false);
    }
}
```

### 1.21.10: Mixin into PlayerModel.setupAnim()
```java
@Mixin(PlayerModel.class)
@Inject(method = "setupAnim(AvatarRenderState)V", at = @At("TAIL"))
private void fa_hideArmsIfArmor(AvatarRenderState state, CallbackInfo ci) {
    if (state.chestEquipment != null && state.chestEquipment.getItem() instanceof FAArmorItem) {
        PlayerModel model = (PlayerModel)(Object)this;
        model.rightArm.visible = false; model.leftArm.visible = false;
        model.rightSleeve.visible = false; model.leftSleeve.visible = false;
    }
}
```

Key changes:
- Target: `PlayerRenderer` → `PlayerModel`
- Injection: `INVOKE render()` → `TAIL setupAnim()`  
- Access: `player.getItemBySlot()` → `state.chestEquipment`

---

## FAClientEventHandler.java — Removed

Only existed in 1.21.1. Registered item color providers and `dyed` predicate. Replaced by data-driven item model JSONs with `minecraft:condition`/`minecraft:has_component`.

---

## FAArmorRenderer.java — GeckoLib 5 Rewrite

Same as NeoForge:
- Class gains second type param `R extends HumanoidRenderState & GeoRenderState`
- Bone fields → per-frame lookups via static methods
- `Color getRenderColor()` → `int getRenderColor()`
- `addRenderLayer()` → `withRenderLayer()`
- Removed: `grabRelevantBones()`, `preRender()`, `applyBaseTransformations()`, etc.
- Added: `captureDefaultRenderState()`, `buildRenderTask()`, `applyExtraBonesStatic()`, etc.

---

## FADyeableGeoLayer.java — DataTicket System

Same as NeoForge. `GeoRenderLayer<T>` → `TextureLayerGeoLayer<T, RenderData, R>`. `render()` → `submitRenderTask()`. Uses DataTickets.

---

## FAArmorModel.java

```java
// 1.21.1: getModelResource(T), getTextureResource(T)
// 1.21.10: getModelResource(GeoRenderState), getTextureResource(GeoRenderState)
```

---

## FARenderUtils.java — Player → AvatarRenderState

Same as NeoForge:
- `applyCapeRotation(Player, GeoBone, float)` → `applyCapeRotation(AvatarRenderState, GeoBone)`
- `setFrontLegCapeAngle(GeoArmorRenderer, GeoBone)` → `setFrontLegCapeAngle(GeoBone, GeoBone, GeoBone)`
- `applyBraidRotation(Player, GeoBone, float)` → `applyBraidRotation(AvatarRenderState, GeoBone)`
- `setArmsVisibility(PlayerModel<T>, boolean)` → `setArmsVisibility(PlayerModel, boolean)` (raw type)

---

## FAArmorEffectsConfig.java

```java
// 1.21.1: BuiltInRegistries.MOB_EFFECT.getHolder(id)
// 1.21.10: BuiltInRegistries.MOB_EFFECT.get(id)
```

---

## FAConfigs.java

`armorSupplier()` takes `ArmorItem.Type` → `ArmorType`. Gains private constructor.

---

## Build Changes

```gradle
# Geo path
'assets/fantasy_armor/geo'  →  'assets/fantasy_armor/geckolib/models'
```

Adds copy for `shared/resources/items` → `assets/fantasy_armor/items`.
{
  "model": {
    "type": "minecraft:condition",
    "property": "minecraft:has_component",
    "component": "minecraft:dyed_color",
    "on_true": {
      "type": "minecraft:model",
      "model": "fantasy_armor:hero_helmet_dyed",
      "tints": [{ "type": "minecraft:constant", "value": -1 },
                { "type": "minecraft:dye", "default": -1 }]
    },
    "on_false": {
      "type": "minecraft:model",
      "model": "fantasy_armor:hero_helmet"
    }
  }
}
```

This replaces the programmatic `ItemProperties.register()` + `ColorProviderRegistry` approach from `FAClientEventHandler`.

### Recipe JSON Format

```json
// 1.21.1 — nested objects
{
  "template": { "item": "minecraft:iron_ingot" },
  "base": { "item": "minecraft:iron_helmet" },
  "addition": { "item": "fantasy_armor:moon_crystal" },
  "result": { "id": "fantasy_armor:hero_helmet" }
}

// 1.21.10 — flat strings
{
  "template": "minecraft:iron_ingot",
  "base": "minecraft:iron_helmet",
  "addition": "fantasy_armor:moon_crystal",
  "result": "fantasy_armor:hero_helmet"
}
```

### fabric.mod.json

- Removed: `entrypoints.client` pointing to `FAClientEventHandler`
- Changed: `depends.minecraft` from `~1.21.1` to `~1.21.10`
- Changed: `depends.geckolib` from `>=4.8.2` to `>=5.3-alpha-3`

### fantasy_armor.mixins.json — Unchanged

The mixin config file is identical even though `FARenderMixin.java` was rewritten. The class reference `FARenderMixin` didn't change.

---

## Fabric vs NeoForge: Key Differences in the Migration

While most API changes are shared (they're the same Minecraft version and GeckoLib version), here's what's different about the Fabric side:

| Aspect | Fabric Approach | NeoForge Approach |
|---|---|---|
| Item registration | `Registry.register()` with `ResourceKey` + factory pattern | `DeferredRegister.Items.registerItem()` with lambda |
| Config timing | Not an issue (Fabric loads config earlier) | `safeGet()` wrapper needed for early config access |
| Arm visibility | Mixin into `PlayerModel.setupAnim()` | Was in `RenderPlayerEvent.Pre/Post`, now in renderer pipeline |
| Color/dye registration (1.21.1) | `FAClientEventHandler` as `ClientModInitializer` | `FAClientEventHandler` as `@EventBusSubscriber` |
| Color/dye registration (1.21.10) | Both use data-driven item models (same) | Both use data-driven item models (same) |
| Config system | Cloth Config + custom JSON files | NeoForge's built-in `ModConfigSpec` |

---

## Quick Reference: Import Migration Table

| 1.21.1 Import | 1.21.10 Import |
|---|---|
| `net.minecraft.world.item.ArmorItem` | `net.minecraft.world.item.equipment.ArmorType` |
| `net.minecraft.world.item.ArmorMaterials` | `net.minecraft.world.item.equipment.ArmorMaterials` |
| `net.minecraft.world.entity.player.Player` | `net.minecraft.client.renderer.entity.state.AvatarRenderState` |
| `net.minecraft.client.player.AbstractClientPlayer` | *(removed from mixin)* |
| `net.minecraft.client.renderer.entity.player.PlayerRenderer` | `net.minecraft.client.model.PlayerModel` *(mixin target)* |
| `software.bernie.geckolib.animation.AnimatableManager` | `software.bernie.geckolib.animatable.manager.AnimatableManager` |
| `software.bernie.geckolib.util.Color` | *(removed — use raw int ARGB)* |
| `software.bernie.geckolib.renderer.layer.GeoRenderLayer` | `software.bernie.geckolib.renderer.layer.TextureLayerGeoLayer` |
| *(not needed)* | `software.bernie.geckolib.renderer.base.GeoRenderState` |
| *(not needed)* | `software.bernie.geckolib.constant.DataTickets` |
