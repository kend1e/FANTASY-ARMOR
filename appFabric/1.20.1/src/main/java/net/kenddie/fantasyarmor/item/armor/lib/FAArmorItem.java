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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class FAArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    protected FAArmorItem(Type type, double knockbackResistance, double movementSpeed, double maxHealth, double attackDamage, double attackSpeed, double luck) {
        super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        FAConfig config = FAConfig.getInstance();
        if (config.applyModificators) {
            if (knockbackResistance > 0) {
                builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "Armor knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
            }

            if (movementSpeed > 0) {
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Armor movement speed", movementSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            if (maxHealth > 0) {
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Armor health gain", maxHealth, AttributeModifier.Operation.ADDITION));
            }

            if (attackDamage > 0) {
                builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "Armor attack damage", attackDamage, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            if (attackSpeed > 0) {
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "Armor attack speed", attackSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            if (luck > 0) {
                builder.put(Attributes.LUCK, new AttributeModifier(UUID.randomUUID(), "Armor luck", luck, AttributeModifier.Operation.ADDITION));
            }

        }
        attributeModifiers = builder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        FAConfig config = FAConfig.getInstance();
        if (config.showDescriptions) {
            super.appendHoverText(stack, world, tooltip, flag);

            String translationKey = this.getDescriptionId() + ".tooltip";
            String translatedText = Component.translatable(translationKey).getString();

            int maxWidth;
            if (config.descrtiptionsLength < 20 || config.descrtiptionsLength > 1000) {
                maxWidth = 250;
            }
            else {
                maxWidth = config.descrtiptionsLength;
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
            builder.putAll(modifiers);

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