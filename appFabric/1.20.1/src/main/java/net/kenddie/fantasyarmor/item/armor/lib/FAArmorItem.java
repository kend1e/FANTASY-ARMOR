package net.kenddie.fantasyarmor.item.armor.lib;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kenddie.fantasyarmor.config.FAConfig;
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

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class FAArmorItem extends ArmorItem implements GeoItem {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    protected FAArmorItem(Type type, FAArmorAttributes armorAttributes) {
        super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1).fireResistant());
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (FAConfig.getValues().applyModifiers()) {
            if (armorAttributes.armor() > 0) {
                builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), "Armor", armorAttributes.armor(), AttributeModifier.Operation.ADDITION));
            }

            if (armorAttributes.armorToughness() > 0) {
                builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "Armor toughness", armorAttributes.armorToughness(), AttributeModifier.Operation.ADDITION));
            }

            if (armorAttributes.knockbackResistance() > 0) {
                builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "Armor knockback resistance", armorAttributes.knockbackResistance(), AttributeModifier.Operation.ADDITION));
            }

            if (armorAttributes.movementSpeed() > 0) {
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Armor movement speed", armorAttributes.movementSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            if (armorAttributes.maxHealth() > 0) {
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Armor health gain", armorAttributes.maxHealth(), AttributeModifier.Operation.ADDITION));
            }

            if (armorAttributes.attackDamage() > 0) {
                builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "Armor attack damage", armorAttributes.attackDamage(), AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            if (armorAttributes.attackSpeed() > 0) {
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "Armor attack speed", armorAttributes.attackSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            if (armorAttributes.luck() > 0) {
                builder.put(Attributes.LUCK, new AttributeModifier(UUID.randomUUID(), "Armor luck", armorAttributes.luck(), AttributeModifier.Operation.ADDITION));
            }
        }
        attributeModifiers = builder.build();

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (FAConfig.getValues().showDescriptions()) {
            super.appendHoverText(stack, world, tooltip, flag);

            String translationKey = this.getDescriptionId() + ".tooltip";
            String translatedText = Component.translatable(translationKey).getString();

            int maxWidth;
            if (FAConfig.getValues().descrtiptionsLength() < 20 || FAConfig.getValues().descrtiptionsLength() > 1000) {
                maxWidth = 250;
            }
            else {
                maxWidth = FAConfig.getValues().descrtiptionsLength();
            }

            Font font = Minecraft.getInstance().font;

            String[] lines = translatedText.split("\n");

            for (String line : lines) {
                StringBuilder currentLine = new StringBuilder();
                String[] words = line.split(" ");

                for (String word : words) {
                    if (font.width(currentLine + word) > maxWidth) {
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
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(stack, slot);

        if (slot == this.type.getSlot()) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            if (!FAConfig.getValues().applyModifiers()) {
                builder.putAll(modifiers);
            }
            builder.putAll(this.attributeModifiers);

            return builder.build();
        }

        return modifiers;
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
    protected abstract GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer();

    public abstract List<MobEffectInstance> getFullSetEffects();
}