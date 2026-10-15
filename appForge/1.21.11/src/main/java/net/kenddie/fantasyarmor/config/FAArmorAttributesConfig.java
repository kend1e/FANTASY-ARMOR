package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class FAArmorAttributesConfig {
    public final ForgeConfigSpec.DoubleValue armor;
    public final ForgeConfigSpec.DoubleValue armorToughness;
    public final ForgeConfigSpec.DoubleValue knockbackResistance;
    public final ForgeConfigSpec.DoubleValue movementSpeed;
    public final ForgeConfigSpec.DoubleValue maxHealth;
    public final ForgeConfigSpec.DoubleValue attackDamage;
    public final ForgeConfigSpec.DoubleValue attackSpeed;
    public final ForgeConfigSpec.DoubleValue luck;
    public final ForgeConfigSpec.DoubleValue durability;

    private static final Map<FAArmorSet, Map<ArmorItem.Type, FAArmorAttributes>> DEFAULTS = new HashMap<>();

    public FAArmorAttributesConfig(ForgeConfigSpec.Builder builder, FAArmorSet armorSet, ArmorItem.Type type) {
        FAArmorAttributes defaults = DEFAULTS.getOrDefault(armorSet, Map.of()).getOrDefault(type, getGlobalDefaults(type));

        armor = builder.defineInRange("armor", defaults.armor(), 0.0, 100.0);
        armorToughness = builder.defineInRange("armorToughness", defaults.armorToughness(), 0.0, 100.0);
        knockbackResistance = builder.defineInRange("knockbackResistance", defaults.knockbackResistance(), 0.0, 1.0);
        movementSpeed = builder.defineInRange("movementSpeed", defaults.movementSpeed(), -1.0, 1.0);
        maxHealth = builder.defineInRange("maxHealth", defaults.maxHealth(), 0.0, 100.0);
        attackDamage = builder.defineInRange("attackDamage", defaults.attackDamage(), 0.0, 100.0);
        attackSpeed = builder.defineInRange("attackSpeed", defaults.attackSpeed(), -1.0, 1.0);
        luck = builder.defineInRange("luck", defaults.luck(), -100.0, 100.0);
        durability = builder.defineInRange("durability", defaults.durability(), 0.0, 10000.0);
    }

    private FAArmorAttributes getGlobalDefaults(ArmorItem.Type type) {
        return FAArmorDefaults.fallback(type.name().toLowerCase());
    }

    static {
        Map<String, Map<String, FAArmorAttributes>> allDefaults = FAArmorDefaults.load();
        for (FAArmorSet set : FAArmorSet.values()) {
            String setKey = set.name().toLowerCase();
            Map<String, FAArmorAttributes> setDefaults = allDefaults.get(setKey);
            if (setDefaults == null) continue;
            Map<ArmorItem.Type, FAArmorAttributes> pieceMap = new HashMap<>();
            for (ArmorItem.Type type : ArmorItem.Type.values()) {
                String pieceKey = type.name().toLowerCase();
                FAArmorAttributes attrs = setDefaults.get(pieceKey);
                if (attrs != null) pieceMap.put(type, attrs);
            }
            DEFAULTS.put(set, pieceMap);
        }
    }
}
