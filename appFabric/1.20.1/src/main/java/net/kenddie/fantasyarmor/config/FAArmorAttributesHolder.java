package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.kenddie.fantasyarmor.item.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.ArmorItem;

import java.util.function.Supplier;

public final class FAArmorAttributesHolder {
    private static FAArmorAttributesConfig config;

    private FAArmorAttributesHolder() {}

    public static void init() {
        AutoConfig.register(FAArmorAttributesConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(FAArmorAttributesConfig.class).getConfig();
    }

    public static FAArmorAttributesConfig get() {
        return config;
    }

    public static Supplier<FAArmorAttributes> supplier(FAArmorSet set, ArmorItem.Type type) {
        return () -> {
            var cfg = AutoConfig.getConfigHolder(FAArmorAttributesConfig.class).getConfig();
            return cfg.getAttributes(set.getName(), type.getName());
        };
    }
}