package net.kenddie.fantasyarmor.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FAConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue APPLY_ARMOR_EFFECTS;
    public static final ForgeConfigSpec.BooleanValue APPLY_MODIFIERS;
    public static final ForgeConfigSpec.BooleanValue SHOW_DESCRIPTIONS;
    public static final ForgeConfigSpec.BooleanValue SHOW_CAPES;
    public static final ForgeConfigSpec.BooleanValue EPIC_FIGHT_NOT_STATIC_CAPES;

    public static boolean applyArmorEffects;
    public static boolean applyModifiers;
    public static boolean showDescriptions;
    public static boolean showCapes;
    public static boolean epicFightNotStaticCapes;

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

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void applyConfigValues() {
        applyArmorEffects = APPLY_ARMOR_EFFECTS.get();
        applyModifiers = APPLY_MODIFIERS.get();
        showDescriptions = SHOW_DESCRIPTIONS.get();
        showCapes = SHOW_CAPES.get();
        epicFightNotStaticCapes = EPIC_FIGHT_NOT_STATIC_CAPES.get();
    }
}
