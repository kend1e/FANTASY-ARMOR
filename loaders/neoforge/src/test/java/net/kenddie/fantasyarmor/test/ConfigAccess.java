package net.kenddie.fantasyarmor.test;

import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.config.FAArmorConfig;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.common.ModConfigSpec;

final class ConfigAccess {
    static void withSettings(Runnable scenario) { scenario.run(); }
    static boolean modifiers() { return FAConfig.applyModifiers; }
    static void modifiers(boolean value) { mainSetting(FAConfig.APPLY_MODIFIERS, value); }
    static boolean durability() { return FAConfig.enableDurability; }
    static void durability(boolean value) { mainSetting(FAConfig.ENABLE_DURABILITY, value); }
    static boolean effects() { return FAConfig.applyArmorEffects; }
    static void effects(boolean value) { mainSetting(FAConfig.APPLY_ARMOR_EFFECTS, value); }
    static double helmetArmor() { return helmetSetting().get(); }
    static void helmetArmor(double value) { fixture(helmetSetting(), value); }

    static boolean loaded() {
        return FAConfig.SPEC.isLoaded() && FAArmorConfig.SPEC.isLoaded() && FAArmorEffectsConfig.SPEC.isLoaded();
    }

    static void verifyRestartPolicy(GameTestHelper helper) {
        var setting = FAConfig.ENABLE_DURABILITY;
        boolean active = setting.get();
        boolean configured = setting.getRaw();
        try {
            setting.set(!active);
            TestSupport.check(helper, setting.get() == active, "WORLD_RESTART must keep active value until restart");
        } finally {
            setting.set(configured);
        }
    }

    private static ModConfigSpec.DoubleValue helmetSetting() {
        return FAArmorConfig.ARMOR_CONFIGS.get("eclipse_soldier").get("helmet").armor;
    }

    private static void mainSetting(ModConfigSpec.BooleanValue setting, boolean value) {
        fixture(setting, value);
        FAConfig.applyConfigValues();
    }

    private static <T> void fixture(ModConfigSpec.ConfigValue<T> setting, T value) {
        setting.set(value);
        // Bypass WORLD_RESTART caching for this fixture.
        setting.clearCache();
    }

    private ConfigAccess() {}
}
