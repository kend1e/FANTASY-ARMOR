package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.item.armor.FAArmorAttributes;
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
                    .build();
        }
    }

    private static final Map<FAArmorSet, Map<ArmorType, FAArmorAttributes>> DEFAULTS = new EnumMap<>(FAArmorSet.class);

    static {
// ECLIPSE_SOLDIER
        Map<ArmorType, FAArmorAttributes> eclipseSoldier = new HashMap<>();
        eclipseSoldier.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.1)
                .build());
        eclipseSoldier.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        eclipseSoldier.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        eclipseSoldier.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.ECLIPSE_SOLDIER, eclipseSoldier);

// DRAGONSLAYER
        Map<ArmorType, FAArmorAttributes> dragonslayer = new HashMap<>();
        dragonslayer.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        dragonslayer.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.1)
                .build());
        dragonslayer.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        dragonslayer.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.DRAGONSLAYER, dragonslayer);

// HERO
        Map<ArmorType, FAArmorAttributes> hero = new HashMap<>();
        hero.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackDamage(0.1)
                .attackSpeed(0.1)
                .build());
        hero.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackDamage(0.1)
                .attackSpeed(0.1)
                .build());
        hero.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        hero.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.HERO, hero);

// GOLDEN_HORNS
        Map<ArmorType, FAArmorAttributes> goldenHorns = new HashMap<>();
        goldenHorns.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .attackSpeed(0.1)
                .build());
        goldenHorns.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.01)
                .attackSpeed(0.1)
                .build());
        goldenHorns.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.02)
                .build());
        goldenHorns.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.04)
                .build());
        DEFAULTS.put(FAArmorSet.GOLDEN_HORNS, goldenHorns);

// THIEF
        Map<ArmorType, FAArmorAttributes> thief = new HashMap<>();
        thief.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .attackSpeed(0.1)
                .luck(1.0)
                .build());
        thief.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .attackSpeed(0.1)
                .build());
        thief.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.1)
                .attackSpeed(0.1)
                .build());
        thief.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.1)
                .attackSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.THIEF, thief);

// WANDERING_WIZARD
        Map<ArmorType, FAArmorAttributes> wanderingWizard = new HashMap<>();
        wanderingWizard.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .luck(2.0)
                .build());
        wanderingWizard.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        wanderingWizard.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        wanderingWizard.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.WANDERING_WIZARD, wanderingWizard);

// CHESS_BOARD_KNIGHT
        Map<ArmorType, FAArmorAttributes> chessBoardKnight = new HashMap<>();
        chessBoardKnight.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        chessBoardKnight.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        chessBoardKnight.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        chessBoardKnight.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        DEFAULTS.put(FAArmorSet.CHESS_BOARD_KNIGHT, chessBoardKnight);

// DARK_LORD
        Map<ArmorType, FAArmorAttributes> darkLord = new HashMap<>();
        darkLord.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        darkLord.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        darkLord.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        darkLord.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        DEFAULTS.put(FAArmorSet.DARK_LORD, darkLord);

// SUNSET_WINGS
        Map<ArmorType, FAArmorAttributes> sunsetWings = new HashMap<>();
        sunsetWings.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        sunsetWings.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        sunsetWings.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        sunsetWings.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.SUNSET_WINGS, sunsetWings);

// FOG_GUARD
        Map<ArmorType, FAArmorAttributes> fogGuard = new HashMap<>();
        fogGuard.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        fogGuard.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.01)
                .build());
        fogGuard.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.02)
                .build());
        fogGuard.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.FOG_GUARD, fogGuard);

// DARK_COVER
        Map<ArmorType, FAArmorAttributes> darkCover = new HashMap<>();
        darkCover.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(2.0)
                .build());
        darkCover.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.1)
                .build());
        darkCover.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        darkCover.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.DARK_COVER, darkCover);

// SPARK_OF_DAWN
        Map<ArmorType, FAArmorAttributes> sparkOfDawn = new HashMap<>();
        sparkOfDawn.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        sparkOfDawn.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackSpeed(0.05)
                .build());
        sparkOfDawn.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .attackSpeed(0.05)
                .build());
        sparkOfDawn.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.SPARK_OF_DAWN, sparkOfDawn);

// GOLDEN_EXECUTION
        Map<ArmorType, FAArmorAttributes> goldenExecution = new HashMap<>();
        goldenExecution.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .attackSpeed(0.05)
                .build());
        goldenExecution.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(4.0)
                .attackSpeed(0.05)
                .build());
        goldenExecution.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        goldenExecution.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.GOLDEN_EXECUTION, goldenExecution);

// FORGOTTEN_TRACE
        Map<ArmorType, FAArmorAttributes> forgottenTrace = new HashMap<>();
        forgottenTrace.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .luck(1.0)
                .build());
        forgottenTrace.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .maxHealth(2.0)
                .build());
        forgottenTrace.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.02)
                .maxHealth(1.0)
                .build());
        forgottenTrace.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .luck(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.FORGOTTEN_TRACE, forgottenTrace);

// REDEEMER
        Map<ArmorType, FAArmorAttributes> redeemer = new HashMap<>();
        redeemer.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(2.0)
                .attackSpeed(0.05)
                .luck(1.0)
                .build());
        redeemer.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(2.0)
                .attackSpeed(0.03)
                .movementSpeed(0.02)
                .luck(1.0)
                .build());
        redeemer.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .movementSpeed(0.04)
                .build());
        redeemer.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.02)
                .build());
        DEFAULTS.put(FAArmorSet.REDEEMER, redeemer);

