package net.kenddie.fantasyarmor.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FAConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue APPLY_ARMOR_EFFECTS;
    public static final ModConfigSpec.BooleanValue APPLY_MODIFIERS;
    public static final ModConfigSpec.BooleanValue SHOW_DESCRIPTIONS;
    public static final ModConfigSpec.BooleanValue SHOW_CAPES;
    public static final ModConfigSpec.BooleanValue EPIC_FIGHT_NOT_STATIC_CAPES;
    public static final ModConfigSpec.BooleanValue SHOW_PARTICLES;
    public static final ModConfigSpec.IntValue EFFECTS_INTERVAL;
    public static final ModConfigSpec.BooleanValue SHOW_EFFECT_ICON;

    public static boolean applyArmorEffects;
    public static boolean applyModifiers;
    public static boolean showDescriptions;
    public static boolean showCapes;
    public static boolean epicFightNotStaticCapes;
    public static boolean showParticles;
    public static int effectsInterval;
    public static boolean showEffectIcon;

    static {
        BUILDER.push("General Settings");

        APPLY_ARMOR_EFFECTS = BUILDER
                .comment("Apply armor effects")
                .define("applyArmorEffects", true);

        APPLY_MODIFIERS = BUILDER
                .comment("Apply attribute modifiers")
                .define("applyModifiers", true);

        SHOW_DESCRIPTIONS = BUILDER
                .comment("Show item descriptions")
                .define("showDescriptions", true);

        SHOW_CAPES = BUILDER
                .comment("Render custom capes attached to chestplates")
                .define("showCapes", true);

        EPIC_FIGHT_NOT_STATIC_CAPES = BUILDER
                .comment("Do not make capes static if Epic Fight is loaded. It is not implemented yet completely")
                .define("epicFightNotStaticCapes", false);

        SHOW_PARTICLES = BUILDER
                .comment("Show effect particles")
                .define("showParticles", false);

        EFFECTS_INTERVAL = BUILDER
                .comment("The minimum duration of the existing effect, after which it will be given again")
                .defineInRange("effectsInterval", 241, 1, Integer.MAX_VALUE);

        SHOW_EFFECT_ICON = BUILDER
                .comment("Show effect icon or not")
                .define("showEffectIcon", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void applyConfigValues() {
        applyArmorEffects = APPLY_ARMOR_EFFECTS.get();
        applyModifiers = APPLY_MODIFIERS.get();
        showDescriptions = SHOW_DESCRIPTIONS.get();
        showCapes = SHOW_CAPES.get();
        epicFightNotStaticCapes = EPIC_FIGHT_NOT_STATIC_CAPES.get();
        showParticles = SHOW_PARTICLES.get();
        effectsInterval = EFFECTS_INTERVAL.get();
        showEffectIcon = SHOW_EFFECT_ICON.get();
    }
}
