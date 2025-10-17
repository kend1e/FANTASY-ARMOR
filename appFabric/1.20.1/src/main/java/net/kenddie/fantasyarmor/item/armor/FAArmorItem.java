package net.kenddie.fantasyarmor.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kenddie.fantasyarmor.client.armor.model.FAArmorModel;
import net.kenddie.fantasyarmor.client.armor.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.kenddie.fantasyarmor.config.FAConfigs;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class FAArmorItem extends ArmorItem implements GeoItem {
    private static final Map<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        map.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        map.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        map.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final FAArmorSet armorSet;
    private final Supplier<FAArmorAttributes> attributesSupplier;
    private Multimap<Attribute, AttributeModifier> cachedModifiers;

    protected FAArmorItem(FAArmorSet armorSet, Type type, Supplier<FAArmorAttributes> attributesSupplier) {
        super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1).fireResistant());
        this.armorSet = armorSet;
        this.attributesSupplier = attributesSupplier;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (FAConfigs.getMainConfig().showDescriptions) {
            super.appendHoverText(stack, world, tooltip, flag);

            String translationKey = this.getDescriptionId() + ".tooltip";
            String translatedText = Component.translatable(translationKey).getString();

            Font font = Minecraft.getInstance().font;

            String[] lines = translatedText.split("\n");

            for (String line : lines) {
                StringBuilder currentLine = new StringBuilder();
                String[] words = line.split(" ");

                for (String word : words) {
                    if (font.width(currentLine + word) > FAConfigs.getMainConfig().descriptionsLength) {
                        tooltip.add(Component.literal(currentLine.toString()));
                        currentLine = new StringBuilder();
                        currentLine.append("§7");
                    }
                    if (currentLine.length() > 2) {
                        currentLine.append(" ");
                        currentLine.append("§7");
                    }
                    currentLine.append(word);
                }

                if (currentLine.length() > 0) {
                    tooltip.add(Component.literal(currentLine.toString()));
                }
            }
        }
    }


    @Environment(EnvType.CLIENT)
    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<? extends FAArmorItem> renderer;

            @Override
            public HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<LivingEntity> original) {
                if (renderer == null) {
                    renderer = createArmorRenderer();
                }

                renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                //noinspection unchecked
                return renderer;
            }
        });
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        if (slot != this.type.getSlot() || !FAConfigs.getMainConfig().applyModifiers) {
            return super.getAttributeModifiers(stack, slot);
        }

        if (cachedModifiers == null) {
            cachedModifiers = buildModifiers();
        }

        return cachedModifiers;
    }

    private Multimap<Attribute, AttributeModifier> buildModifiers() {
        FAArmorAttributes armorAttributes = attributesSupplier.get();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(this.type);

        if (armorAttributes.armor() != 0) {
            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor", armorAttributes.armor(), AttributeModifier.Operation.ADDITION));
        }
        if (armorAttributes.armorToughness() != 0) {
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", armorAttributes.armorToughness(), AttributeModifier.Operation.ADDITION));
        }
        if (armorAttributes.knockbackResistance() != 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", armorAttributes.knockbackResistance(), AttributeModifier.Operation.ADDITION));
        }
        if (armorAttributes.movementSpeed() != 0) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor movement speed", armorAttributes.movementSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (armorAttributes.maxHealth() != 0) {
            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Armor health gain", armorAttributes.maxHealth(), AttributeModifier.Operation.ADDITION));
        }
        if (armorAttributes.attackDamage() != 0) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Armor attack damage", armorAttributes.attackDamage(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (armorAttributes.attackSpeed() != 0) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Armor attack speed", armorAttributes.attackSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (armorAttributes.luck() != 0) {
            builder.put(Attributes.LUCK, new AttributeModifier(uuid, "Armor luck", armorAttributes.luck(), AttributeModifier.Operation.ADDITION));
        }

        return builder.build();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Environment(EnvType.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(armorSet.getGeoPath(), armorSet.getTexturePath()));
    }

    public List<MobEffectInstance> getFullSetEffects() {
        return FAArmorEffectsConfig.getEffectsFor(
                armorSet.getName(),
                FAConfigs.getMainConfig().showParticles,
                FAConfigs.getMainConfig().showEffectIcon
        );
    }
}