package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.shared.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.shared.config.FAArmorDefaults;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Config(name = "fantasy_armor-attributes")
public class FAArmorAttributesConfig implements ConfigData {
    @ConfigEntry.Category("armor_attributes")
    @ConfigEntry.Gui.CollapsibleObject
    public Map<String, PerSet> bySet = new LinkedHashMap<>();

    public static class PerSet {
        @ConfigEntry.Gui.CollapsibleObject
        public Map<String, Piece> byPiece = new LinkedHashMap<>();
    }

    public static class Piece {
        public double armor = 0.0;
        public double armorToughness = 0.0;
        public double knockbackResistance = 0.0; // 0..1
        public double movementSpeed = 0.0;       // -1..1
        public double maxHealth = 0.0;
        public double attackDamage = 0.0;
        public double attackSpeed = 0.0;         // -1..1
        public double luck = 0.0;                // -100..100
        public double durability = 0.0;              // 0..10000

        public FAArmorAttributes toAttributes() {
            return new FAArmorAttributes.Builder()
                    .armor(armor)
                    .armorToughness(armorToughness)
                    .knockbackResistance(knockbackResistance)
                    .movementSpeed(movementSpeed)
                    .maxHealth(maxHealth)
                    .attackDamage(attackDamage)
                    .attackSpeed(attackSpeed)
                    .luck(luck)
                    .durability(durability)
                    .build();
        }
    }

    private static final Map<FAArmorSet, Map<ArmorType, FAArmorAttributes>> DEFAULTS = new EnumMap<>(FAArmorSet.class);

    static {
        Map<String, Map<String, FAArmorAttributes>> allDefaults = FAArmorDefaults.load();
        for (FAArmorSet set : FAArmorSet.values()) {
            String setKey = set.name().toLowerCase();
            Map<String, FAArmorAttributes> setDefaults = allDefaults.get(setKey);
            if (setDefaults == null) continue;
            Map<ArmorType, FAArmorAttributes> pieceMap = new HashMap<>();
            for (ArmorType type : ArmorType.values()) {
                String pieceKey = type.name().toLowerCase();
                FAArmorAttributes attrs = setDefaults.get(pieceKey);
                if (attrs != null) pieceMap.put(type, attrs);
            }
            DEFAULTS.put(set, pieceMap);
        }
    }

    @Override
    public void validatePostLoad() throws ValidationException {
        if (bySet == null) bySet = new LinkedHashMap<>();
        for (FAArmorSet set : FAArmorSet.values()) {
            String setKey = set.getName(); // lower-case
            PerSet perSet = bySet.computeIfAbsent(setKey, k -> new PerSet());
            if (perSet.byPiece == null) perSet.byPiece = new LinkedHashMap<>();

            for (ArmorType type : FAArmorItems.VALID_ARMOR_TYPES) {
                String pieceKey = type.getName();
                perSet.byPiece.computeIfAbsent(pieceKey, pk -> {
                    FAArmorAttributes a = DEFAULTS.get(set).get(type);
                    Piece p = new Piece();
                    p.armor = a.armor();
                    p.armorToughness = a.armorToughness();
                    p.knockbackResistance = a.knockbackResistance();
                    p.movementSpeed = a.movementSpeed();
                    p.maxHealth = a.maxHealth();
                    p.attackDamage = a.attackDamage();
                    p.attackSpeed = a.attackSpeed();
                    p.luck = a.luck();
                    p.durability = a.durability();
                    return p;
                });
            }
        }
    }

    public FAArmorAttributes getAttributes(String setName, String pieceName) {
        PerSet ps = bySet.get(setName);
        if (ps == null) return DEFAULTS.getOrDefault(resolveSet(setName), Map.of()).getOrDefault(resolveType(pieceName), fallback(resolveType(pieceName)));
        Piece piece = ps.byPiece.get(pieceName);
        if (piece == null) return DEFAULTS.getOrDefault(resolveSet(setName), Map.of()).getOrDefault(resolveType(pieceName), fallback(resolveType(pieceName)));
        return piece.toAttributes();
    }

    private static FAArmorSet resolveSet(String name) {
        for (FAArmorSet s : FAArmorSet.values()) if (s.getName().equals(name)) return s;
        return FAArmorSet.HERO;
    }

    private static ArmorType resolveType(String piece) {
        for (ArmorType t : ArmorType.values()) if (t.getName().equals(piece)) return t;
        return ArmorType.HELMET;
    }

    private static FAArmorAttributes fallback(ArmorType t) {
        return FAArmorDefaults.fallback(t.getName());
    }
}
