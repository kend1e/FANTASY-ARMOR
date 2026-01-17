package net.kenddie.fantasyarmor.item.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.client.model.FAArmorModel;
import net.kenddie.fantasyarmor.client.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.kenddie.fantasyarmor.config.FAConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import org.jetbrains.annotations.NotNull;
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

public abstract class FAArmorItem extends Item implements GeoItem {
    public static final int DEFAULT_COLOR = 0xA06540;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final FAArmorSet armorSet;
    private final ArmorType armorType;


    protected FAArmorItem(FAArmorSet armorSet, ArmorType armorType, Supplier<FAArmorAttributes> attributesSupplier, Item.Properties properties) {
        super(buildProperties(properties, armorType, attributesSupplier.get()));
        this.armorSet = armorSet;
        this.armorType = armorType;
    }

    private static Item.Properties buildProperties(Item.Properties properties, ArmorType armorType, FAArmorAttributes armorAttributes) {
        ItemAttributeModifiers modifiers = buildAttributeModifiers(armorType, armorAttributes);

        return properties
                .stacksTo(1)
                .fireResistant()
                .humanoidArmor(ArmorMaterials.NETHERITE, armorType)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
    }

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
        if (!FAConfigs.getMainConfig().showDescriptions) {
            super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
            return;
        }

        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        String translationKey = this.getDescriptionId() + ".tooltip";
        String translatedText = Component.translatable(translationKey).getString();

        Font font = Minecraft.getInstance().font;
        String[] lines = translatedText.split("\n");

        for (String line : lines) {
            StringBuilder currentLine = new StringBuilder();
            String[] words = line.split(" ");

            for (String word : words) {
                if (font.width(currentLine + word) > FAConfigs.getMainConfig().descriptionsLength) {
                    tooltipAdder.accept(Component.literal(currentLine.toString()));
                    currentLine = new StringBuilder("§7");
                }
                if (currentLine.length() > 2) {
                    currentLine.append(" §7");
                }
                currentLine.append(word);
            }

            if (!currentLine.isEmpty()) {
                tooltipAdder.accept(Component.literal(currentLine.toString()));
            }
        }
    }


    private static ItemAttributeModifiers buildAttributeModifiers(ArmorType armorType, FAArmorAttributes armorAttributes) {
        if (!FAConfigs.getMainConfig().applyModifiers) {
            return ItemAttributeModifiers.builder().build();
        }

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(armorType.getSlot());
        String slotName = armorType.getName();

        if (armorAttributes.armor() != 0) {
            builder.add(
                    Attributes.ARMOR,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_armor"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_armor_toughness"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_knockback"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_speed"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_health"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_attack_damage"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_attack_speed"),
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
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, slotName + "_luck"),
                            armorAttributes.luck(),
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slotGroup
            );
        }

        return builder.build();
    }

    public List<MobEffectInstance> getFullSetEffects() {
        return FAArmorEffectsConfig.getEffectsFor(
                armorSet.getName(),
                FAConfigs.getMainConfig().showParticles,
                FAConfigs.getMainConfig().showEffectIcon
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        // No controllers yet
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?, ?> renderer;

            @Override
            public @Nullable GeoArmorRenderer<?, ?> getGeoArmorRenderer(ItemStack itemStack, EquipmentSlot equipmentSlot) {
                if (this.renderer == null) {
                    boolean dyeable = resourceExists(ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, armorSet.getOverlayPath()));
                    this.renderer = new FAArmorRenderer<>(new FAArmorModel<>(armorSet.getGeoPath(), armorSet.getTexturePath()), dyeable);
                }

                return this.renderer;
            }
        });
    }

    public static boolean resourceExists(ResourceLocation location) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        try {
            return resourceManager.getResource(location).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
