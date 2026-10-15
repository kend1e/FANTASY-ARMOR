package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.kenddie.fantasyarmor.shared.config.FAEffectDefaults;
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