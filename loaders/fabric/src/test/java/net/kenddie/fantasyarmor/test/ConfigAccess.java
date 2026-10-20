package net.kenddie.fantasyarmor.test;

import net.kenddie.fantasyarmor.config.FAConfigs;

final class ConfigAccess {
    static boolean modifiers() { return FAConfigs.getMainConfig().applyModifiers; }
    static void modifiers(boolean value) { FAConfigs.getMainConfig().applyModifiers = value; }
    static boolean durability() { return FAConfigs.getMainConfig().enableDurability; }
    static void durability(boolean value) { FAConfigs.getMainConfig().enableDurability = value; }
    static boolean effects() { return FAConfigs.getMainConfig().applyArmorEffects; }
    static void effects(boolean value) { FAConfigs.getMainConfig().applyArmorEffects = value; }
    static double helmetArmor() { return FAConfigs.getArmorConfig().sets.get("eclipse_soldier").get("helmet").armor; }
    static void helmetArmor(double value) { FAConfigs.getArmorConfig().sets.get("eclipse_soldier").get("helmet").armor = value; }
    static boolean loaded() { return FAConfigs.getMainConfig() != null && FAConfigs.getArmorConfig() != null && FAConfigs.getEffectsConfig() != null; }
    private ConfigAccess() {}
}
