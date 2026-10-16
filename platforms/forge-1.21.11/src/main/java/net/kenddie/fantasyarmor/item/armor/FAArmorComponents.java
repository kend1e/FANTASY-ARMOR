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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Tracks only this mod's last applied values. External overrides are never reasserted. */
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
        ItemAttributeModifiers actual = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        ItemAttributeModifiers expected = previous == null ? item.defaultModifiers() : previous.modifiers();
        boolean manageAttributes = (previous == null || previous.manageAttributes())
                && owned(actual).equals(owned(expected));
        // Explicit first-time stack patches may come from commands or another mod.
        if (previous == null && stack.getComponentsPatch().get(DataComponents.ATTRIBUTE_MODIFIERS) != null)
            manageAttributes = false;

        int actualMaxDamage = stack.getOrDefault(DataComponents.MAX_DAMAGE, 0);
        boolean actualUnbreakable = stack.has(DataComponents.UNBREAKABLE);
        boolean manageDurability = (previous == null || previous.manageDurability())
                && actualMaxDamage == (previous == null ? item.defaultMaxDamage() : previous.maxDamage())
                && actualUnbreakable == (previous == null ? item.defaultUnbreakable() : previous.unbreakable());
        if (previous == null && (stack.getComponentsPatch().get(DataComponents.MAX_DAMAGE) != null
                || stack.getComponentsPatch().get(DataComponents.UNBREAKABLE) != null)) manageDurability = false;

        if (manageAttributes) {
            List<ItemAttributeModifiers.Entry> merged = new ArrayList<>(actual.modifiers());
            merged.removeIf(FAArmorComponents::isOwned);
            merged.addAll(target.modifiers().modifiers());
            ItemAttributeModifiers replacement = new ItemAttributeModifiers(List.copyOf(merged));
            if (!Objects.equals(actual, replacement)) stack.set(DataComponents.ATTRIBUTE_MODIFIERS, replacement);
            actual = replacement;
        }
        if (manageDurability) {
            if (actualMaxDamage != target.maxDamage()) stack.set(DataComponents.MAX_DAMAGE, target.maxDamage());
            if (target.unbreakable()) {
                if (!actualUnbreakable) stack.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
            } else if (actualUnbreakable) stack.remove(DataComponents.UNBREAKABLE);
            actualMaxDamage = target.maxDamage();
            actualUnbreakable = target.unbreakable();
            // DAMAGE is intentionally untouched: keep absolute wear, including while unbreakable.
        }
        stack.set(APPLIED.get(), new Applied(settings.revision(), new ItemAttributeModifiers(owned(actual)),
                actualMaxDamage, actualUnbreakable, manageAttributes, manageDurability));
    }

    private static boolean isOwned(ItemAttributeModifiers.Entry entry) {
        return entry.modifier().id().getNamespace().equals(FantasyArmor.MOD_ID);
    }

    private static List<ItemAttributeModifiers.Entry> owned(ItemAttributeModifiers modifiers) {
        return modifiers.modifiers().stream().filter(FAArmorComponents::isOwned).toList();
    }

    private FAArmorComponents() {}
}
