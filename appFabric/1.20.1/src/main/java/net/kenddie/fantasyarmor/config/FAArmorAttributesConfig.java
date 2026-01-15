package net.kenddie.fantasyarmor.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.kenddie.fantasyarmor.item.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.ArmorItem;

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

    private static final Map<FAArmorSet, Map<ArmorItem.Type, FAArmorAttributes>> DEFAULTS = new EnumMap<>(FAArmorSet.class);

    static {
// ECLIPSE_SOLDIER
        Map<ArmorItem.Type, FAArmorAttributes> eclipseSoldier = new HashMap<>();
        eclipseSoldier.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.1)
                .build());
        eclipseSoldier.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        eclipseSoldier.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        eclipseSoldier.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.ECLIPSE_SOLDIER, eclipseSoldier);

// DRAGONSLAYER
        Map<ArmorItem.Type, FAArmorAttributes> dragonslayer = new HashMap<>();
        dragonslayer.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        dragonslayer.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.1)
                .build());
        dragonslayer.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        dragonslayer.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.DRAGONSLAYER, dragonslayer);

// HERO
        Map<ArmorItem.Type, FAArmorAttributes> hero = new HashMap<>();
        hero.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackDamage(0.1)
                .attackSpeed(0.1)
                .build());
        hero.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackDamage(0.1)
                .attackSpeed(0.1)
                .build());
        hero.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        hero.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.HERO, hero);

// GOLDEN_HORNS
        Map<ArmorItem.Type, FAArmorAttributes> goldenHorns = new HashMap<>();
        goldenHorns.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .attackSpeed(0.1)
                .build());
        goldenHorns.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.01)
                .attackSpeed(0.1)
                .build());
        goldenHorns.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.02)
                .build());
        goldenHorns.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.04)
                .build());
        DEFAULTS.put(FAArmorSet.GOLDEN_HORNS, goldenHorns);

// THIEF
        Map<ArmorItem.Type, FAArmorAttributes> thief = new HashMap<>();
        thief.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .attackSpeed(0.1)
                .luck(1.0)
                .build());
        thief.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .attackSpeed(0.1)
                .build());
        thief.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.1)
                .attackSpeed(0.1)
                .build());
        thief.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.1)
                .attackSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.THIEF, thief);

// WANDERING_WIZARD
        Map<ArmorItem.Type, FAArmorAttributes> wanderingWizard = new HashMap<>();
        wanderingWizard.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .luck(2.0)
                .build());
        wanderingWizard.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        wanderingWizard.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        wanderingWizard.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.WANDERING_WIZARD, wanderingWizard);

// CHESS_BOARD_KNIGHT
        Map<ArmorItem.Type, FAArmorAttributes> chessBoardKnight = new HashMap<>();
        chessBoardKnight.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        chessBoardKnight.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        chessBoardKnight.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .build());
        chessBoardKnight.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        DEFAULTS.put(FAArmorSet.CHESS_BOARD_KNIGHT, chessBoardKnight);

// DARK_LORD
        Map<ArmorItem.Type, FAArmorAttributes> darkLord = new HashMap<>();
        darkLord.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        darkLord.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        darkLord.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        darkLord.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        DEFAULTS.put(FAArmorSet.DARK_LORD, darkLord);

// SUNSET_WINGS
        Map<ArmorItem.Type, FAArmorAttributes> sunsetWings = new HashMap<>();
        sunsetWings.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        sunsetWings.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        sunsetWings.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        sunsetWings.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.SUNSET_WINGS, sunsetWings);

// FOG_GUARD
        Map<ArmorItem.Type, FAArmorAttributes> fogGuard = new HashMap<>();
        fogGuard.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        fogGuard.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.01)
                .build());
        fogGuard.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.02)
                .build());
        fogGuard.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.FOG_GUARD, fogGuard);

// DARK_COVER
        Map<ArmorItem.Type, FAArmorAttributes> darkCover = new HashMap<>();
        darkCover.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(2.0)
                .build());
        darkCover.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .attackSpeed(0.1)
                .build());
        darkCover.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        darkCover.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.DARK_COVER, darkCover);

// SPARK_OF_DAWN
        Map<ArmorItem.Type, FAArmorAttributes> sparkOfDawn = new HashMap<>();
        sparkOfDawn.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        sparkOfDawn.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .attackSpeed(0.05)
                .build());
        sparkOfDawn.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .attackSpeed(0.05)
                .build());
        sparkOfDawn.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.SPARK_OF_DAWN, sparkOfDawn);

// GOLDEN_EXECUTION
        Map<ArmorItem.Type, FAArmorAttributes> goldenExecution = new HashMap<>();
        goldenExecution.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .attackSpeed(0.05)
                .build());
        goldenExecution.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(4.0)
                .attackSpeed(0.05)
                .build());
        goldenExecution.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        goldenExecution.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.GOLDEN_EXECUTION, goldenExecution);

