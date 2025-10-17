package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Config(name = "fantasy_armor-effects")
public class FAArmorEffectsConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public Map<String, List<EffectEntry>> effects = new HashMap<>();

    public static final class EffectEntry {
        public String id = "minecraft:regeneration";
        public int duration = 200;
        public int amplifier = 0;

        public EffectEntry() {}

        public EffectEntry(String id, int duration, int amplifier) {
            this.id = id;
            this.duration = duration;
            this.amplifier = amplifier;
        }
    }

    public static Map<String, List<EffectEntry>> defaults() {
        Map<String, List<EffectEntry>> map = new HashMap<>();
        map.put(FAArmorSet.WIND_WORSHIPPER.getName(), List.of(
                new EffectEntry("minecraft:jump_boost", 259, 0)
        ));
        map.put(FAArmorSet.WANDERING_WIZARD.getName(), List.of(
                new EffectEntry("minecraft:water_breathing", 259, 0),
                new EffectEntry("minecraft:night_vision", 259, 0),
                new EffectEntry("minecraft:fire_resistance", 259, 0)
        ));
        map.put(FAArmorSet.TWINNED.getName(), List.of(
                new EffectEntry("minecraft:regeneration", 259, 0),
                new EffectEntry("minecraft:haste", 259, 1)
        ));
        map.put(FAArmorSet.THIEF.getName(), List.of(
                new EffectEntry("minecraft:jump_boost", 259, 0)
        ));
        map.put(FAArmorSet.SUNSET_WINGS.getName(), List.of(
                new EffectEntry("minecraft:jump_boost", 259, 0)
        ));
        map.put(FAArmorSet.RONIN.getName(), List.of(
                new EffectEntry("minecraft:jump_boost", 259, 0)
        ));
        map.put(FAArmorSet.SPARK_OF_DAWN.getName(), List.of(
                new EffectEntry("minecraft:night_vision", 259, 0),
                new EffectEntry("minecraft:fire_resistance", 259, 0)
        ));
        map.put(FAArmorSet.SILVER_KNIGHT.getName(), List.of(
                new EffectEntry("minecraft:luck", 259, 0),
                new EffectEntry("minecraft:strength", 259, 0)
        ));
        map.put(FAArmorSet.REDEEMER.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:fire_resistance", 259, 0)
        ));
        map.put(FAArmorSet.GRAVE_SENTINEL.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:fire_resistance", 259, 0)
        ));
        map.put(FAArmorSet.DRAGONSLAYER.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:fire_resistance", 259, 0)
        ));
        map.put(FAArmorSet.OLD_KNIGHT.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:resistance", 259, 0)
        ));
        map.put(FAArmorSet.ECLIPSE_SOLDIER.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:resistance", 259, 0)
        ));
        map.put(FAArmorSet.MALENIA.getName(), List.of(
                new EffectEntry("minecraft:regeneration", 259, 0)
        ));
        map.put(FAArmorSet.HERO.getName(), List.of(
                new EffectEntry("minecraft:regeneration", 259, 0)
        ));
        map.put(FAArmorSet.DEAD_GLADIATOR.getName(), List.of(
                new EffectEntry("minecraft:regeneration", 259, 0)
        ));
        map.put(FAArmorSet.LADY_MARIA.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:regeneration", 259, 0)
        ));
        map.put(FAArmorSet.GOLDEN_HORNS.getName(), List.of(
                new EffectEntry("minecraft:haste", 259, 1)
        ));
        map.put(FAArmorSet.GOLDEN_EXECUTION.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0)
        ));
        map.put(FAArmorSet.GILDED_HUNT.getName(), List.of(
                new EffectEntry("minecraft:jump_boost", 259, 0),
                new EffectEntry("minecraft:strength", 259, 0)
        ));
        map.put(FAArmorSet.FORGOTTEN_TRACE.getName(), List.of(
                new EffectEntry("minecraft:haste", 259, 0),
                new EffectEntry("minecraft:regeneration", 259, 0)
        ));
        map.put(FAArmorSet.FOG_GUARD.getName(), List.of(
                new EffectEntry("minecraft:fire_resistance", 259, 0),
                new EffectEntry("minecraft:resistance", 259, 0)
        ));
        map.put(FAArmorSet.FLESH_OF_THE_FEASTER.getName(), List.of(
                new EffectEntry("minecraft:resistance", 259, 0)
        ));
        map.put(FAArmorSet.EVENING_GHOST.getName(), List.of(
                new EffectEntry("minecraft:regeneration", 259, 0),
                new EffectEntry("minecraft:night_vision", 259, 0)
        ));
        map.put(FAArmorSet.DARK_LORD.getName(), List.of(
                new EffectEntry("minecraft:strength", 259, 0),
                new EffectEntry("minecraft:night_vision", 259, 0),
                new EffectEntry("minecraft:fire_resistance", 259, 0)
        ));
        map.put(FAArmorSet.DARK_COVER.getName(), List.of(
                new EffectEntry("minecraft:regeneration", 259, 0),
                new EffectEntry("minecraft:strength", 259, 0)
        ));
        map.put(FAArmorSet.CRUCIBLE_KNIGHT.getName(), List.of(
                new EffectEntry("minecraft:fire_resistance", 259, 0),
                new EffectEntry("minecraft:regeneration", 259, 0)
        ));
        map.put(FAArmorSet.CHESS_BOARD_KNIGHT.getName(), List.of(
                new EffectEntry("minecraft:haste", 259, 0),
                new EffectEntry("minecraft:regeneration", 259, 0),
                new EffectEntry("minecraft:strength", 259, 0)
        ));
        return map;
    }

    @Override
    public void validatePostLoad() {
        if (effects == null) effects = new HashMap<>();
        Map<String, List<EffectEntry>> def = defaults();
        for (var e : def.entrySet()) {
            effects.putIfAbsent(e.getKey(), e.getValue());
        }
    }

    public static List<MobEffectInstance> getEffectsFor(String armorSetName, boolean showParticles, boolean showIcon) {
        var cfg = me.shedaniel.autoconfig.AutoConfig.getConfigHolder(FAArmorEffectsConfig.class).getConfig();
        var list = cfg.effects.getOrDefault(armorSetName, Collections.emptyList());
        List<MobEffectInstance> out = new ArrayList<>(list.size());
        for (EffectEntry e : list) {
            ResourceLocation id = new ResourceLocation(e.id);
            MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(id);
            if (effect != null) {
                out.add(new MobEffectInstance(effect, e.duration, e.amplifier, true, showParticles, showIcon));
            }
        }
        return out;
    }
}