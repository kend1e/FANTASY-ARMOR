package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.kenddie.fantasyarmor.item.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Supplier;

public final class FAConfigs {
    private static FAArmorEffectsConfig effectsConfig;
    private static FAConfig mainConfig;
    private static FAArmorAttributesConfig armorConfig;

    private FAConfigs() {
    }

    public static void init() {
        AutoConfig.register(FAConfig.class, GsonConfigSerializer::new);
        mainConfig = AutoConfig.getConfigHolder(FAConfig.class).getConfig();

        AutoConfig.register(FAArmorEffectsConfig.class, GsonConfigSerializer::new);
        effectsConfig = AutoConfig.getConfigHolder(FAArmorEffectsConfig.class).getConfig();

        AutoConfig.register(FAArmorAttributesConfig.class, GsonConfigSerializer::new);
        armorConfig = AutoConfig.getConfigHolder(FAArmorAttributesConfig.class).getConfig();
    }

    public static FAConfig getMainConfig() {
        return mainConfig;
    }

    public static FAArmorEffectsConfig getEffectsConfig() {
        return effectsConfig;
    }

    public static FAArmorAttributesConfig getArmorConfig() {
        return armorConfig;
    }

    public static Supplier<FAArmorAttributes> armorSupplier(FAArmorSet set, ArmorType type) {
        return () -> armorConfig.getAttributes(set.getName(), type.getName());
    }
}