// FORGOTTEN_TRACE
        Map<ArmorItem.Type, FAArmorAttributes> forgottenTrace = new HashMap<>();
        forgottenTrace.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .luck(1.0)
                .build());
        forgottenTrace.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .maxHealth(2.0)
                .build());
        forgottenTrace.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.02)
                .maxHealth(1.0)
                .build());
        forgottenTrace.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .movementSpeed(0.05)
                .maxHealth(1.0)
                .luck(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.FORGOTTEN_TRACE, forgottenTrace);

// REDEEMER
        Map<ArmorItem.Type, FAArmorAttributes> redeemer = new HashMap<>();
        redeemer.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(2.0)
                .attackSpeed(0.05)
                .luck(1.0)
                .build());
        redeemer.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .maxHealth(2.0)
                .attackSpeed(0.03)
                .movementSpeed(0.02)
                .luck(1.0)
                .build());
        redeemer.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6)
                .armorToughness(3)
                .knockbackResistance(0.2)
                .movementSpeed(0.04)
                .build());
        redeemer.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3)
                .armorToughness(3)
                .knockbackResistance(0.1)
                .movementSpeed(0.02)
                .build());
        DEFAULTS.put(FAArmorSet.REDEEMER, redeemer);

// TWINNED
        Map<ArmorItem.Type, FAArmorAttributes> twinned = new HashMap<>();
        twinned.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.1)
                .movementSpeed(0.02)
                .build());
        twinned.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .movementSpeed(0.02)
                .build());
        twinned.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.05)
                .movementSpeed(0.02)
                .build());
        twinned.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.05)
                .movementSpeed(0.02)
                .build());
        DEFAULTS.put(FAArmorSet.TWINNED, twinned);

// GILDED_HUNT
        Map<ArmorItem.Type, FAArmorAttributes> gildedHunt = new HashMap<>();
        gildedHunt.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        gildedHunt.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .attackSpeed(0.1)
                .build());
        gildedHunt.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        gildedHunt.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.GILDED_HUNT, gildedHunt);

// LADY_MARIA
        Map<ArmorItem.Type, FAArmorAttributes> ladyMaria = new HashMap<>();
        ladyMaria.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.05)
                .luck(1.0)
                .build());
        ladyMaria.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .attackSpeed(0.05)
                .build());
        ladyMaria.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        ladyMaria.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.LADY_MARIA, ladyMaria);

// CRUCIBLE_KNIGHT
        Map<ArmorItem.Type, FAArmorAttributes> crucibleKnight = new HashMap<>();
        crucibleKnight.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .build());
        crucibleKnight.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        crucibleKnight.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .build());
        crucibleKnight.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.CRUCIBLE_KNIGHT, crucibleKnight);

// EVENING_GHOST
        Map<ArmorItem.Type, FAArmorAttributes> eveningGhost = new HashMap<>();
        eveningGhost.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .luck(1.0)
                .build());
        eveningGhost.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .luck(1.0)
                .build());
        eveningGhost.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.04)
                .build());
        eveningGhost.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.EVENING_GHOST, eveningGhost);

// RONIN
        Map<ArmorItem.Type, FAArmorAttributes> ronin = new HashMap<>();
        ronin.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.1)
                .build());
        ronin.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.1)
                .build());
        ronin.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .build());
        ronin.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        DEFAULTS.put(FAArmorSet.RONIN, ronin);

// MALENIA
        Map<ArmorItem.Type, FAArmorAttributes> malenia = new HashMap<>();
        malenia.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.07)
                .attackSpeed(0.05)
                .build());
        malenia.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .movementSpeed(0.05)
                .attackSpeed(0.05)
                .build());
        malenia.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.05)
                .build());
        malenia.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.08)
                .build());
        DEFAULTS.put(FAArmorSet.MALENIA, malenia);

// OLD_KNIGHT
        Map<ArmorItem.Type, FAArmorAttributes> oldKnight = new HashMap<>();
        oldKnight.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        oldKnight.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        oldKnight.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .maxHealth(1.0)
                .build());
        oldKnight.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.OLD_KNIGHT, oldKnight);

// SILVER_KNIGHT
        Map<ArmorItem.Type, FAArmorAttributes> silverKnight = new HashMap<>();
        silverKnight.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .luck(1.0)
                .attackSpeed(0.03)
                .build());
        silverKnight.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(3.0)
                .knockbackResistance(0.2)
                .luck(1.0)
                .attackSpeed(0.03)
                .build());
        silverKnight.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        silverKnight.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(3.0)
                .knockbackResistance(0.1)
                .luck(1.0)
                .build());
        DEFAULTS.put(FAArmorSet.SILVER_KNIGHT, silverKnight);

