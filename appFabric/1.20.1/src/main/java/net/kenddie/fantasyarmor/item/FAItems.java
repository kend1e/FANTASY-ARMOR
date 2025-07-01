package net.kenddie.fantasyarmor.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.ChessBoardKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.CrucibleKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.DarkCoverArmorItem;
import net.kenddie.fantasyarmor.item.armor.DarkLordArmorItem;
import net.kenddie.fantasyarmor.item.armor.DeadGladiatorArmorItem;
import net.kenddie.fantasyarmor.item.armor.DragonslayerArmorItem;
import net.kenddie.fantasyarmor.item.armor.EclipseSoldierArmorItem;
import net.kenddie.fantasyarmor.item.armor.EveningGhostArmorItem;
import net.kenddie.fantasyarmor.item.armor.FeasterArmorItem;
import net.kenddie.fantasyarmor.item.armor.FogGuardArmorItem;
import net.kenddie.fantasyarmor.item.armor.ForgottenTraceArmorItem;
import net.kenddie.fantasyarmor.item.armor.GildedHuntArmorItem;
import net.kenddie.fantasyarmor.item.armor.GoldenExecutionArmorItem;
import net.kenddie.fantasyarmor.item.armor.GoldenHornsArmorItem;
import net.kenddie.fantasyarmor.item.armor.GraveSentinelArmorItem;
import net.kenddie.fantasyarmor.item.armor.HeroArmorItem;
import net.kenddie.fantasyarmor.item.armor.LadyMariaArmorItem;
import net.kenddie.fantasyarmor.item.armor.MaleniaArmorItem;
import net.kenddie.fantasyarmor.item.armor.OldKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.RedeemerArmorItem;
import net.kenddie.fantasyarmor.item.armor.RoninArmorItem;
import net.kenddie.fantasyarmor.item.armor.SilverKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.SparkOfDawnArmorItem;
import net.kenddie.fantasyarmor.item.armor.SunsetWingsArmorItem;
import net.kenddie.fantasyarmor.item.armor.ThiefArmorItem;
import net.kenddie.fantasyarmor.item.armor.TwinnedArmorItem;
import net.kenddie.fantasyarmor.item.armor.WanderingWizardArmorItem;
import net.kenddie.fantasyarmor.item.armor.WindWorshipperArmorItem;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorAttributes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collection;

public final class FAItems {
    public static final Collection<Item> ITEMS = new ArrayList<>();

    public static final Item MOON_CRYSTAL = new Item(new FabricItemSettings());

