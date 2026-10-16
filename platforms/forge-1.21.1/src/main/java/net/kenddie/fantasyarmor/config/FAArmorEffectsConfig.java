package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;
import net.kenddie.fantasyarmor.shared.config.FAEffectDefaults;
import net.kenddie.fantasyarmor.shared.config.EffectEntry;
import net.minecraft.core.Holder;
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
import java.util.Optional;

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
                    .map(EffectEntry::toConfigValue)
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
        effectsList = builder.worldRestart().defineListAllowEmpty(
                List.of("effects"), () -> defaultEffects, EffectEntry::isValidConfigValue);
    }

    public List<MobEffectInstance> getEffects() {
        List<MobEffectInstance> result = new ArrayList<>();
        for (String entry : SPEC.isLoaded() ? effectsList.get() : effectsList.getDefault()) {
            var parsed = EffectEntry.parse(entry);
            if (parsed.isEmpty()) continue;
            var effectEntry = parsed.get();
            var holder = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.tryParse(effectEntry.id()));
            if (holder.isEmpty()) continue;
            result.add(new MobEffectInstance(holder.get(), effectEntry.duration(), effectEntry.amplifier(),
                    true, FAConfig.showParticles, FAConfig.showEffectIcon));
        }
        return result;
    }
}