// DEAD_GLADIATOR
        Map<ArmorItem.Type, FAArmorAttributes> deadGladiator = new HashMap<>();
        deadGladiator.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackDamage(0.05)
                .build());
        deadGladiator.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .attackDamage(0.1)
                .build());
        deadGladiator.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackDamage(0.05)
                .build());
        deadGladiator.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.DEAD_GLADIATOR, deadGladiator);

// FLESH_OF_THE_FEASTER
        Map<ArmorItem.Type, FAArmorAttributes> fleshOfTheFeaster = new HashMap<>();
        fleshOfTheFeaster.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(1.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .build());
        fleshOfTheFeaster.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(1.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .build());
        fleshOfTheFeaster.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(1.0)
                .knockbackResistance(0.1)
                .maxHealth(1.0)
                .build());
        fleshOfTheFeaster.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(1.0)
                .knockbackResistance(0.1)
                .build());
        DEFAULTS.put(FAArmorSet.FLESH_OF_THE_FEASTER, fleshOfTheFeaster);

// WIND_WORSHIPPER
        Map<ArmorItem.Type, FAArmorAttributes> windWorshipper = new HashMap<>();
        windWorshipper.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.03)
                .build());
        windWorshipper.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        windWorshipper.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        windWorshipper.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.WIND_WORSHIPPER, windWorshipper);

// GRAVE_SENTINEL
        Map<ArmorItem.Type, FAArmorAttributes> graveSentinel = new HashMap<>();
        graveSentinel.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.03)
                .build());
        graveSentinel.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .attackSpeed(0.03)
                .build());
        graveSentinel.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackSpeed(0.02)
                .build());
        graveSentinel.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.GRAVE_SENTINEL, graveSentinel);
// ORNSTEIN
        Map<ArmorItem.Type, FAArmorAttributes> ornstein = new HashMap<>();
        ornstein.put(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .attackDamage(0.05)
                .maxHealth(2.0)
                .build());
        ornstein.put(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder()
                .armor(8.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .maxHealth(2.0)
                .attackDamage(0.05)
                .build());
        ornstein.put(ArmorItem.Type.LEGGINGS, new FAArmorAttributes.Builder()
                .armor(6.0)
                .armorToughness(2.0)
                .knockbackResistance(0.1)
                .movementSpeed(0.03)
                .maxHealth(2.0)
                .build());
        ornstein.put(ArmorItem.Type.BOOTS, new FAArmorAttributes.Builder()
                .armor(3.0)
                .armorToughness(2.0)
                .knockbackResistance(0.2)
                .movementSpeed(0.03)
                .build());
        DEFAULTS.put(FAArmorSet.ORNSTEIN, ornstein);

        for (FAArmorSet set : FAArmorSet.values()) {
            DEFAULTS.computeIfAbsent(set, s -> new EnumMap<>(ArmorItem.Type.class));
            var map = DEFAULTS.get(set);
            map.putIfAbsent(ArmorItem.Type.HELMET, new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build());
            map.putIfAbsent(ArmorItem.Type.BOOTS,  new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build());
            map.putIfAbsent(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes.Builder().armor(8).armorToughness(2).knockbackResistance(0.1).build());
            map.putIfAbsent(ArmorItem.Type.LEGGINGS,   new FAArmorAttributes.Builder().armor(6).armorToughness(2).knockbackResistance(0.1).build());
        }
    }

    @Override
    public void validatePostLoad() throws ValidationException {
        if (bySet == null) bySet = new LinkedHashMap<>();
        for (FAArmorSet set : FAArmorSet.values()) {
            String setKey = set.getName(); // lower-case
            PerSet perSet = bySet.computeIfAbsent(setKey, k -> new PerSet());
            if (perSet.byPiece == null) perSet.byPiece = new LinkedHashMap<>();

            for (ArmorItem.Type type : ArmorItem.Type.values()) {
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

    private static ArmorItem.Type resolveType(String piece) {
        for (ArmorItem.Type t : ArmorItem.Type.values()) if (t.getName().equals(piece)) return t;
        return ArmorItem.Type.HELMET;
    }

    private static FAArmorAttributes fallback(ArmorItem.Type t) {
        return switch (t) {
            case HELMET, BOOTS -> new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build();
            case CHESTPLATE -> new FAArmorAttributes.Builder().armor(8).armorToughness(2).knockbackResistance(0.1).build();
            case LEGGINGS -> new FAArmorAttributes.Builder().armor(6).armorToughness(2).knockbackResistance(0.1).build();
        };
    }
}
