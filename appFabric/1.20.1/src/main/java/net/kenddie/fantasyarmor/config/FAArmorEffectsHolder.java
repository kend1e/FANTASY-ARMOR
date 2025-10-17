package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public final class FAArmorEffectsHolder {
    private static FAArmorEffectsConfig config;

    public static void init() {
        AutoConfig.register(FAArmorEffectsConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(FAArmorEffectsConfig.class).getConfig();
    }

    public static FAArmorEffectsConfig get() {
        return config;
    }
}
