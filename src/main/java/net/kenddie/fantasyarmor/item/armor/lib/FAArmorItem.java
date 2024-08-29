package net.kenddie.fantasyarmor.item.armor.lib;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
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
    protected final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_UUID = UUID.randomUUID();
    private static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.randomUUID();
    private static final UUID MAX_HEALTH_MODIFIER_UUID = UUID.randomUUID();
    private static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.randomUUID();
    private static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.randomUUID();
    private static final UUID LUCK_MODIFIER_UUID = UUID.randomUUID();



    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public FAArmorItem(Type type, double knockbackResistance, double movementSpeed, double maxHealth, double attackDamage, double attackSpeed, double luck) {
        super(ArmorMaterials.NETHERITE, type, new Properties().stacksTo(1));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "Armor knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
        }

        if (movementSpeed > 0) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER_UUID, "Armor movement speed", movementSpeed, AttributeModifier.Operation.ADDITION));
        }

        if (maxHealth > 0) {
            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH_MODIFIER_UUID, "Armor health gain", maxHealth, AttributeModifier.Operation.ADDITION));
        }

        if (attackDamage > 0) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Armor attack damage", attackDamage, AttributeModifier.Operation.ADDITION));
        }

        if (attackSpeed > 0) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "Armor attack speed", attackSpeed, AttributeModifier.Operation.ADDITION));
        }

        if (luck > 0) {
            builder.put(Attributes.LUCK, new AttributeModifier(LUCK_MODIFIER_UUID, "Armor luck", luck, AttributeModifier.Operation.ADDITION));
        }

        this.attributeModifiers = builder.build();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<? extends FAArmorItem> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if(renderer == null) {
                    renderer = createArmorRenderer();
                }

                renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return renderer;
            }
        });
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);

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

    @OnlyIn(Dist.CLIENT)
    protected abstract GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer();
}