package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import static net.kenddie.fantasyarmor.shared.config.ArmorAttributeLimits.*;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.minecraftforge.common.ForgeConfigSpec;

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

    public FAArmorAttributesConfig(ForgeConfigSpec.Builder builder, String setName, String pieceName) {
        defaults = FAArmorDefaults.get(setName, pieceName);

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
        if (!FAArmorConfig.SPEC.isLoaded()) return defaults;
        return new FAArmorAttributes(
                armor.get(), armorToughness.get(), knockbackResistance.get(),
                movementSpeed.get(), maxHealth.get(), attackDamage.get(),
                attackSpeed.get(), luck.get(), durability.get());
    }
}
