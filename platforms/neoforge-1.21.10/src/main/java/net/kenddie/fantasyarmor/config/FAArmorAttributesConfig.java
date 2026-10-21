package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import static net.kenddie.fantasyarmor.shared.config.ArmorAttributeLimits.*;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.kenddie.fantasyarmor.shared.armor.FAArmorSet;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.common.ModConfigSpec;

public class FAArmorAttributesConfig {
    public final ModConfigSpec.DoubleValue armor;
    public final ModConfigSpec.DoubleValue armorToughness;
    public final ModConfigSpec.DoubleValue knockbackResistance;
    public final ModConfigSpec.DoubleValue movementSpeed;
    public final ModConfigSpec.DoubleValue maxHealth;
    public final ModConfigSpec.DoubleValue attackDamage;
    public final ModConfigSpec.DoubleValue attackSpeed;
    public final ModConfigSpec.DoubleValue luck;
    public final ModConfigSpec.DoubleValue durability;

    public FAArmorAttributesConfig(ModConfigSpec.Builder builder, FAArmorSet armorSet, ArmorType type) {
        FAArmorAttributes defaults = FAArmorDefaults.get(armorSet.getName(), type.getName());

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
        return new FAArmorAttributes(armor.get(), armorToughness.get(), knockbackResistance.get(),
                movementSpeed.get(), maxHealth.get(), attackDamage.get(), attackSpeed.get(), luck.get(), durability.get());
    }
}
