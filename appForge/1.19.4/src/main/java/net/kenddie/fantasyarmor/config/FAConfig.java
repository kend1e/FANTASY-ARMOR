package net.kenddie.fantasyarmor.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FAConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue APPLY_ARMOR_EFFECTS;
    public static final ForgeConfigSpec.BooleanValue APPLY_MODIFIERS;
    public static final ForgeConfigSpec.BooleanValue SHOW_DESCRIPTIONS;

    public static boolean applyArmorEffects;
    public static boolean applyModifiers;
    public static boolean showDescriptions;

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

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void applyConfigValues() {
        applyArmorEffects = APPLY_ARMOR_EFFECTS.get();
        applyModifiers = APPLY_MODIFIERS.get();
        showDescriptions = SHOW_DESCRIPTIONS.get();
    }
}
