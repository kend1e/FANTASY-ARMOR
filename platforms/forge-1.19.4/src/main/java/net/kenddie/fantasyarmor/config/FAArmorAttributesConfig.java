package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.minecraftforge.common.ForgeConfigSpec;

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

    public FAArmorAttributesConfig(ForgeConfigSpec.Builder builder, String setName, String pieceName) {
        FAArmorAttributes defaults = FAArmorDefaults.get(setName, pieceName);

        armor = builder.defineInRange("armor", defaults.armor(), 0.0, 1000.0);
        armorToughness = builder.defineInRange("armorToughness", defaults.armorToughness(), 0.0, 1000.0);
        knockbackResistance = builder.defineInRange("knockbackResistance", defaults.knockbackResistance(), 0.0, 100.0);
        movementSpeed = builder.defineInRange("movementSpeed", defaults.movementSpeed(), 0.0, 100.0);
        maxHealth = builder.defineInRange("maxHealth", defaults.maxHealth(), 0.0, 10000.0);
        attackDamage = builder.defineInRange("attackDamage", defaults.attackDamage(), 0.0, 10000.0);
        attackSpeed = builder.defineInRange("attackSpeed", defaults.attackSpeed(), 0.0, 100.0);
        luck = builder.defineInRange("luck", defaults.luck(), 0.0, 10000.0);
        durability = builder.defineInRange("durability", defaults.durability(), 0.0, 100000.0);
    }
}
