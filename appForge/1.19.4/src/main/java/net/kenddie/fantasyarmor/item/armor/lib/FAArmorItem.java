package net.kenddie.fantasyarmor.item.armor.lib;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.kenddie.fantasyarmor.config.FAConfig;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public abstract class FAArmorItem extends ArmorItem implements GeoItem {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    protected FAArmorItem(Type type, FAArmorAttributes armorAttributes) {
        super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

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

        attributeModifiers = builder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (FAConfig.showDescriptions) {
            super.appendHoverText(stack, world, tooltip, flag);

            String translationKey = this.getDescriptionId() + ".tooltip";
            tooltip.add(Component.translatable(translationKey));
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<? extends FAArmorItem> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (renderer == null) {
                    renderer = createArmorRenderer();
                }

                renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return renderer;
            }
        });
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot != this.type.getSlot() || !FAConfig.applyModifiers) {
            return super.getAttributeModifiers(slot, stack);
        }

        return attributeModifiers;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @OnlyIn(Dist.CLIENT)
    protected abstract GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer();

    public abstract List<MobEffectInstance> getFullSetEffects();
}