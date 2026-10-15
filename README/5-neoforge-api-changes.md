# NeoForge API Changes: 1.21.1 → 1.21.10

This is a reference for what changed between versions — useful when porting or debugging.

## Recurring Import Changes

| 1.21.1 | 1.21.10 |
|---|---|
| `ArmorItem.Type` from `net.minecraft.world.item.ArmorItem` | `ArmorType` from `net.minecraft.world.item.equipment.ArmorType` |
| `net.minecraft.world.item.ArmorMaterials` | `net.minecraft.world.item.equipment.ArmorMaterials` |
| `software.bernie.geckolib.animation.AnimatableManager` | `software.bernie.geckolib.animatable.manager.AnimatableManager` |

---

## FAItems.java

```java
// 1.21.1
ITEMS.register("moon_crystal", () -> new Item(new Item.Properties()));

// 1.21.10
ITEMS.registerSimpleItem("moon_crystal");
```

---

## FAArmorItems.java

### Registration Pattern
```java
// 1.21.1
FAItems.ITEMS.register(name, () -> set.create(type, attributesSupplier));

// 1.21.10
FAItems.ITEMS.registerItem(name, props -> set.create(type, attributesSupplier, props));
```

### Config Timing
Items register before config loads in 1.21.10. Add safe accessor:
```java
private static double safeGet(ModConfigSpec.DoubleValue val) {
    try { return val.get(); }
    catch (IllegalStateException e) { return ((ModConfigSpec.ConfigValue<Double>) val).getDefault(); }
}
```

---

## FAArmorItem.java — Major Rewrite

This file changed the most. Here's a breakdown of what's different.

### Base Class
- 1.21.1: `extends ArmorItem`
- 1.21.10: `extends Item` with `.humanoidArmor()` in properties

### Constructor

```java
// 1.21.1
protected FAArmorItem(FAArmorSet armorSet, Type type, Supplier<FAArmorAttributes> attributesSupplier) {
    super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1).fireResistant());
```

```java
// 1.21.10
protected FAArmorItem(FAArmorSet armorSet, ArmorType type,
                      Supplier<FAArmorAttributes> attributesSupplier, Properties properties) {
    super(buildProperties(properties, type, attributesSupplier.get()));
```

Notice we no longer create properties ourselves — we receive them from the registration system and augment them.

### Attribute Modifiers: Lazy → Eager

In 1.21.1, attribute modifiers were built lazily when first requested:
```java
// 1.21.1
@Override
public ItemAttributeModifiers getDefaultAttributeModifiers() {
    if (cachedModifiers == null) cachedModifiers = buildModifiers();
    return cachedModifiers;
}
```

In 1.21.10, they're built once at construction time and baked into the item's properties:
```java
// 1.21.10
private static Item.Properties buildProperties(Item.Properties properties, ArmorType armorType,
                                                FAArmorAttributes armorAttributes) {
    ItemAttributeModifiers modifiers = buildAttributeModifiers(armorType, armorAttributes);
    return properties.stacksTo(1).fireResistant()
            .humanoidArmor(ArmorMaterials.NETHERITE, armorType)
            .component(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
}
```

The `buildModifiers()` instance method became `buildAttributeModifiers()` static method, since it no longer has access to `this`.

### Durability: Lifecycle Hooks → Properties

In 1.21.1, durability was managed through a bunch of lifecycle method overrides:
- `syncDurabilityComponent(ItemStack)` — synced max damage to the stack
- `getDefaultInstance()` — called syncDurability
- `onCraftedBy(...)` — called syncDurability
- `inventoryTick(...)` — called syncDurability
- `getMaxDamage(ItemStack)` — calculated dynamically
- `isDamageable(ItemStack)` — returned true if durability enabled

**All of these are gone in 1.21.10.** Durability is now set once at construction time via `properties.durability(...)` inside `buildProperties()`, using `safeGetEnableDurability()` to handle the config timing issue.