// TWINNED
        Map<ArmorType, FAArmorAttributes> twinned = new HashMap<>();
        twinned.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.1)
                .movementSpeed(0.02)
                .build());
        twinned.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .movementSpeed(0.02)
                .build());
        twinned.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.05)
                .movementSpeed(0.02)
                .build());
        twinned.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.05)
                .movementSpeed(0.02)
                .build());
        DEFAULTS.put(FAArmorSet.TWINNED, twinned);

// GILDED_HUNT
        Map<ArmorType, FAArmorAttributes> gildedHunt = new HashMap<>();
        gildedHunt.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        gildedHunt.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        gildedHunt.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        gildedHunt.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.GILDED_HUNT, gildedHunt);

// LADY_MARIA
        Map<ArmorType, FAArmorAttributes> ladyMaria = new HashMap<>();
        ladyMaria.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.05)
                .luck(1.0)
                .build());
        ladyMaria.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .attackSpeed(0.05)
                .build());
        ladyMaria.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        ladyMaria.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.LADY_MARIA, ladyMaria);

// CRUCIBLE_KNIGHT
        Map<ArmorType, FAArmorAttributes> crucibleKnight = new HashMap<>();
        crucibleKnight.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .build());
        crucibleKnight.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        crucibleKnight.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .build());
        crucibleKnight.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.CRUCIBLE_KNIGHT, crucibleKnight);

// EVENING_GHOST
        Map<ArmorType, FAArmorAttributes> eveningGhost = new HashMap<>();
        eveningGhost.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .luck(1.0)
                .build());
        eveningGhost.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .luck(1.0)
                .build());
        eveningGhost.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.04)
                .build());
        eveningGhost.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.EVENING_GHOST, eveningGhost);

// RONIN
        Map<ArmorType, FAArmorAttributes> ronin = new HashMap<>();
        ronin.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.1)
                .build());
        ronin.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.1)
                .build());
        ronin.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .build());
        ronin.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.RONIN, ronin);

// MALENIA
        Map<ArmorType, FAArmorAttributes> malenia = new HashMap<>();
        malenia.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.07)
                .attackSpeed(0.05)
                .build());
        malenia.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .movementSpeed(0.05)
                .attackSpeed(0.05)
                .build());
        malenia.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        malenia.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.08)
                .build());
        DEFAULTS.put(FAArmorSet.MALENIA, malenia);

// OLD_KNIGHT
        Map<ArmorType, FAArmorAttributes> oldKnight = new HashMap<>();
        oldKnight.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        oldKnight.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        oldKnight.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        oldKnight.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.OLD_KNIGHT, oldKnight);

// SILVER_KNIGHT
        Map<ArmorType, FAArmorAttributes> silverKnight = new HashMap<>();
        silverKnight.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .luck(1.0)
                .attackSpeed(0.03)
                .build());
        silverKnight.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .luck(1.0)
                .attackSpeed(0.03)
                .build());
        silverKnight.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        silverKnight.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .luck(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.SILVER_KNIGHT, silverKnight);

// DEAD_GLADIATOR
        Map<ArmorType, FAArmorAttributes> deadGladiator = new HashMap<>();
        deadGladiator.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackDamage(0.05)
                .build());
        deadGladiator.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .attackDamage(0.1)
                .build());
        deadGladiator.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackDamage(0.05)
                .build());
        deadGladiator.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.DEAD_GLADIATOR, deadGladiator);

// FLESH_OF_THE_FEASTER
        Map<ArmorType, FAArmorAttributes> fleshOfTheFeaster = new HashMap<>();
        fleshOfTheFeaster.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(1.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .build());
        fleshOfTheFeaster.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(1.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        fleshOfTheFeaster.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(1.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .build());
        fleshOfTheFeaster.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(1.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.FLESH_OF_THE_FEASTER, fleshOfTheFeaster);

// WIND_WORSHIPPER
        Map<ArmorType, FAArmorAttributes> windWorshipper = new HashMap<>();
        windWorshipper.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.03)
                .build());
        windWorshipper.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        windWorshipper.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        windWorshipper.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.WIND_WORSHIPPER, windWorshipper);

// GRAVE_SENTINEL
        Map<ArmorType, FAArmorAttributes> graveSentinel = new HashMap<>();
        graveSentinel.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.03)
                .build());
        graveSentinel.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        graveSentinel.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        graveSentinel.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.GRAVE_SENTINEL, graveSentinel);
// ORNSTEIN
        Map<ArmorType, FAArmorAttributes> ornstein = new HashMap<>();
        ornstein.put(ArmorType.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackDamage(0.05)
                .maxHealth(2.0)
                .build());
        ornstein.put(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .attackDamage(0.05)
                .build());
        ornstein.put(ArmorType.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .maxHealth(2.0)
                .build());
        ornstein.put(ArmorType.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.ORNSTEIN, ornstein);

        for (FAArmorSet set : FAArmorSet.values()) {
            DEFAULTS.computeIfAbsent(set, s -> new EnumMap<>(ArmorType.class));
            var map = DEFAULTS.get(set);
            map.putIfAbsent(ArmorType.HELMET, new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build());
            map.putIfAbsent(ArmorType.BOOTS,  new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build());
            map.putIfAbsent(ArmorType.CHESTPLATE, new FAArmorAttributes.Builder().armor(8).armorToughness(2).knockbackResistance(0.1).build());
            map.putIfAbsent(ArmorType.LEGGINGS,   new FAArmorAttributes.Builder().armor(6).armorToughness(2).knockbackResistance(0.1).build());
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
        return switch (t) {
            case HELMET, BOOTS -> new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build();
            case CHESTPLATE, BODY -> new FAArmorAttributes.Builder().armor(8).armorToughness(2).knockbackResistance(0.1).build();
            case LEGGINGS -> new FAArmorAttributes.Builder().armor(6).armorToughness(2).knockbackResistance(0.1).build();
        };
    }
}
