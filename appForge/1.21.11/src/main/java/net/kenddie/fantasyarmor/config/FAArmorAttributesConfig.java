package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class FAArmorAttributesConfig {
    public final FAArmorAttributes defaults;
    public final ForgeConfigSpec.DoubleValue armor;
    public final ForgeConfigSpec.DoubleValue armorToughness;
    public final ForgeConfigSpec.DoubleValue knockbackResistance;
    public final ForgeConfigSpec.DoubleValue movementSpeed;
    public final ForgeConfigSpec.DoubleValue maxHealth;
    public final ForgeConfigSpec.DoubleValue attackDamage;
    public final ForgeConfigSpec.DoubleValue attackSpeed;
    public final ForgeConfigSpec.DoubleValue luck;
    public final ForgeConfigSpec.DoubleValue durability;

    private static final Map<FAArmorSet, Map<ArmorType, FAArmorAttributes>> DEFAULTS = new HashMap<>();

    public FAArmorAttributesConfig(ForgeConfigSpec.Builder builder, FAArmorSet armorSet, ArmorType type) {
        defaults = DEFAULTS.getOrDefault(armorSet, Map.of()).getOrDefault(type, getGlobalDefaults(type));

        armor = builder.worldRestart().defineInRange("armor", defaults.armor(), 0.0, 100.0);
        armorToughness = builder.worldRestart().defineInRange("armorToughness", defaults.armorToughness(), 0.0, 100.0);
        knockbackResistance = builder.worldRestart().defineInRange("knockbackResistance", defaults.knockbackResistance(), 0.0, 1.0);
        movementSpeed = builder.worldRestart().defineInRange("movementSpeed", defaults.movementSpeed(), -1.0, 1.0);
        maxHealth = builder.worldRestart().defineInRange("maxHealth", defaults.maxHealth(), 0.0, 100.0);
        attackDamage = builder.worldRestart().defineInRange("attackDamage", defaults.attackDamage(), 0.0, 100.0);
        attackSpeed = builder.worldRestart().defineInRange("attackSpeed", defaults.attackSpeed(), -1.0, 1.0);
        luck = builder.worldRestart().defineInRange("luck", defaults.luck(), -100.0, 100.0);
        durability = builder.worldRestart().defineInRange("durability", defaults.durability(), 0.0, 10000.0);
    }

    public FAArmorAttributes read() {
        return new FAArmorAttributes.Builder()
                .armor(armor.get()).armorToughness(armorToughness.get())
                .knockbackResistance(knockbackResistance.get()).movementSpeed(movementSpeed.get())
                .maxHealth(maxHealth.get()).attackDamage(attackDamage.get())
                .attackSpeed(attackSpeed.get()).luck(luck.get()).durability(durability.get()).build();
    }

    private FAArmorAttributes getGlobalDefaults(ArmorType type) {
        return FAArmorDefaults.fallback(type.getName());
    }

    static {
        Map<String, Map<String, FAArmorAttributes>> allDefaults = FAArmorDefaults.load();
        for (FAArmorSet set : FAArmorSet.values()) {
            String setKey = set.getName();
            Map<String, FAArmorAttributes> setDefaults = allDefaults.get(setKey);
            if (setDefaults == null) continue;
            Map<ArmorType, FAArmorAttributes> pieceMap = new HashMap<>();
            for (ArmorType type : ArmorType.values()) {
                String pieceKey = type.getName();
                FAArmorAttributes attrs = setDefaults.get(pieceKey);
                if (attrs != null) pieceMap.put(type, attrs);
            }
            DEFAULTS.put(set, pieceMap);
        }
    }
}
