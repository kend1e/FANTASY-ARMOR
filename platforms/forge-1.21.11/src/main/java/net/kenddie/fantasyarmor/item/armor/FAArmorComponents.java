package net.kenddie.fantasyarmor.item.armor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.config.FAArmorConfig;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


/** Applies native config values to our armor, preserving absolute wear. */
public final class FAArmorComponents {
    public record Applied(String revision, ItemAttributeModifiers modifiers, int maxDamage, boolean unbreakable,
                          boolean manageAttributes, boolean manageDurability) {
        public static final Codec<Applied> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("revision").forGetter(Applied::revision),
                ItemAttributeModifiers.CODEC.fieldOf("modifiers").forGetter(Applied::modifiers),
                Codec.INT.fieldOf("max_damage").forGetter(Applied::maxDamage),
                Codec.BOOL.fieldOf("unbreakable").forGetter(Applied::unbreakable),
                Codec.BOOL.fieldOf("manage_attributes").forGetter(Applied::manageAttributes),
                Codec.BOOL.fieldOf("manage_durability").forGetter(Applied::manageDurability)
        ).apply(instance, Applied::new));
    }

    public static final DeferredRegister<DataComponentType<?>> TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, FantasyArmor.MOD_ID);
    public static final RegistryObject<DataComponentType<Applied>> APPLIED = TYPES.register("applied_armor_settings",
            () -> DataComponentType.<Applied>builder().persistent(Applied.CODEC).build());

    public static void update(ItemStack stack) {
        if (!FAArmorConfig.SPEC.isLoaded() || !(stack.getItem() instanceof FAArmorItem item)) return;
        var attributes = FAArmorConfig.ARMOR_CONFIGS.get(item.getArmorSet().getName())
                .get(item.getArmorType().getName()).read().withBonuses(FAConfig.applyModifiers());
        var modifiers = FAArmorItem.buildAttributeModifiers(item.getArmorType(), attributes);
        if (!modifiers.equals(stack.get(DataComponents.ATTRIBUTE_MODIFIERS))) {
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
        }
        int maxDamage = Math.max(1, (int) attributes.durability());
        if (stack.getOrDefault(DataComponents.MAX_DAMAGE, 0) != maxDamage) {
            stack.set(DataComponents.MAX_DAMAGE, maxDamage);
        }
        if (!stack.has(DataComponents.DAMAGE)) stack.set(DataComponents.DAMAGE, 0);
        if (!FAConfig.enableDurability() || attributes.durability() <= 0) {
            if (!stack.has(DataComponents.UNBREAKABLE)) stack.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
        } else {
            stack.remove(DataComponents.UNBREAKABLE);
        }
        // Keep the codec registration to read old saves, but no longer persist session markers.
        stack.remove(APPLIED.get());
    }

    private FAArmorComponents() {}
}
