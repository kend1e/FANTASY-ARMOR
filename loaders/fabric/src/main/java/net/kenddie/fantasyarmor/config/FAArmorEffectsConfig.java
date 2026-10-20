package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.kenddie.fantasyarmor.shared.config.FAEffectDefaults;

import java.util.ArrayList;
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
        for (var entry : FAEffectDefaults.load().entrySet()) {
            List<EffectEntry> local = new ArrayList<>();
            for (var e : entry.getValue()) {
                local.add(new EffectEntry(e.id(), e.duration(), e.amplifier()));
            }
            map.put(entry.getKey(), local);
        }
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

    public static List<net.kenddie.fantasyarmor.shared.config.EffectEntry> getEffectsFor(String armorSetName) {
        var list = FAConfigs.getEffectsConfig().effects.get(armorSetName);
        if (list == null) return List.of();
        List<net.kenddie.fantasyarmor.shared.config.EffectEntry> result = new ArrayList<>(list.size());
        for (EffectEntry entry : list) {
            if (entry != null) net.kenddie.fantasyarmor.shared.config.EffectEntry
                    .validated(entry.id, entry.duration, entry.amplifier).ifPresent(result::add);
        }
        return result;
    }
}
