package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;

import java.util.function.Supplier;

public final class FAConfigs {
    private FAConfigs() {}

    public static void init() {
        AutoConfig.register(FAConfig.class, GsonConfigSerializer::new);

        AutoConfig.register(FAArmorEffectsConfig.class, GsonConfigSerializer::new);

        AutoConfig.register(FAArmorAttributesConfig.class, GsonConfigSerializer::new);
    }

    public static FAConfig getMainConfig() {
        return AutoConfig.getConfigHolder(FAConfig.class).getConfig();
    }

    public static FAArmorEffectsConfig getEffectsConfig() {
        return AutoConfig.getConfigHolder(FAArmorEffectsConfig.class).getConfig();
    }

    public static FAArmorAttributesConfig getArmorConfig() {
        return AutoConfig.getConfigHolder(FAArmorAttributesConfig.class).getConfig();
    }

    public static Supplier<FAArmorAttributes> armorSupplier(FAArmorSet set, String pieceName) {
        return () -> getArmorConfig().getAttributes(set.getName(), pieceName);
    }
}