### Tooltip API Change

```java
// 1.21.1
@Override
public void appendHoverText(ItemStack pStack, TooltipContext pContext,
                            List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
    pTooltipComponents.add(Component.translatable(key));
}
```

```java
// 1.21.10
@Override
public void appendHoverText(ItemStack stack, TooltipContext context,
                            TooltipDisplay tooltipDisplay,
                            Consumer<Component> tooltipAdder, TooltipFlag flag) {
    tooltipAdder.accept(Component.translatable(key));
}
```

The `List<Component>` became a `Consumer<Component>`, and there's a new `TooltipDisplay` parameter.

### DyedItemColor Constructor

```java
// 1.21.1
new DyedItemColor(color, false)
```

```java
// 1.21.10
new DyedItemColor(color)
```

The `showInTooltip` boolean parameter was removed.

### GeoRenderProvider Changes

The method for providing the GeckoLib armor renderer changed significantly:

```java
// 1.21.1
@Override
public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(
        @Nullable T livingEntity, ItemStack itemStack,
        @Nullable EquipmentSlot equipmentSlot,
        @Nullable HumanoidModel<T> original) {
    // returns HumanoidModel<?>
}
```

```java
// 1.21.10
@Override
public @Nullable GeoArmorRenderer<?, ?> getGeoArmorRenderer(
        ItemStack itemStack, EquipmentSlot equipmentSlot) {
    // returns GeoArmorRenderer<?, ?>
}
```

The entity and original model parameters are gone. The return type changed from `HumanoidModel<?>` to `GeoArmorRenderer<?, ?>`. And the renderer type itself changed from `GeoArmorRenderer<? extends FAArmorItem>` (single type param) to `GeoArmorRenderer<?, ?>` (two type params).

### Removed Methods Summary

These 1.21.1 methods have **no equivalent** in 1.21.10:

| Method | Why It's Gone |
|---|---|
| `isDurabilityEnabled()` | Replaced by `safeGetEnableDurability()` |
| `syncDurabilityComponent()` | Durability set at construction |
| `getDefaultInstance()` | No longer needs durability sync |
| `onCraftedBy()` | No longer needs durability sync |
| `inventoryTick()` | No longer needs durability sync |
| `getMaxDamage()` | Durability set at construction |
| `isDamageable()` | Durability set at construction |
| `isEnchantable()` | Default behavior is fine now |
| `getDefaultAttributeModifiers()` | Modifiers baked into properties |
| `createArmorRenderer()` | Inlined into `createGeoRenderer()` |

---

## FAArmorSet.java — Factory Interface Change

The factory function that creates armor items gained a 4th parameter:

```java
// 1.21.1
TriFunction<FAArmorSet, ArmorItem.Type, Supplier<FAArmorAttributes>, FAArmorItem> factory;
FAArmorItem create(ArmorItem.Type type, Supplier<FAArmorAttributes> attributesSupplier) {
    return factory.apply(this, type, attributesSupplier);
}
```

```java
// 1.21.10
QuadFunction<FAArmorSet, ArmorType, Supplier<FAArmorAttributes>, Item.Properties, FAArmorItem> factory;
FAArmorItem create(ArmorType type, Supplier<FAArmorAttributes> attributesSupplier, Item.Properties properties) {
    return factory.apply(this, type, attributesSupplier, properties);
}
```

The `TriFunction` interface was replaced by `QuadFunction` (both are defined at the bottom of the file).

Also, `getGeoPath()` changed from `"geo/" + name + "_armor.geo.json"` to just `name + "_armor"`. GeckoLib 5 handles the path resolution internally — you just give it the model name.

---

## FAArmorSets.java — Constructor Signature Ripple

Every single inner class (all 30 armor sets) changed the same way:

```java
// 1.21.1
HeroHelmet(FAArmorSet armorSet, Type type, Supplier<FAArmorAttributes> armorAttributes) {
    super(armorSet, type, armorAttributes);
}
```

