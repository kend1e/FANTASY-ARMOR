package net.kenddie.fantasyarmor.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FAConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue APPLY_ARMOR_EFFECTS;
    public static final ForgeConfigSpec.BooleanValue APPLY_MODIFIERS;
    public static final ForgeConfigSpec.BooleanValue SHOW_PARTICLES;
    public static final ForgeConfigSpec.IntValue EFFECTS_INTERVAL;
    public static final ForgeConfigSpec.BooleanValue SHOW_EFFECT_ICON;
    public static final ForgeConfigSpec.BooleanValue ENABLE_DURABILITY;

    public static boolean applyArmorEffects;
    public static boolean applyModifiers;
    public static boolean showParticles;
    public static int effectsInterval;
    public static boolean showEffectIcon;
    public static boolean enableDurability;

    static {
        BUILDER.push("General Settings");

        APPLY_ARMOR_EFFECTS = BUILDER.worldRestart()
                .comment("Apply armor effects")
                .define("applyArmorEffects", true);

        APPLY_MODIFIERS = BUILDER.worldRestart()
                .comment("Apply attribute modifiers")
                .define("applyModifiers", true);

        SHOW_PARTICLES = BUILDER.worldRestart()
                .comment("Show effect particles")
                .define("showParticles", false);

        EFFECTS_INTERVAL = BUILDER.worldRestart()
                .comment("The minimum duration of the existing effect, after which it will be given again")
                .defineInRange("effectsInterval", 241, 1, Integer.MAX_VALUE);

        SHOW_EFFECT_ICON = BUILDER.worldRestart()
                .comment("Show effect icon or not")
                .define("showEffectIcon", true);

        ENABLE_DURABILITY = BUILDER.worldRestart()
                .comment("Enable durability on Fantasy Armor pieces. When false, armor is indestructible. Requires world restart.")
                .define("enableDurability", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void applyConfigValues() {
        applyArmorEffects = APPLY_ARMOR_EFFECTS.get();
        applyModifiers = APPLY_MODIFIERS.get();
        showParticles = SHOW_PARTICLES.get();
        effectsInterval = EFFECTS_INTERVAL.get();
        showEffectIcon = SHOW_EFFECT_ICON.get();
        enableDurability = ENABLE_DURABILITY.get();
    }
}
