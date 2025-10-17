package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public final class FAConfigs {
    private static FAConfig config;

    public static void init() {
        AutoConfig.register(FAConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(FAConfig.class).getConfig();
    }

    public static FAConfig get() {
        return config;
    }
}
