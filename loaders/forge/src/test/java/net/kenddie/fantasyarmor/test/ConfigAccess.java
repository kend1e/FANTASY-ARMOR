package net.kenddie.fantasyarmor.test;

import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.config.FAArmorConfig;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;

final class ConfigAccess {
    static boolean modifiers() { return FAConfig.applyModifiers(); }
    static void modifiers(boolean value) { FAConfig.APPLY_MODIFIERS.set(value); }
    static boolean durability() { return FAConfig.enableDurability(); }
    static void durability(boolean value) { FAConfig.ENABLE_DURABILITY.set(value); }
    static boolean effects() { return FAConfig.applyArmorEffects(); }
    static void effects(boolean value) { FAConfig.APPLY_ARMOR_EFFECTS.set(value); }
    static double helmetArmor() { return FAArmorConfig.ARMOR_CONFIGS.get("eclipse_soldier").get("helmet").armor.get(); }
    static void helmetArmor(double value) { FAArmorConfig.ARMOR_CONFIGS.get("eclipse_soldier").get("helmet").armor.set(value); }
    static boolean loaded() { return FAConfig.SPEC.isLoaded() && FAArmorConfig.SPEC.isLoaded() && FAArmorEffectsConfig.SPEC.isLoaded(); }
    private ConfigAccess() {}
}