```java
// 1.21.10
HeroHelmet(FAArmorSet armorSet, ArmorType type,
           Supplier<FAArmorAttributes> armorAttributes, Item.Properties properties) {
    super(armorSet, type, armorAttributes, properties);
}
```

These files are generated by `generateJavaSources`, so you don't have to change them by hand.

---

## FAArmorRenderer.java — Complete Rendering Overhaul

This is the second most changed file, and it's where the GeckoLib 4 → 5 migration really hits.

### Class Signature

```java
// 1.21.1
class FAArmorRenderer<T extends FAArmorItem> extends GeoArmorRenderer<T>
```

```java
// 1.21.10
class FAArmorRenderer<T extends FAArmorItem, R extends HumanoidRenderState & GeoRenderState>
        extends GeoArmorRenderer<T, R>
```

GeckoLib 5 adds a second type parameter `R` for the render state. This is part of the new state-based rendering architecture.

### Instance Fields → Static Methods

In 1.21.1, the renderer cached bone references as instance fields:
```java
// 1.21.1
private GeoBone cape;
private GeoBone frontCape;
private GeoBone leftLegCloth;
private GeoBone rightLegCloth;
private GeoBone braid;
private GeoBone epicFightCape;
```

In 1.21.10, **all bone fields are gone**. Bones are fetched from the baked model each frame via `bakedModel.getBone("boneName")`. The bone handling logic moved into static methods: `applyExtraBonesStatic()`, `renderExtraBonesStatic()`, and `renderExtraBone()`.

### Render Color

```java
// 1.21.1
Color getRenderColor(T animatable, float partialTick, int packedLight) {
    return Color.WHITE;
}
```

```java
// 1.21.10
int getRenderColor(T animatable, RenderData renderData, float partialTick) {
    return 0xFFFFFFFF;
}
```

Returns a raw int ARGB value instead of a `Color` object. The parameters also changed.

### Layer Registration

```java
// 1.21.1
addRenderLayer(new FADyeableGeoLayer<>(this));
```

```java
// 1.21.10
this.withRenderLayer(r -> new FADyeableGeoLayer<>((GeoArmorRenderer) r));
```

### Lifecycle Method Changes

These 1.21.1 methods were all removed:
- `grabRelevantBones()` — bones are no longer cached
- `applyBoneVisibilityBySlot()` — visibility handled in `buildRenderTask()`
- `applyBoneVisibilityByPart()` — same
- `preRender()` — physics moved to `applyExtraBonesStatic()`
- `applyBaseTransformations()` — positioning moved to `buildRenderTask()`
- `setAllVisible()` — visibility handled differently now

These 1.21.10 methods are new:
- `captureDefaultRenderState()` — populates render state with dye data via DataTickets
- `buildRenderTask()` — the entire render pipeline: iterates armor segments, renders each geometry part, then handles extra bones
- `applyExtraBonesStatic()` — applies visibility, positioning, and physics to all extra bones (capes, braids, cloth)
- `renderExtraBonesStatic()` — renders extra bones after the segment tree
- `renderExtraBone()` — renders one extra bone
- `isRenderedBySegmentTree()` — checks whether a bone was already rendered by the segment system

### The New Render Pipeline

This is the biggest conceptual shift. In 1.21.1, GeckoLib's lifecycle methods (`preRender`, `applyBaseTransformations`, etc.) were called in sequence, and you overrode them to customize behavior.

In 1.21.10, you override `buildRenderTask()` and take complete control of the rendering. The method receives an `OrderedSubmitNodeCollector` (which collects render tasks to be submitted) and a `CameraRenderState`. You iterate through the model's segments yourself and submit render tasks for each one.

The extra bones (capes, braids, cloth pieces) are rendered separately after the segment tree, because they don't map to standard armor model parts.

---

## FADyeableGeoLayer.java — DataTicket System

