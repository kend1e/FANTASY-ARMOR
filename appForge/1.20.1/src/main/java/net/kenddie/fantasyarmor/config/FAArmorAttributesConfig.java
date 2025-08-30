package net.kenddie.fantasyarmor.config;

import net.kenddie.fantasyarmor.item.armor.lib.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorSet;
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
    }

    private FAArmorAttributes getGlobalDefaults(ArmorItem.Type type) {
        return switch (type) {
            case HELMET, BOOTS -> new FAArmorAttributes.Builder().armor(3).armorToughness(2).knockbackResistance(0.1).build();
            case CHESTPLATE -> new FAArmorAttributes.Builder().armor(8).armorToughness(2).knockbackResistance(0.1).build();
            case LEGGINGS -> new FAArmorAttributes.Builder().armor(6).armorToughness(2).knockbackResistance(0.1).build();
        };
    }

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
    }
}
