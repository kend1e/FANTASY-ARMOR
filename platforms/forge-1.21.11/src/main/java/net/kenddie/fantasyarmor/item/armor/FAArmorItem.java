package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.client.model.FAArmorModel;
import net.kenddie.fantasyarmor.client.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.config.FAClientConfig;
import net.kenddie.fantasyarmor.config.FAServerSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FAArmorItem extends Item implements GeoItem {
    public static final int DEFAULT_COLOR = 0xA06540;
    private static final boolean DEFAULT_UNBREAKABLE = true;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final FAArmorSet armorSet;
    private final ArmorType armorType;
    private final ItemAttributeModifiers defaultModifiers;
    private final int defaultMaxDamage;

    public FAArmorItem(FAArmorSet armorSet, ArmorType armorType, Supplier<FAArmorAttributes> attributesSupplier, Item.Properties properties) {
        super(buildProperties(properties, armorType, attributesSupplier.get()));
        this.armorSet = armorSet;
        this.armorType = armorType;
        this.defaultModifiers = buildAttributeModifiers(armorType, attributesSupplier.get());
        this.defaultMaxDamage = Math.max(1, (int) attributesSupplier.get().durability());
    }

    private static Item.Properties buildProperties(Item.Properties properties, ArmorType armorType, FAArmorAttributes armorAttributes) {
        ItemAttributeModifiers modifiers = buildAttributeModifiers(armorType, armorAttributes);

        properties = properties
                .stacksTo(1)
                .fireResistant()
                .humanoidArmor(ArmorMaterials.NETHERITE, armorType)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);

        // Configuration is world-specific and is applied to stacks on the server.
        properties.durability(Math.max(1, (int) armorAttributes.durability()));
        if (DEFAULT_UNBREAKABLE) {
            properties.component(DataComponents.UNBREAKABLE, net.minecraft.util.Unit.INSTANCE);
        }

        return properties;
    }

    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.server.level.ServerLevel level,
                              net.minecraft.world.entity.Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, level, entity, slot);
        net.kenddie.fantasyarmor.item.armor.FAArmorComponents.update(stack);
    }

    public ItemAttributeModifiers defaultModifiers() { return defaultModifiers; }
    public int defaultMaxDamage() { return defaultMaxDamage; }
    public boolean defaultUnbreakable() { return DEFAULT_UNBREAKABLE; }

    public FAArmorSet getArmorSet() {
        return armorSet;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public boolean hasCustomColor(ItemStack stack) {
        return stack.has(DataComponents.DYED_COLOR);
    }

    public int getColor(ItemStack stack) {
        DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
        return color != null ? color.rgb() : DEFAULT_COLOR;
    }

    public void setColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
    }

    public void clearColor(ItemStack stack) {
        stack.remove(DataComponents.DYED_COLOR);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        if (FAClientConfig.SHOW_DESCRIPTIONS.get()) {
            tooltipAdder.accept(Component.translatable(getDescriptionId() + ".tooltip"));
        }
    }

    public static ItemAttributeModifiers buildAttributeModifiers(ArmorType armorType, FAArmorAttributes armorAttributes) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(armorType.getSlot());
        String slotName = armorType.getName();

        if (armorAttributes.armor() != 0) {
            builder.add(
                    Attributes.ARMOR,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_armor"),
                            armorAttributes.armor(),
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.armorToughness() != 0) {
            builder.add(
                    Attributes.ARMOR_TOUGHNESS,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_armor_toughness"),
                            armorAttributes.armorToughness(),
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.knockbackResistance() != 0) {
            builder.add(
                    Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_knockback"),
                            armorAttributes.knockbackResistance(),
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.movementSpeed() != 0) {
            builder.add(
                    Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_speed"),
                            armorAttributes.movementSpeed(),
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.maxHealth() != 0) {
            builder.add(
                    Attributes.MAX_HEALTH,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_health"),
                            armorAttributes.maxHealth(),
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.attackDamage() != 0) {
            builder.add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_attack_damage"),
                            armorAttributes.attackDamage(),
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.attackSpeed() != 0) {
            builder.add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_attack_speed"),
                            armorAttributes.attackSpeed(),
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    ),
                    slotGroup
            );
        }

        if (armorAttributes.luck() != 0) {
            builder.add(
                    Attributes.LUCK,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_luck"),
                            armorAttributes.luck(),
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slotGroup
            );
        }

        return builder.build();
    }

    public List<MobEffectInstance> getFullSetEffects() {
        return FAServerSettings.effectsFor(armorSet);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        // No controllers yet
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?, ?> renderer;

            @Override
            public @Nullable GeoArmorRenderer<?, ?> getGeoArmorRenderer(ItemStack itemStack, EquipmentSlot equipmentSlot) {
                if (this.renderer == null) {
                    boolean dyeable = resourceExists(Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, armorSet.getOverlayPath()));
                    this.renderer = new FAArmorRenderer<>(new FAArmorModel<>(armorSet.getGeoPath(), armorSet.getTexturePath()), dyeable);
                }

                return this.renderer;
            }
        });
    }

    public static boolean resourceExists(Identifier location) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        try {
            return resourceManager.getResource(location).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
