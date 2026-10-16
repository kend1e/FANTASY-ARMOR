package net.kenddie.fantasyarmor.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FAConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT_SPEC;

    public static final ForgeConfigSpec.BooleanValue APPLY_ARMOR_EFFECTS;
    public static final ForgeConfigSpec.BooleanValue APPLY_MODIFIERS;
    public static final ForgeConfigSpec.BooleanValue SHOW_DESCRIPTIONS;
    public static final ForgeConfigSpec.BooleanValue SHOW_CAPES;
    public static final ForgeConfigSpec.BooleanValue EPIC_FIGHT_NOT_STATIC_CAPES;
    public static final ForgeConfigSpec.BooleanValue SHOW_PARTICLES;
    public static final ForgeConfigSpec.IntValue EFFECTS_INTERVAL;
    public static final ForgeConfigSpec.BooleanValue SHOW_EFFECT_ICON;
    public static final ForgeConfigSpec.BooleanValue ENABLE_DURABILITY;

    public static boolean applyArmorEffects = true;
    public static boolean applyModifiers = true;
    public static boolean showDescriptions = true;
    public static boolean showCapes = true;
    public static boolean epicFightNotStaticCapes;
    public static boolean showParticles;
    public static int effectsInterval = 241;
    public static boolean showEffectIcon = true;
    public static boolean enableDurability;

    static {
        BUILDER.push("General Settings");

        APPLY_ARMOR_EFFECTS = BUILDER
                .comment("Apply armor effects")
                .define("applyArmorEffects", true);

        APPLY_MODIFIERS = BUILDER
                .comment("Apply attribute modifiers")
                .define("applyModifiers", true);

        SHOW_DESCRIPTIONS = CLIENT_BUILDER
                .comment("Show item descriptions")
                .define("showDescriptions", true);

        SHOW_CAPES = CLIENT_BUILDER
                .comment("Render custom capes attached to chestplates")
                .define("showCapes", true);

        EPIC_FIGHT_NOT_STATIC_CAPES = CLIENT_BUILDER
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

        ENABLE_DURABILITY = BUILDER
                .comment("Enable durability on Fantasy Armor pieces. When false, armor is indestructible. Requires world restart.")
                .define("enableDurability", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
        CLIENT_SPEC = CLIENT_BUILDER.build();
    }

    public static void applyClientValues() {
        showDescriptions = SHOW_DESCRIPTIONS.get();
        showCapes = SHOW_CAPES.get();
        epicFightNotStaticCapes = EPIC_FIGHT_NOT_STATIC_CAPES.get();
    }

    public static void applyConfigValues() {
        applyArmorEffects = APPLY_ARMOR_EFFECTS.get();
        applyModifiers = APPLY_MODIFIERS.get();
        showParticles = SHOW_PARTICLES.get();
        effectsInterval = EFFECTS_INTERVAL.get();
        showEffectIcon = SHOW_EFFECT_ICON.get();
        enableDurability = ENABLE_DURABILITY.get();
    }
    public static void resetServerValues() {
        applyArmorEffects = APPLY_ARMOR_EFFECTS.getDefault();
        applyModifiers = APPLY_MODIFIERS.getDefault();
        showParticles = SHOW_PARTICLES.getDefault();
        effectsInterval = EFFECTS_INTERVAL.getDefault();
        showEffectIcon = SHOW_EFFECT_ICON.getDefault();
        enableDurability = ENABLE_DURABILITY.getDefault();
    }
}
