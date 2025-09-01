package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FAArmorEffectsConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final Map<String, FAArmorEffectsConfig> ARMOR_EFFECTS_CONFIGS = new HashMap<>();
    private static final Map<FAArmorSet, List<String>> DEFAULT_EFFECTS = Map.ofEntries(
            Map.entry(FAArmorSet.WIND_WORSHIPPER, List.of("minecraft:jump_boost,259,0")),
            Map.entry(FAArmorSet.WANDERING_WIZARD, List.of(
                    "minecraft:water_breathing,259,0",
                    "minecraft:night_vision,259,0",
                    "minecraft:fire_resistance,259,0"
            )),
            Map.entry(FAArmorSet.TWINNED, List.of(
                    "minecraft:regeneration,259,0",
                    "minecraft:haste,259,1"
            )),
            Map.entry(FAArmorSet.THIEF, List.of("minecraft:jump_boost,259,0")),
            Map.entry(FAArmorSet.SUNSET_WINGS, List.of("minecraft:jump_boost,259,0")),
            Map.entry(FAArmorSet.RONIN, List.of("minecraft:jump_boost,259,0")),
            Map.entry(FAArmorSet.SPARK_OF_DAWN, List.of(
                    "minecraft:night_vision,259,0",
                    "minecraft:fire_resistance,259,0"
            )),
            Map.entry(FAArmorSet.SILVER_KNIGHT, List.of(
                    "minecraft:luck,259,0",
                    "minecraft:strength,259,0"
            )),
            Map.entry(FAArmorSet.REDEEMER, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:fire_resistance,259,0"
            )),
            Map.entry(FAArmorSet.GRAVE_SENTINEL, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:fire_resistance,259,0"
            )),
            Map.entry(FAArmorSet.DRAGONSLAYER, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:fire_resistance,259,0"
            )),
            Map.entry(FAArmorSet.OLD_KNIGHT, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:resistance,259,0"
            )),
            Map.entry(FAArmorSet.ECLIPSE_SOLDIER, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:resistance,259,0"
            )),
            Map.entry(FAArmorSet.MALENIA, List.of("minecraft:regeneration,259,0")),
            Map.entry(FAArmorSet.HERO, List.of("minecraft:regeneration,259,0")),
            Map.entry(FAArmorSet.DEAD_GLADIATOR, List.of("minecraft:regeneration,259,0")),
            Map.entry(FAArmorSet.LADY_MARIA, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:regeneration,259,0"
            )),
            Map.entry(FAArmorSet.GOLDEN_HORNS, List.of("minecraft:haste,259,1")),
            Map.entry(FAArmorSet.GOLDEN_EXECUTION, List.of("minecraft:strength,259,0")),
            Map.entry(FAArmorSet.GILDED_HUNT, List.of(
                    "minecraft:jump_boost,259,0",
                    "minecraft:strength,259,0"
            )),
            Map.entry(FAArmorSet.FORGOTTEN_TRACE, List.of(
                    "minecraft:haste,259,0",
                    "minecraft:regeneration,259,0"
            )),
            Map.entry(FAArmorSet.FOG_GUARD, List.of(
                    "minecraft:fire_resistance,259,0",
                    "minecraft:resistance,259,0"
            )),
            Map.entry(FAArmorSet.FLESH_OF_THE_FEASTER, List.of("minecraft:resistance,259,0")),
            Map.entry(FAArmorSet.EVENING_GHOST, List.of(
                    "minecraft:regeneration,259,0",
                    "minecraft:night_vision,259,0"
            )),
            Map.entry(FAArmorSet.DARK_LORD, List.of(
                    "minecraft:strength,259,0",
                    "minecraft:night_vision,259,0",
                    "minecraft:fire_resistance,259,0"
            )),
            Map.entry(FAArmorSet.DARK_COVER, List.of(
                    "minecraft:regeneration,259,0",
                    "minecraft:strength,259,0"
            )),
            Map.entry(FAArmorSet.CRUCIBLE_KNIGHT, List.of(
                    "minecraft:fire_resistance,259,0",
                    "minecraft:regeneration,259,0"
            )),
            Map.entry(FAArmorSet.CHESS_BOARD_KNIGHT, List.of(
                    "minecraft:haste,259,0",
                    "minecraft:regeneration,259,0",
                    "minecraft:strength,259,0"
            ))
    );


    private final ModConfigSpec.ConfigValue<List<? extends String>> effectsList;

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

    public FAArmorEffectsConfig(ModConfigSpec.Builder builder, List<String> defaultEffects) {
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
            if (parts.length < 3) continue;

            ResourceLocation id = ResourceLocation.tryParse(parts[0]);
            if (id == null) continue;

            int duration;
            int amplifier;
            try {
                duration = Integer.parseInt(parts[1]);
                amplifier = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex) {
                continue;
            }

            Optional<Holder.Reference<MobEffect>> holderOpt = BuiltInRegistries.MOB_EFFECT.getHolder(id);
            if (holderOpt.isEmpty()) continue;
            Holder<MobEffect> effectHolder = holderOpt.get();

            result.add(new MobEffectInstance(effectHolder, duration, amplifier, true, FAConfig.showParticles, true));
        }
        return result;
    }
}