This file was completely rewritten to use GeckoLib 5's DataTicket system for passing data through the render pipeline.

### 1.21.1: Direct Access

```java
class FADyeableGeoLayer<T extends FAArmorItem> extends GeoRenderLayer<T> {
    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
                       RenderType renderType, MultiBufferSource bufferSource,
                       VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        // Directly read dye color from animatable
        // Call this.renderer.reRender(...)
    }
}
```

### 1.21.10: State-Based DataTickets

```java
class FADyeableGeoLayer<T extends Item & GeoItem, R extends HumanoidRenderState & GeoRenderState>
        extends TextureLayerGeoLayer<T, GeoArmorRenderer.RenderData, R> {

    static final DataTicket<Boolean> HAS_DYE_TICKET = ...;
    static final DataTicket<Integer> DYE_COLOR_TICKET = ...;
    static final DataTicket<ResourceLocation> OVERLAY_TEX_TICKET = ...;

    @Override
    public void submitRenderTask(R renderState, PoseStack poseStack, BakedGeoModel bakedModel,
                                  SubmitNodeCollector submitNodeCollector, CameraRenderState cameraState,
                                  int packedLight, int packedOverlay, int renderColor, boolean additive) {
        // Read dye data from renderState via tickets
        // Call this.renderer.buildRenderTask(...)
    }
}
```

The base class changed from `GeoRenderLayer<T>` to `TextureLayerGeoLayer<T, RenderData, R>`. The render method changed from `render()` to `submitRenderTask()`. And instead of directly accessing the animatable to check dye colors, the data is stored in the render state via `DataTicket` objects:

1. `FAArmorRenderer.captureDefaultRenderState()` sets the ticket values
2. `FADyeableGeoLayer.submitRenderTask()` reads them back from the render state

---

## FAArmorModel.java — Model Resource Methods

Two of the three GeckoLib model methods changed their parameter types:

```java
// 1.21.1
public ResourceLocation getModelResource(T animatable) { ... }
public ResourceLocation getTextureResource(T animatable) { ... }
public ResourceLocation getAnimationResource(T animatable) { ... }  // unchanged
```

```java
// 1.21.10
public ResourceLocation getModelResource(GeoRenderState renderState) { ... }
public ResourceLocation getTextureResource(GeoRenderState renderState) { ... }
public ResourceLocation getAnimationResource(T animatable) { ... }  // unchanged
```

`getModelResource` and `getTextureResource` now take `GeoRenderState` instead of the animatable. `getAnimationResource` still takes the animatable. This means you can't directly access the armor item from these methods anymore — you'd need to get it from the render state if you needed it.

---

## FARenderUtils.java — Player to RenderState

All the utility methods that took a `Player` now take `AvatarRenderState` instead:

### applyCapeRotation

```java
// 1.21.1
static void applyCapeRotation(Player player, GeoBone bone, float partialTick) {
    // Manual interpolation with Mth.lerp() on player.xCloakO, yCloakO, zCloakO
    CapePhysics.CapeMotion motion = CapePhysics.computeCapeMotion(dx, dy, dz, ...);
    CapePhysics.CapeRotation rot = CapePhysics.computeCapeRotation(motion, ...);
}
```

```java
// 1.21.10
static void applyCapeRotation(AvatarRenderState state, GeoBone bone) {
    // Uses pre-computed state.capeFlap, state.capeLean, state.capeLean2
    CapePhysics.CapeRotation rot = CapePhysics.computeCapeRotation(..., state.capeFlap,
            state.capeLean, state.capeLean2, state.isCrouching);
}
```

No more manual interpolation — `AvatarRenderState` provides pre-computed cape physics values.

### setFrontLegCapeAngle

```java
// 1.21.1
static <T extends Item & GeoItem> void setFrontLegCapeAngle(GeoArmorRenderer<T> renderer, GeoBone bone) {
    GeoBone leftLeg = renderer.getLeftLegBone(...);
    // ...
}
```

