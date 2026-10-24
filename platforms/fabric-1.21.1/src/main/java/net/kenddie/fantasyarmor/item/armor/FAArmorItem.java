package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.shared.config.EffectEntry;
import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.client.model.FAArmorModel;
import net.kenddie.fantasyarmor.client.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.kenddie.fantasyarmor.config.FAConfigs;
import net.kenddie.fantasyarmor.item.FATooltips;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.ItemAttributeModifiers;
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
        super(ArmorMaterials.NETHERITE, type, buildProperties(attributesSupplier));
        this.armorSet = armorSet;
        this.attributesSupplier = attributesSupplier;
    }

    private static Properties buildProperties(Supplier<FAArmorAttributes> attributesSupplier) {
        Properties props = new Properties().stacksTo(1).fireResistant();
        FAArmorAttributes attrs = attributesSupplier.get();
        if (FAConfigs.getMainConfig().enableDurability && attrs.durability() > 0) {
            props = props.durability((int) attrs.durability());
        }
        return props;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

    public static final int DEFAULT_COLOR = 0xA06540;

    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.world.level.Level level, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (!level.isClientSide) applyServerSettings(stack);
    }

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
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        if (FAConfigs.getMainConfig().showDescriptions) {
            FATooltips.append(
                    Component.translatable(getDescriptionId() + ".tooltip"),
                    FAConfigs.getMainConfig().descriptionsLength,
                    tooltip::add);
        }

    }

    @Environment(EnvType.CLIENT)
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
        return modifiersFor(attributesSupplier.get().withBonuses(FAConfigs.getMainConfig().applyModifiers));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Environment(EnvType.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {

        return new FAArmorRenderer<>(new FAArmorModel<>("geo/" + armorSet.getGeoPath() + ".geo.json", armorSet.getTexturePath()), armorSet,
                            () -> FAConfigs.getMainConfig().showCapes, () -> false, 5.5f);
    }

    public List<EffectEntry> getFullSetEffects() {
        return FAArmorEffectsConfig.getEffectsFor(armorSet.getName());
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
        var attributes = attributesSupplier.get().withBonuses(FAConfigs.getMainConfig().applyModifiers);
        ArmorStackSettings.apply(stack, attributes, modifiersFor(attributes), FAConfigs.getMainConfig().enableDurability);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        applyServerSettings(stack);
        return stack;
    }

    @Override
    public void onCraftedBy(ItemStack stack, net.minecraft.world.level.Level level,
                            net.minecraft.world.entity.player.Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) applyServerSettings(stack);
    }

}
