package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.kenddie.fantasyarmor.shared.config.FAEffectDefaults;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FAArmorEffectsConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final Map<String, FAArmorEffectsConfig> ARMOR_EFFECTS_CONFIGS = new HashMap<>();
    private static final Map<FAArmorSet, List<String>> DEFAULT_EFFECTS = buildDefaultEffects();

    private static Map<FAArmorSet, List<String>> buildDefaultEffects() {
        Map<FAArmorSet, List<String>> result = new HashMap<>();
        for (FAArmorSet set : FAArmorSet.values()) {
            var entries = FAEffectDefaults.get(set.getName());
            if (!entries.isEmpty()) {
                result.put(set, entries.stream()
                    .map(e -> e.id() + "," + e.duration() + "," + e.amplifier())
                    .toList());
            }
        }
        return Map.copyOf(result);
    }

    private final ForgeConfigSpec.ConfigValue<List<? extends String>> effectsList;

    static {
        BUILDER.push("Armor Effects");

        for (FAArmorSet armorSet : FAArmorSet.values()) {
            BUILDER.push(armorSet.getName());

            List<String> defaults = DEFAULT_EFFECTS.getOrDefault(armorSet, Collections.emptyList());

            FAArmorEffectsConfig effectsConfig = new FAArmorEffectsConfig(BUILDER, defaults);
            ARMOR_EFFECTS_CONFIGS.put(armorSet.getName(), effectsConfig);

            BUILDER.pop();
        }

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public FAArmorEffectsConfig(ForgeConfigSpec.Builder builder, List<String> defaultEffects) {
        effectsList = builder.defineList(
                "effects",
                defaultEffects,
                o -> o instanceof String
        );
    }

    public List<MobEffectInstance> getEffects() {
        List<MobEffectInstance> result = new ArrayList<>();
        for (String entry : effectsList.get()) {
            String[] parts = entry.split(",");
            if (parts.length >= 3) {
                ResourceLocation id = new ResourceLocation(parts[0]);
                MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(id);
                if (effect != null) {
                    int duration = Integer.parseInt(parts[1]);
                    int amplifier = Integer.parseInt(parts[2]);
                    result.add(new MobEffectInstance(effect, duration, amplifier, true, FAConfig.showParticles, true));
                }
            }
        }
        return result;
    }
}