    // ECLIPSE_SOLDIER
    public static final Item ECLIPSE_SOLDIER_HELMET = new EclipseSoldierArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.1)
            .build());

    public static final Item ECLIPSE_SOLDIER_CHESTPLATE = new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item ECLIPSE_SOLDIER_LEGGINGS = new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item ECLIPSE_SOLDIER_BOOTS = new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // DRAGONSLAYER
    public static final Item DRAGONSLAYER_HELMET = new DragonslayerArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item DRAGONSLAYER_CHESTPLATE = new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.1)
            .build());

    public static final Item DRAGONSLAYER_LEGGINGS = new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item DRAGONSLAYER_BOOTS = new DragonslayerArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // HERO
    public static final Item HERO_HELMET = new HeroArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackDamage(0.1)
            .attackSpeed(0.1)
            .build());

    public static final Item HERO_CHESTPLATE = new HeroArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackDamage(0.1)
            .attackSpeed(0.1)
            .build());

    public static final Item HERO_LEGGINGS = new HeroArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    public static final Item HERO_BOOTS = new HeroArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // GOLDEN_HORNS
    public static final Item GOLDEN_HORNS_HELMET = new GoldenHornsArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.03)
            .attackSpeed(0.1)
            .build());

    public static final Item GOLDEN_HORNS_CHESTPLATE = new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.01)
            .attackSpeed(0.1)
            .build());

    public static final Item GOLDEN_HORNS_LEGGINGS = new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.02)
            .build());

    public static final Item GOLDEN_HORNS_BOOTS = new GoldenHornsArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.04)
            .build());

    // THIEF
    public static final Item THIEF_HELMET = new ThiefArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .attackSpeed(0.1)
            .luck(1.0)
            .build());

    public static final Item THIEF_CHESTPLATE = new ThiefArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .attackSpeed(0.1)
            .build());

    public static final Item THIEF_LEGGINGS = new ThiefArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.1)
            .attackSpeed(0.1)
            .build());

    public static final Item THIEF_BOOTS = new ThiefArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.1)
            .attackSpeed(0.05)
            .build());

    // WANDERING_WIZARD
    public static final Item WANDERING_WIZARD_HELMET = new WanderingWizardArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .luck(2.0)
            .build());

    public static final Item WANDERING_WIZARD_CHESTPLATE = new WanderingWizardArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    public static final Item WANDERING_WIZARD_LEGGINGS = new WanderingWizardArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    public static final Item WANDERING_WIZARD_BOOTS = new WanderingWizardArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    // CHESS_BOARD_KNIGHT
    public static final Item CHESS_BOARD_KNIGHT_HELMET = new ChessBoardKnightArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item CHESS_BOARD_KNIGHT_CHESTPLATE = new ChessBoardKnightArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(2.0)
            .build());

    public static final Item CHESS_BOARD_KNIGHT_LEGGINGS = new ChessBoardKnightArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item CHESS_BOARD_KNIGHT_BOOTS = new ChessBoardKnightArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(2.0)
            .build());

    // DARK_LORD
    public static final Item DARK_LORD_HELMET = new DarkLordArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.02)
            .build());

    public static final Item DARK_LORD_CHESTPLATE = new DarkLordArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.03)
            .build());

    public static final Item DARK_LORD_LEGGINGS = new DarkLordArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.03)
            .build());

    public static final Item DARK_LORD_BOOTS = new DarkLordArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.02)
            .build());

    // SUNSET_WINGS
    public static final Item SUNSET_WINGS_HELMET = new SunsetWingsArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .maxHealth(1.0)
            .build());

    public static final Item SUNSET_WINGS_CHESTPLATE = new SunsetWingsArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .maxHealth(1.0)
            .build());

    public static final Item SUNSET_WINGS_LEGGINGS = new SunsetWingsArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .maxHealth(1.0)
            .build());

    public static final Item SUNSET_WINGS_BOOTS = new SunsetWingsArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .maxHealth(1.0)
            .build());

    // FOG_GUARD
    public static final Item FOG_GUARD_HELMET = new FogGuardArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.1)
            .build());

    public static final Item FOG_GUARD_CHESTPLATE = new FogGuardArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.01)
            .build());

    public static final Item FOG_GUARD_LEGGINGS = new FogGuardArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.02)
            .build());

    public static final Item FOG_GUARD_BOOTS = new FogGuardArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.03)
            .build());

    // DARK_COVER
    public static final Item DARK_COVER_HELMET = new DarkCoverArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(2.0)
            .build());

    public static final Item DARK_COVER_CHESTPLATE = new DarkCoverArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.1)
            .build());

    public static final Item DARK_COVER_LEGGINGS = new DarkCoverArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(1.0)
            .build());

    public static final Item DARK_COVER_BOOTS = new DarkCoverArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // SPARK_OF_DAWN
    public static final Item SPARK_OF_DAWN_HELMET = new SparkOfDawnArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.1)
            .build());

    public static final Item SPARK_OF_DAWN_CHESTPLATE = new SparkOfDawnArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.05)
            .build());

    public static final Item SPARK_OF_DAWN_LEGGINGS = new SparkOfDawnArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .movementSpeed(0.05)
            .maxHealth(1.0)
            .attackSpeed(0.05)
            .build());

    public static final Item SPARK_OF_DAWN_BOOTS = new SparkOfDawnArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    // GOLDEN_EXECUTION
    public static final Item GOLDEN_EXECUTION_HELMET = new GoldenExecutionArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(2.0)
            .attackSpeed(0.05)
            .build());

    public static final Item GOLDEN_EXECUTION_CHESTPLATE = new GoldenExecutionArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(4.0)
            .attackSpeed(0.05)
            .build());

    public static final Item GOLDEN_EXECUTION_LEGGINGS = new GoldenExecutionArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(1.0)
            .build());

    public static final Item GOLDEN_EXECUTION_BOOTS = new GoldenExecutionArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(1.0)
            .build());

    // FORGOTTEN_TRACE
    public static final Item FORGOTTEN_TRACE_HELMET = new ForgottenTraceArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .luck(1.0)
            .build());

    public static final Item FORGOTTEN_TRACE_CHESTPLATE = new ForgottenTraceArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.03)
            .maxHealth(2.0)
            .build());

    public static final Item FORGOTTEN_TRACE_LEGGINGS = new ForgottenTraceArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.02)
            .maxHealth(1.0)
            .build());

    public static final Item FORGOTTEN_TRACE_BOOTS = new ForgottenTraceArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .movementSpeed(0.05)
            .maxHealth(1.0)
            .luck(1.0)
            .build());

    // REDEEMER
    public static final Item REDEEMER_HELMET = new RedeemerArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(2.0)
            .attackSpeed(0.05)
            .luck(1.0)
            .build());

    public static final Item REDEEMER_CHESTPLATE = new RedeemerArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(2.0)
            .attackSpeed(0.03)
            .movementSpeed(0.02)
            .luck(1.0)
            .build());

    public static final Item REDEEMER_LEGGINGS = new RedeemerArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .movementSpeed(0.04)
            .build());

    public static final Item REDEEMER_BOOTS = new RedeemerArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.02)
            .build());

    // TWINNED
    public static final Item TWINNED_HELMET = new TwinnedArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.1)
            .movementSpeed(0.02)
            .build());

    public static final Item TWINNED_CHESTPLATE = new TwinnedArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.02)
            .movementSpeed(0.02)
            .build());

    public static final Item TWINNED_LEGGINGS = new TwinnedArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.05)
            .movementSpeed(0.02)
            .build());

    public static final Item TWINNED_BOOTS = new TwinnedArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.05)
            .movementSpeed(0.02)
            .build());

    // GILDED_HUNT
    public static final Item GILDED_HUNT_HELMET = new GildedHuntArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.1)
            .build());

    public static final Item GILDED_HUNT_CHESTPLATE = new GildedHuntArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .attackSpeed(0.1)
            .build());

    public static final Item GILDED_HUNT_LEGGINGS = new GildedHuntArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    public static final Item GILDED_HUNT_BOOTS = new GildedHuntArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // LADY_MARIA
    public static final Item LADY_MARIA_HELMET = new LadyMariaArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.05)
            .luck(1.0)
            .build());

    public static final Item LADY_MARIA_CHESTPLATE = new LadyMariaArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .attackSpeed(0.05)
            .build());

    public static final Item LADY_MARIA_LEGGINGS = new LadyMariaArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    public static final Item LADY_MARIA_BOOTS = new LadyMariaArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.03)
            .build());

    // CRUCIBLE_KNIGHT
    public static final Item CRUCIBLE_KNIGHT_HELMET = new CrucibleKnightArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item CRUCIBLE_KNIGHT_CHESTPLATE = new CrucibleKnightArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(2.0)
            .build());

    public static final Item CRUCIBLE_KNIGHT_LEGGINGS = new CrucibleKnightArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item CRUCIBLE_KNIGHT_BOOTS = new CrucibleKnightArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // EVENING_GHOST
    public static final Item EVENING_GHOST_HELMET = new EveningGhostArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .luck(1.0)
            .build());

    public static final Item EVENING_GHOST_CHESTPLATE = new EveningGhostArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.03)
            .luck(1.0)
            .build());

    public static final Item EVENING_GHOST_LEGGINGS = new EveningGhostArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.04)
            .build());

    public static final Item EVENING_GHOST_BOOTS = new EveningGhostArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    // RONIN
    public static final Item RONIN_HELMET = new RoninArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.1)
            .build());

    public static final Item RONIN_CHESTPLATE = new RoninArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.1)
            .build());

    public static final Item RONIN_LEGGINGS = new RoninArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.03)
            .build());

    public static final Item RONIN_BOOTS = new RoninArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    // MALENIA
    public static final Item MALENIA_HELMET = new MaleniaArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.07)
            .attackSpeed(0.05)
            .build());

    public static final Item MALENIA_CHESTPLATE = new MaleniaArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .movementSpeed(0.05)
            .attackSpeed(0.05)
            .build());

    public static final Item MALENIA_LEGGINGS = new MaleniaArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.05)
            .build());

    public static final Item MALENIA_BOOTS = new MaleniaArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.08)
            .build());

    // OLD KNIGHT
    public static final Item OLD_KNIGHT_HELMET = new OldKnightArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(1.0)
            .build());

    public static final Item OLD_KNIGHT_CHESTPLATE = new OldKnightArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(2.0)
            .build());

    public static final Item OLD_KNIGHT_LEGGINGS = new OldKnightArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .maxHealth(1.0)
            .build());

    public static final Item OLD_KNIGHT_BOOTS = new OldKnightArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    // SILVER KNIGHT
    public static final Item SILVER_KNIGHT_HELMET = new SilverKnightArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .luck(1.0)
            .attackSpeed(0.03)
            .build());

    public static final Item SILVER_KNIGHT_CHESTPLATE = new SilverKnightArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .luck(1.0)
            .attackSpeed(0.03)
            .build());

    public static final Item SILVER_KNIGHT_LEGGINGS = new SilverKnightArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.02)
            .build());

    public static final Item SILVER_KNIGHT_BOOTS = new SilverKnightArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .luck(1.0)
            .build());

    // DEAD GLADIATOR
    public static final Item DEAD_GLADIATOR_HELMET = new DeadGladiatorArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(2.0)
            .knockbackResistance(0.1)
            .attackDamage(0.05)
            .build());

    public static final Item DEAD_GLADIATOR_CHESTPLATE = new DeadGladiatorArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(2.0)
            .knockbackResistance(0.2)
            .attackDamage(0.1)
            .build());

    public static final Item DEAD_GLADIATOR_LEGGINGS = new DeadGladiatorArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(2.0)
            .knockbackResistance(0.1)
            .attackDamage(0.05)
            .build());

    public static final Item DEAD_GLADIATOR_BOOTS = new DeadGladiatorArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(2.0)
            .knockbackResistance(0.1)
            .build());

    // FLESH OF THE FEASTER
    public static final Item FLESH_OF_THE_FEASTER_HELMET = new FeasterArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(1.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .build());

    public static final Item FLESH_OF_THE_FEASTER_CHESTPLATE = new FeasterArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(1.0)
            .knockbackResistance(0.2)
            .maxHealth(2.0)
            .build());

    public static final Item FLESH_OF_THE_FEASTER_LEGGINGS = new FeasterArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(1.0)
            .knockbackResistance(0.1)
            .maxHealth(1.0)
            .build());

    public static final Item FLESH_OF_THE_FEASTER_BOOTS = new FeasterArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(1.0)
            .knockbackResistance(0.1)
            .build());

    // WIND WORSHIPPER
    public static final Item WIND_WORSHIPPER_HELMET = new WindWorshipperArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(2.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.03)
            .build());

    public static final Item WIND_WORSHIPPER_CHESTPLATE = new WindWorshipperArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(2.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.03)
            .build());

    public static final Item WIND_WORSHIPPER_LEGGINGS = new WindWorshipperArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(2.0)
            .knockbackResistance(0.1)
            .attackSpeed(0.02)
            .build());

    public static final Item WIND_WORSHIPPER_BOOTS = new WindWorshipperArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(2.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.03)
            .build());

    // GRAVE SENTINEL
    public static final Item GRAVE_SENTINEL_HELMET = new GraveSentinelArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item GRAVE_SENTINEL_CHESTPLATE = new GraveSentinelArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.1)
            .build());

    public static final Item GRAVE_SENTINEL_LEGGINGS = new GraveSentinelArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build());

    public static final Item GRAVE_SENTINEL_BOOTS = new GraveSentinelArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build());

    private FAItems() {
    }

    public static void register() {
        register("moon_crystal", MOON_CRYSTAL);

        register("eclipse_soldier_helmet", ECLIPSE_SOLDIER_HELMET);
        register("eclipse_soldier_chestplate", ECLIPSE_SOLDIER_CHESTPLATE);
        register("eclipse_soldier_leggings", ECLIPSE_SOLDIER_LEGGINGS);
        register("eclipse_soldier_boots", ECLIPSE_SOLDIER_BOOTS);

        register("dragonslayer_helmet", DRAGONSLAYER_HELMET);
        register("dragonslayer_chestplate", DRAGONSLAYER_CHESTPLATE);
        register("dragonslayer_leggings", DRAGONSLAYER_LEGGINGS);
        register("dragonslayer_boots", DRAGONSLAYER_BOOTS);

        register("hero_helmet", HERO_HELMET);
        register("hero_chestplate", HERO_CHESTPLATE);
        register("hero_leggings", HERO_LEGGINGS);
        register("hero_boots", HERO_BOOTS);

        register("golden_horns_helmet", GOLDEN_HORNS_HELMET);
        register("golden_horns_chestplate", GOLDEN_HORNS_CHESTPLATE);
        register("golden_horns_leggings", GOLDEN_HORNS_LEGGINGS);
        register("golden_horns_boots", GOLDEN_HORNS_BOOTS);

        register("thief_helmet", THIEF_HELMET);
        register("thief_chestplate", THIEF_CHESTPLATE);
        register("thief_leggings", THIEF_LEGGINGS);
        register("thief_boots", THIEF_BOOTS);

        register("wandering_wizard_helmet", WANDERING_WIZARD_HELMET);
        register("wandering_wizard_chestplate", WANDERING_WIZARD_CHESTPLATE);
        register("wandering_wizard_leggings", WANDERING_WIZARD_LEGGINGS);
        register("wandering_wizard_boots", WANDERING_WIZARD_BOOTS);

        register("chess_board_knight_helmet", CHESS_BOARD_KNIGHT_HELMET);
        register("chess_board_knight_chestplate", CHESS_BOARD_KNIGHT_CHESTPLATE);
        register("chess_board_knight_leggings", CHESS_BOARD_KNIGHT_LEGGINGS);
        register("chess_board_knight_boots", CHESS_BOARD_KNIGHT_BOOTS);

        register("dark_lord_helmet", DARK_LORD_HELMET);
        register("dark_lord_chestplate", DARK_LORD_CHESTPLATE);
        register("dark_lord_leggings", DARK_LORD_LEGGINGS);
        register("dark_lord_boots", DARK_LORD_BOOTS);

        register("sunset_wings_helmet", SUNSET_WINGS_HELMET);
        register("sunset_wings_chestplate", SUNSET_WINGS_CHESTPLATE);
        register("sunset_wings_leggings", SUNSET_WINGS_LEGGINGS);
        register("sunset_wings_boots", SUNSET_WINGS_BOOTS);

        register("fog_guard_helmet", FOG_GUARD_HELMET);
        register("fog_guard_chestplate", FOG_GUARD_CHESTPLATE);
        register("fog_guard_leggings", FOG_GUARD_LEGGINGS);
        register("fog_guard_boots", FOG_GUARD_BOOTS);

        register("dark_cover_helmet", DARK_COVER_HELMET);
        register("dark_cover_chestplate", DARK_COVER_CHESTPLATE);
        register("dark_cover_leggings", DARK_COVER_LEGGINGS);
        register("dark_cover_boots", DARK_COVER_BOOTS);

        register("spark_of_dawn_helmet", SPARK_OF_DAWN_HELMET);
        register("spark_of_dawn_chestplate", SPARK_OF_DAWN_CHESTPLATE);
        register("spark_of_dawn_leggings", SPARK_OF_DAWN_LEGGINGS);
        register("spark_of_dawn_boots", SPARK_OF_DAWN_BOOTS);

        register("golden_execution_helmet", GOLDEN_EXECUTION_HELMET);
        register("golden_execution_chestplate", GOLDEN_EXECUTION_CHESTPLATE);
        register("golden_execution_leggings", GOLDEN_EXECUTION_LEGGINGS);
        register("golden_execution_boots", GOLDEN_EXECUTION_BOOTS);

        register("forgotten_trace_helmet", FORGOTTEN_TRACE_HELMET);
        register("forgotten_trace_chestplate", FORGOTTEN_TRACE_CHESTPLATE);
        register("forgotten_trace_leggings", FORGOTTEN_TRACE_LEGGINGS);
        register("forgotten_trace_boots", FORGOTTEN_TRACE_BOOTS);

        register("redeemer_helmet", REDEEMER_HELMET);
        register("redeemer_chestplate", REDEEMER_CHESTPLATE);
        register("redeemer_leggings", REDEEMER_LEGGINGS);
        register("redeemer_boots", REDEEMER_BOOTS);

        register("twinned_helmet", TWINNED_HELMET);
        register("twinned_chestplate", TWINNED_CHESTPLATE);
        register("twinned_leggings", TWINNED_LEGGINGS);
        register("twinned_boots", TWINNED_BOOTS);

        register("gilded_hunt_helmet", GILDED_HUNT_HELMET);
        register("gilded_hunt_chestplate", GILDED_HUNT_CHESTPLATE);
        register("gilded_hunt_leggings", GILDED_HUNT_LEGGINGS);
        register("gilded_hunt_boots", GILDED_HUNT_BOOTS);

        register("lady_maria_helmet", LADY_MARIA_HELMET);
        register("lady_maria_chestplate", LADY_MARIA_CHESTPLATE);
        register("lady_maria_leggings", LADY_MARIA_LEGGINGS);
        register("lady_maria_boots", LADY_MARIA_BOOTS);

        register("crucible_knight_helmet", CRUCIBLE_KNIGHT_HELMET);
        register("crucible_knight_chestplate", CRUCIBLE_KNIGHT_CHESTPLATE);
        register("crucible_knight_leggings", CRUCIBLE_KNIGHT_LEGGINGS);
        register("crucible_knight_boots", CRUCIBLE_KNIGHT_BOOTS);

        register("evening_ghost_helmet", EVENING_GHOST_HELMET);
        register("evening_ghost_chestplate", EVENING_GHOST_CHESTPLATE);
        register("evening_ghost_leggings", EVENING_GHOST_LEGGINGS);
        register("evening_ghost_boots", EVENING_GHOST_BOOTS);

        register("ronin_helmet", RONIN_HELMET);
        register("ronin_chestplate", RONIN_CHESTPLATE);
        register("ronin_leggings", RONIN_LEGGINGS);
        register("ronin_boots", RONIN_BOOTS);

        register("malenia_helmet", MALENIA_HELMET);
        register("malenia_chestplate", MALENIA_CHESTPLATE);
        register("malenia_leggings", MALENIA_LEGGINGS);
        register("malenia_boots", MALENIA_BOOTS);

        register("old_knight_helmet", OLD_KNIGHT_HELMET);
        register("old_knight_chestplate", OLD_KNIGHT_CHESTPLATE);
        register("old_knight_leggings", OLD_KNIGHT_LEGGINGS);
        register("old_knight_boots", OLD_KNIGHT_BOOTS);

        register("silver_knight_helmet", SILVER_KNIGHT_HELMET);
        register("silver_knight_chestplate", SILVER_KNIGHT_CHESTPLATE);
        register("silver_knight_leggings", SILVER_KNIGHT_LEGGINGS);
        register("silver_knight_boots", SILVER_KNIGHT_BOOTS);

        register("dead_gladiator_helmet", DEAD_GLADIATOR_HELMET);
        register("dead_gladiator_chestplate", DEAD_GLADIATOR_CHESTPLATE);
        register("dead_gladiator_leggings", DEAD_GLADIATOR_LEGGINGS);
        register("dead_gladiator_boots", DEAD_GLADIATOR_BOOTS);

        register("flesh_of_the_feaster_helmet", FLESH_OF_THE_FEASTER_HELMET);
        register("flesh_of_the_feaster_chestplate", FLESH_OF_THE_FEASTER_CHESTPLATE);
        register("flesh_of_the_feaster_leggings", FLESH_OF_THE_FEASTER_LEGGINGS);
        register("flesh_of_the_feaster_boots", FLESH_OF_THE_FEASTER_BOOTS);

        register("wind_worshipper_helmet", WIND_WORSHIPPER_HELMET);
        register("wind_worshipper_chestplate", WIND_WORSHIPPER_CHESTPLATE);
        register("wind_worshipper_leggings", WIND_WORSHIPPER_LEGGINGS);
        register("wind_worshipper_boots", WIND_WORSHIPPER_BOOTS);

        register("grave_sentinel_helmet", GRAVE_SENTINEL_HELMET);
        register("grave_sentinel_chestplate", GRAVE_SENTINEL_CHESTPLATE);
        register("grave_sentinel_leggings", GRAVE_SENTINEL_LEGGINGS);
        register("grave_sentinel_boots", GRAVE_SENTINEL_BOOTS);
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(FantasyArmor.MOD_ID, name), item);
        ITEMS.add(item);
    }
}