package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class FAArmorConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final Map<String, Map<String, FAArmorAttributesConfig>> ARMOR_CONFIGS = new HashMap<>();

    static {
        BUILDER.push("Armor Attributes");

        for (FAArmorSet armorSet : FAArmorSet.values()) {
            BUILDER.push(armorSet.getName());
            Map<String, FAArmorAttributesConfig> pieces = new HashMap<>();

            for (String piece : java.util.List.of("helmet", "chestplate", "leggings", "boots")) {
                BUILDER.push(piece);
                FAArmorAttributesConfig attributesConfig = new FAArmorAttributesConfig(BUILDER, armorSet.getName(), piece);
                pieces.put(piece, attributesConfig);
                BUILDER.pop();
            }

            ARMOR_CONFIGS.put(armorSet.getName(), pieces);
            BUILDER.pop();
        }

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
