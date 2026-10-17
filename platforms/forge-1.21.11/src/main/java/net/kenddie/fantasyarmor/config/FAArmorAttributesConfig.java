package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import static net.kenddie.fantasyarmor.shared.config.ArmorAttributeLimits.*;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;
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

        armor = builder.worldRestart().defineInRange("armor", defaults.armor(), ARMOR.min(), ARMOR.max());
        armorToughness = builder.worldRestart().defineInRange("armorToughness", defaults.armorToughness(), ARMOR_TOUGHNESS.min(), ARMOR_TOUGHNESS.max());
        knockbackResistance = builder.worldRestart().defineInRange("knockbackResistance", defaults.knockbackResistance(), KNOCKBACK_RESISTANCE.min(), KNOCKBACK_RESISTANCE.max());
        movementSpeed = builder.worldRestart().defineInRange("movementSpeed", defaults.movementSpeed(), MOVEMENT_SPEED.min(), MOVEMENT_SPEED.max());
        maxHealth = builder.worldRestart().defineInRange("maxHealth", defaults.maxHealth(), MAX_HEALTH.min(), MAX_HEALTH.max());
        attackDamage = builder.worldRestart().defineInRange("attackDamage", defaults.attackDamage(), ATTACK_DAMAGE.min(), ATTACK_DAMAGE.max());
        attackSpeed = builder.worldRestart().defineInRange("attackSpeed", defaults.attackSpeed(), ATTACK_SPEED.min(), ATTACK_SPEED.max());
        luck = builder.worldRestart().defineInRange("luck", defaults.luck(), LUCK.min(), LUCK.max());
        durability = builder.worldRestart().defineInRange("durability", defaults.durability(), DURABILITY.min(), DURABILITY.max());
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
            for (ArmorType type : java.util.List.of(ArmorType.HELMET, ArmorType.CHESTPLATE, ArmorType.LEGGINGS, ArmorType.BOOTS)) {
                String pieceKey = type.getName();
                FAArmorAttributes attrs = setDefaults.get(pieceKey);
                if (attrs != null) pieceMap.put(type, attrs);
            }
            DEFAULTS.put(set, pieceMap);
        }
    }
}
