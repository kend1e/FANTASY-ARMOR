package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import java.util.LinkedHashMap;
import java.util.Map;
import static net.kenddie.fantasyarmor.shared.config.ArmorAttributeLimits.*;

@Config(name = "fantasy_armor-attributes")
public class FAArmorAttributesConfig implements ConfigData {
    public Map<String, Map<String, Piece>> sets = new LinkedHashMap<>();

    public static class Piece {
        public Double armor;
        public Double armorToughness;
        public Double knockbackResistance;
        public Double movementSpeed;
        public Double maxHealth;
        public Double attackDamage;
        public Double attackSpeed;
        public Double luck;
        public Double durability;

        private void validate(FAArmorAttributes defaults) {
            armor = ARMOR.orDefault(armor, defaults.armor());
            armorToughness = ARMOR_TOUGHNESS.orDefault(armorToughness, defaults.armorToughness());
            knockbackResistance = KNOCKBACK_RESISTANCE.orDefault(knockbackResistance, defaults.knockbackResistance());
            movementSpeed = MOVEMENT_SPEED.orDefault(movementSpeed, defaults.movementSpeed());
            maxHealth = MAX_HEALTH.orDefault(maxHealth, defaults.maxHealth());
            attackDamage = ATTACK_DAMAGE.orDefault(attackDamage, defaults.attackDamage());
            attackSpeed = ATTACK_SPEED.orDefault(attackSpeed, defaults.attackSpeed());
            luck = LUCK.orDefault(luck, defaults.luck());
            durability = DURABILITY.orDefault(durability, defaults.durability());
        }

        private FAArmorAttributes toAttributes() {
            return new FAArmorAttributes(armor, armorToughness, knockbackResistance, movementSpeed, maxHealth, attackDamage, attackSpeed, luck, durability);
        }
    }

    @Override
    public void validatePostLoad() {
        if (sets == null) sets = new LinkedHashMap<>();
        FAArmorDefaults.load().forEach((set, pieces) -> {
            Map<String, Piece> configured = sets.computeIfAbsent(set, key -> new LinkedHashMap<>());
            pieces.forEach((piece, defaults) ->
                    configured.computeIfAbsent(piece, key -> new Piece()).validate(defaults));
        });
    }

    public FAArmorAttributes getAttributes(String setName, String pieceName) {
        Map<String, Piece> set = sets.get(setName);
        Piece piece = set == null ? null : set.get(pieceName);
        return piece != null ? piece.toAttributes() : FAArmorDefaults.get(setName, pieceName);
    }
}