```java
// 1.21.10
static void setFrontLegCapeAngle(@Nullable GeoBone leftLeg, @Nullable GeoBone rightLeg, GeoBone frontCape) {
    // Takes bones directly as parameters instead of fetching from renderer
}
```

### applyBraidRotation

```java
// 1.21.1: threshold < -35, sets rotX(30)
// 1.21.10: threshold < -25, sets rotX(Math.toRadians(xRot))
```

The threshold changed slightly, and the rotation value is now computed from the actual look angle instead of being hardcoded.

### setArmsVisibility

`PlayerModel<T extends LivingEntity>` became `PlayerModel` (raw type — the generic was removed from `PlayerModel`).

---

## FARenderEventHandler.java — Gutted

In 1.21.1, this was a full event handler with `RenderPlayerEvent.Pre/Post` subscribers that managed arm visibility when wearing custom armor.

In 1.21.10, it's an empty shell:
```java
public final class FARenderEventHandler {
    private FARenderEventHandler() {}
}
```

The arm visibility logic was absorbed into the renderer pipeline — `FAArmorRenderer.buildRenderTask()` now handles it directly.

---

## FAClientEventHandler.java — Gutted

Same story. In 1.21.1, this was the event handler for:
- `RegisterColorHandlersEvent.Item` — registering item color providers for dye tinting
- `FMLClientSetupEvent` — registering `ItemProperties` for the `dyed` predicate

In 1.21.10, it's empty. The dye color system is now data-driven through item model JSON files using `minecraft:condition` and `minecraft:has_component`.

---

## Config Files

### FAArmorEffectsConfig.java

One registry API change:
```java
// 1.21.1
BuiltInRegistries.MOB_EFFECT.getHolder(id)
```
```java
// 1.21.10
BuiltInRegistries.MOB_EFFECT.get(id)
```

Plus a new helper method `getEffectsFor(String, boolean, boolean)` that wraps effects with particle/icon visibility settings.

### FAArmorConfig.java and FAArmorAttributesConfig.java

Only the `ArmorItem.Type` → `ArmorType` change throughout.

---

## Build System Changes

### gradle.properties
```properties
# 1.21.1
minecraft_version=1.21.1
neo_version=21.1.84
geckolib_version=4.8.2

# 1.21.10
minecraft_version=1.21.10
neo_version=21.10.10-beta
geckolib_version=5.3-alpha-3
```

### build.gradle

- GeckoLib geo model output path: `'assets/fantasy_armor/geo'` → `'assets/fantasy_armor/geckolib/models'`
- Added copy task for `shared/resources/items` → `assets/fantasy_armor/items` (new item model system)

---

## Quick Reference: Import Migration Table

| 1.21.1 Import | 1.21.10 Import |
|---|---|
| `net.minecraft.world.item.ArmorItem` | `net.minecraft.world.item.equipment.ArmorType` |
| `net.minecraft.world.item.ArmorMaterials` | `net.minecraft.world.item.equipment.ArmorMaterials` |
| `net.minecraft.world.entity.LivingEntity` | *(removed from renderer)* |
| `net.minecraft.world.entity.player.Player` | `net.minecraft.client.renderer.entity.state.AvatarRenderState` |
| `net.minecraft.client.model.HumanoidModel` | *(still exists, but renderer returns `GeoArmorRenderer<?,?>`)* |
| `software.bernie.geckolib.animation.AnimatableManager` | `software.bernie.geckolib.animatable.manager.AnimatableManager` |
| `software.bernie.geckolib.util.Color` | *(removed — use raw int ARGB)* |
| `software.bernie.geckolib.renderer.layer.GeoRenderLayer` | `software.bernie.geckolib.renderer.layer.TextureLayerGeoLayer` |
| *(not needed)* | `software.bernie.geckolib.renderer.base.GeoRenderState` |
| *(not needed)* | `software.bernie.geckolib.constant.DataTickets` |
