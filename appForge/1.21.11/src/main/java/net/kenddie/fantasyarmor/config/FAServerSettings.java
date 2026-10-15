package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.item.armor.FAArmorItem;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** Immutable settings for one running world. Reloaded files take effect in the next world session. */
public final class FAServerSettings {
    public record EffectSettings(net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> effect, int duration, int amplifier) {}
    public record ArmorSettings(ItemAttributeModifiers modifiers, int maxDamage, boolean unbreakable) {}
    public record Snapshot(String revision, Map<FAArmorSet, Map<ArmorType, ArmorSettings>> armor,
                           Map<FAArmorSet, List<EffectSettings>> effects,
                           boolean applyEffects, int effectsInterval, boolean particles, boolean effectIcon) {}

    private static volatile Snapshot current;

    public static Snapshot current() { return current; }

    public static synchronized void onConfigLoaded() {
        if (current != null || !FAConfig.SPEC.isLoaded() || !FAArmorConfig.SPEC.isLoaded()
                || !FAArmorEffectsConfig.SPEC.isLoaded()) return;

        FAConfig.applyConfigValues();
        Map<FAArmorSet, Map<ArmorType, ArmorSettings>> armor = new EnumMap<>(FAArmorSet.class);
        Map<FAArmorSet, List<EffectSettings>> effects = new EnumMap<>(FAArmorSet.class);
        for (FAArmorSet set : FAArmorSet.values()) {
            Map<ArmorType, ArmorSettings> pieces = new EnumMap<>(ArmorType.class);
            for (ArmorType type : ArmorType.values()) {
                var config = FAArmorConfig.ARMOR_CONFIGS.get(set.getName()).get(type.getName());
                var attributes = config.read();
                pieces.put(type, new ArmorSettings(
                        FAConfig.applyModifiers ? FAArmorItem.buildAttributeModifiers(type, attributes) : ItemAttributeModifiers.EMPTY,
                        Math.max(1, (int) attributes.durability()),
                        !FAConfig.enableDurability || attributes.durability() <= 0));
            }
            armor.put(set, Map.copyOf(pieces));
            effects.put(set, FAArmorEffectsConfig.ARMOR_EFFECTS_CONFIGS.get(set.getName()).getEffects().stream()
                    .map(effect -> new EffectSettings(effect.getEffect(), effect.getDuration(), effect.getAmplifier())).toList());
        }
        current = new Snapshot(UUID.randomUUID().toString(), Map.copyOf(armor), Map.copyOf(effects),
                FAConfig.applyArmorEffects, FAConfig.effectsInterval, FAConfig.showParticles, FAConfig.showEffectIcon);
    }

    public static List<MobEffectInstance> effectsFor(FAArmorSet set) {
        Snapshot snapshot = current;
        // Return fresh mutable instances from immutable effect descriptors.
        return snapshot == null ? List.of() : snapshot.effects().getOrDefault(set, List.of()).stream()
                .map(effect -> new MobEffectInstance(effect.effect(), effect.duration(), effect.amplifier(),
                        true, snapshot.particles(), snapshot.effectIcon())).toList();
    }

    public static synchronized void clear() { current = null; }

    private FAServerSettings() {}
}
