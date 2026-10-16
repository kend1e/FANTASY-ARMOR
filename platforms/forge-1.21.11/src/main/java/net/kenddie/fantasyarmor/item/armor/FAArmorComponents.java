package net.kenddie.fantasyarmor.item.armor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.config.FAServerSettings;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


/** Applies our server settings once per world session, preserving unrelated stack data. */
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
        var settings = FAServerSettings.current();
        if (settings == null || !(stack.getItem() instanceof FAArmorItem item)) return;
        Applied previous = stack.get(APPLIED.get());
        if (previous != null && previous.revision().equals(settings.revision())) return;

        var target = settings.armor().get(item.getArmorSet()).get(item.getArmorType());
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, target.modifiers());
        stack.set(DataComponents.MAX_DAMAGE, target.maxDamage());
        if (target.unbreakable()) {
            stack.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
        } else {
            stack.remove(DataComponents.UNBREAKABLE);
        }
        // Do not reset or clamp DAMAGE: absolute wear survives configuration changes.
        // Keep the existing codec shape so previously saved stacks remain readable.
        stack.set(APPLIED.get(), new Applied(settings.revision(), target.modifiers(),
                target.maxDamage(), target.unbreakable(), true, true));
    }

    private FAArmorComponents() {}
}
