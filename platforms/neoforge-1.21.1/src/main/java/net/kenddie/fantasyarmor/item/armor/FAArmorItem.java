package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.shared.config.EffectEntry;
import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.client.model.FAArmorModel;
import net.kenddie.fantasyarmor.client.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FAArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final FAArmorSet armorSet;
    private FAArmorAttributes cachedAttributes;
    private final Supplier<FAArmorAttributes> attributesSupplier;
    private ItemAttributeModifiers cachedModifiers;

    public FAArmorItem(FAArmorSet armorSet, Type type, Supplier<FAArmorAttributes> attributesSupplier) {
        super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1).fireResistant());
        this.armorSet = armorSet;
        this.attributesSupplier = attributesSupplier;
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        applyServerSettings(stack);
        return stack;
    }

    @Override
    public void onCraftedBy(@NotNull ItemStack stack, @NotNull Level level, @NotNull Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) applyServerSettings(stack);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!level.isClientSide) applyServerSettings(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        if (FAConfig.enableDurability) {
            double dur = attributesSupplier.get().durability();
            if (dur > 0) return (int) dur;
        }
        return super.getMaxDamage(stack);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return FAConfig.enableDurability && attributesSupplier.get().durability() > 0
                && !stack.has(DataComponents.UNBREAKABLE);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public static final int DEFAULT_COLOR = 0xA06540;

    public boolean hasCustomColor(ItemStack stack) {
        return stack.has(DataComponents.DYED_COLOR);
    }

    public int getColor(ItemStack stack) {
        DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
        return color != null ? color.rgb() : DEFAULT_COLOR;
    }

    public void setColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color, false));
    }

    public void clearColor(ItemStack stack) {
        stack.remove(DataComponents.DYED_COLOR);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (FAConfig.showDescriptions) {
            String translationKey = this.getDescriptionId() + ".tooltip";
            pTooltipComponents.add(Component.translatable(translationKey));
        }

    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<? extends FAArmorItem> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null) {
                    this.renderer = createArmorRenderer();
                }

                //renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return modifiersFor(attributesSupplier.get().withBonuses(FAConfig.applyModifiers));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @OnlyIn(Dist.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {

        return new FAArmorRenderer<>(new FAArmorModel<>("geo/" + armorSet.getGeoPath() + ".geo.json", armorSet.getTexturePath()), armorSet,
                            () -> FAConfig.showCapes, () -> FantasyArmor.isEpicFightLoaded && !FAConfig.epicFightNotStaticCapes, 3.5f);
    }

    public List<EffectEntry> getFullSetEffects() {
        return FAArmorEffectsConfig.ARMOR_EFFECTS_CONFIGS.get(armorSet.getName()).getEffects();
    }

    public FAArmorSet getArmorSet() {
        return armorSet;
    }

    private ItemAttributeModifiers modifiersFor(FAArmorAttributes attributes) {
        if (cachedModifiers == null || !attributes.equals(cachedAttributes)) {
            cachedModifiers = ArmorModifiers.build(type, attributes);
            cachedAttributes = attributes;
        }
        return cachedModifiers;
    }

    public void applyServerSettings(ItemStack stack) {
        var attributes = attributesSupplier.get().withBonuses(FAConfig.applyModifiers);
        ArmorStackSettings.apply(stack, attributes, modifiersFor(attributes), FAConfig.enableDurability);
    }
}
