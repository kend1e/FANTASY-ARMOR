package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.ChessBoardKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.CrucibleKnightArmorItem;
import net.kenddie.fantasyarmor.item.armor.DarkCoverArmorItem;
import net.kenddie.fantasyarmor.item.armor.DarkLordArmorItem;
import net.kenddie.fantasyarmor.item.armor.DragonslayerArmorItem;
import net.kenddie.fantasyarmor.item.armor.EclipseSoldierArmorItem;
import net.kenddie.fantasyarmor.item.armor.EveningGhostArmorItem;
import net.kenddie.fantasyarmor.item.armor.FogGuardArmorItem;
import net.kenddie.fantasyarmor.item.armor.ForgottenTraceArmorItem;
import net.kenddie.fantasyarmor.item.armor.GildedHuntArmorItem;
import net.kenddie.fantasyarmor.item.armor.GoldenExecutionArmorItem;
import net.kenddie.fantasyarmor.item.armor.GoldenHornsArmorItem;
import net.kenddie.fantasyarmor.item.armor.HeroArmorItem;
import net.kenddie.fantasyarmor.item.armor.LadyMariaArmorItem;
import net.kenddie.fantasyarmor.item.armor.RedeemerArmorItem;
import net.kenddie.fantasyarmor.item.armor.RoninArmorItem;
import net.kenddie.fantasyarmor.item.armor.SparkOfDawnArmorItem;
import net.kenddie.fantasyarmor.item.armor.SunsetWingsArmorItem;
import net.kenddie.fantasyarmor.item.armor.ThiefArmorItem;
import net.kenddie.fantasyarmor.item.armor.TwinnedArmorItem;
import net.kenddie.fantasyarmor.item.armor.WanderingWizardArmorItem;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorAttributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
public final class FAItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyArmor.MOD_ID);

    public static final RegistryObject<Item> MOON_CRYSTAL = ITEMS.register("moon_crystal", () -> new Item(new Item.Properties()));

    // ECLIPSE_SOLDIER
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_HELMET = ITEMS.register("eclipse_soldier_helmet", () ->
            new EclipseSoldierArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .movementSpeed(0.1)
            .build()));

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_CHESTPLATE = ITEMS.register("eclipse_soldier_chestplate", () ->
            new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build()));

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_LEGGINGS = ITEMS.register("eclipse_soldier_leggings", () ->
            new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build()));

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_BOOTS = ITEMS.register("eclipse_soldier_boots", () ->
            new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build()));

    // DRAGONSLAYER
    public static final RegistryObject<Item> DRAGONSLAYER_HELMET = ITEMS.register("dragonslayer_helmet", () ->
            new DragonslayerArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build()));

    public static final RegistryObject<Item> DRAGONSLAYER_CHESTPLATE = ITEMS.register("dragonslayer_chestplate", () ->
            new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
            .armor(8.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .attackSpeed(0.1)
            .build()));

    public static final RegistryObject<Item> DRAGONSLAYER_LEGGINGS = ITEMS.register("dragonslayer_leggings", () ->
            new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
            .armor(6.0)
            .armorToughness(3.0)
            .knockbackResistance(0.2)
            .build()));

    public static final RegistryObject<Item> DRAGONSLAYER_BOOTS = ITEMS.register("dragonslayer_boots", () ->
            new DragonslayerArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
            .armor(3.0)
            .armorToughness(3.0)
            .knockbackResistance(0.1)
            .build()));

    // HERO
    public static final RegistryObject<Item> HERO_HELMET = ITEMS.register("hero_helmet", () ->
            new HeroArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackDamage(0.1)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> HERO_CHESTPLATE = ITEMS.register("hero_chestplate", () ->
            new HeroArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackDamage(0.1)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> HERO_LEGGINGS = ITEMS.register("hero_leggings", () ->
            new HeroArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .build()));

    public static final RegistryObject<Item> HERO_BOOTS = ITEMS.register("hero_boots", () ->
            new HeroArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .build()));

    // GOLDEN_HORNS
    public static final RegistryObject<Item> GOLDEN_HORNS_HELMET = ITEMS.register("golden_horns_helmet", () ->
            new GoldenHornsArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.03)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> GOLDEN_HORNS_CHESTPLATE = ITEMS.register("golden_horns_chestplate", () ->
            new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.01)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> GOLDEN_HORNS_LEGGINGS = ITEMS.register("golden_horns_leggings", () ->
            new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.02)
                    .build()));

    public static final RegistryObject<Item> GOLDEN_HORNS_BOOTS = ITEMS.register("golden_horns_boots", () ->
            new GoldenHornsArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.04)
                    .build()));

    // THIEF
    public static final RegistryObject<Item> THIEF_HELMET = ITEMS.register("thief_helmet", () ->
            new ThiefArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .attackSpeed(0.1)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> THIEF_CHESTPLATE = ITEMS.register("thief_chestplate", () ->
            new ThiefArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> THIEF_LEGGINGS = ITEMS.register("thief_leggings", () ->
            new ThiefArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.1)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> THIEF_BOOTS = ITEMS.register("thief_boots", () ->
            new ThiefArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.1)
                    .attackSpeed(0.05)
                    .build()));

    // WANDERING_WIZARD
    public static final RegistryObject<Item> WANDERING_WIZARD_HELMET = ITEMS.register("wandering_wizard_helmet", () ->
            new WanderingWizardArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .luck(2.0)
                    .build()));

    public static final RegistryObject<Item> WANDERING_WIZARD_CHESTPLATE = ITEMS.register("wandering_wizard_chestplate", () ->
            new WanderingWizardArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> WANDERING_WIZARD_LEGGINGS = ITEMS.register("wandering_wizard_leggings", () ->
            new WanderingWizardArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> WANDERING_WIZARD_BOOTS = ITEMS.register("wandering_wizard_boots", () ->
            new WanderingWizardArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));

    // CHESS_BOARD_KNIGHT
    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_HELMET = ITEMS.register("chess_board_knight_helmet", () ->
            new ChessBoardKnightArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .build()));

    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_CHESTPLATE = ITEMS.register("chess_board_knight_chestplate", () ->
            new ChessBoardKnightArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(2.0)
                    .build()));

    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_LEGGINGS = ITEMS.register("chess_board_knight_leggings", () ->
            new ChessBoardKnightArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .build()));

    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_BOOTS = ITEMS.register("chess_board_knight_boots", () ->
            new ChessBoardKnightArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(2.0)
                    .build()));

    // DARK_LORD
    public static final RegistryObject<Item> DARK_LORD_HELMET = ITEMS.register("dark_lord_helmet", () ->
            new DarkLordArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.02)
                    .build()));

    public static final RegistryObject<Item> DARK_LORD_CHESTPLATE = ITEMS.register("dark_lord_chestplate", () ->
            new DarkLordArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .attackSpeed(0.03)
                    .build()));

    public static final RegistryObject<Item> DARK_LORD_LEGGINGS = ITEMS.register("dark_lord_leggings", () ->
            new DarkLordArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .attackSpeed(0.03)
                    .build()));

    public static final RegistryObject<Item> DARK_LORD_BOOTS = ITEMS.register("dark_lord_boots", () ->
            new DarkLordArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.02)
                    .build()));

    // SUNSET_WINGS
    public static final RegistryObject<Item> SUNSET_WINGS_HELMET = ITEMS.register("sunset_wings_helmet", () ->
            new SunsetWingsArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .maxHealth(1.0)
                    .build()));

    public static final RegistryObject<Item> SUNSET_WINGS_CHESTPLATE = ITEMS.register("sunset_wings_chestplate", () ->
            new SunsetWingsArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .maxHealth(1.0)
                    .build()));

    public static final RegistryObject<Item> SUNSET_WINGS_LEGGINGS = ITEMS.register("sunset_wings_leggings", () ->
            new SunsetWingsArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .maxHealth(1.0)
                    .build()));

    public static final RegistryObject<Item> SUNSET_WINGS_BOOTS = ITEMS.register("sunset_wings_boots", () ->
            new SunsetWingsArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .maxHealth(1.0)
                    .build()));

    // FOG_GUARD
    public static final RegistryObject<Item> FOG_GUARD_HELMET = ITEMS.register("fog_guard_helmet", () ->
            new FogGuardArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> FOG_GUARD_CHESTPLATE = ITEMS.register("fog_guard_chestplate", () ->
            new FogGuardArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.01)
                    .build()));

    public static final RegistryObject<Item> FOG_GUARD_LEGGINGS = ITEMS.register("fog_guard_leggings", () ->
            new FogGuardArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.02)
                    .build()));

    public static final RegistryObject<Item> FOG_GUARD_BOOTS = ITEMS.register("fog_guard_boots", () ->
            new FogGuardArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.03)
                    .build()));

    // DARK_COVER
    public static final RegistryObject<Item> DARK_COVER_HELMET = ITEMS.register("dark_cover_helmet", () ->
            new DarkCoverArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(2.0)
                    .build()));

    public static final RegistryObject<Item> DARK_COVER_CHESTPLATE = ITEMS.register("dark_cover_chestplate", () ->
            new DarkCoverArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> DARK_COVER_LEGGINGS = ITEMS.register("dark_cover_leggings", () ->
            new DarkCoverArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(1.0)
                    .build()));

    public static final RegistryObject<Item> DARK_COVER_BOOTS = ITEMS.register("dark_cover_boots", () ->
            new DarkCoverArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .build()));

    // SPARK_OF_DAWN
    public static final RegistryObject<Item> SPARK_OF_DAWN_HELMET = ITEMS.register("spark_of_dawn_helmet", () ->
            new SparkOfDawnArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> SPARK_OF_DAWN_CHESTPLATE = ITEMS.register("spark_of_dawn_chestplate", () ->
            new SparkOfDawnArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> SPARK_OF_DAWN_LEGGINGS = ITEMS.register("spark_of_dawn_leggings", () ->
            new SparkOfDawnArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .movementSpeed(0.05)
                    .maxHealth(1.0)
                    .attackSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> SPARK_OF_DAWN_BOOTS = ITEMS.register("spark_of_dawn_boots", () ->
            new SparkOfDawnArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));

    // GOLDEN_EXECUTION
    public static final RegistryObject<Item> GOLDEN_EXECUTION_HELMET = ITEMS.register("golden_execution_helmet", () ->
            new GoldenExecutionArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(2.0)
                    .attackSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> GOLDEN_EXECUTION_CHESTPLATE = ITEMS.register("golden_execution_chestplate", () ->
            new GoldenExecutionArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(4.0)
                    .attackSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> GOLDEN_EXECUTION_LEGGINGS = ITEMS.register("golden_execution_leggings", () ->
            new GoldenExecutionArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(1.0)
                    .build()));

    public static final RegistryObject<Item> GOLDEN_EXECUTION_BOOTS = ITEMS.register("golden_execution_boots", () ->
            new GoldenExecutionArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(1.0)
                    .build()));

    // FORGOTTEN_TRACE
    public static final RegistryObject<Item> FORGOTTEN_TRACE_HELMET = ITEMS.register("forgotten_trace_helmet", () ->
            new ForgottenTraceArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> FORGOTTEN_TRACE_CHESTPLATE = ITEMS.register("forgotten_trace_chestplate", () ->
            new ForgottenTraceArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.03)
                    .maxHealth(2.0)
                    .build()));

    public static final RegistryObject<Item> FORGOTTEN_TRACE_LEGGINGS = ITEMS.register("forgotten_trace_leggings", () ->
            new ForgottenTraceArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.02)
                    .maxHealth(1.0)
                    .build()));

    public static final RegistryObject<Item> FORGOTTEN_TRACE_BOOTS = ITEMS.register("forgotten_trace_boots", () ->
            new ForgottenTraceArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .movementSpeed(0.05)
                    .maxHealth(1.0)
                    .luck(1.0)
                    .build()));

    // REDEEMER
    public static final RegistryObject<Item> REDEEMER_HELMET = ITEMS.register("redeemer_helmet", () ->
            new RedeemerArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(2.0)
                    .attackSpeed(0.05)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> REDEEMER_CHESTPLATE = ITEMS.register("redeemer_chestplate", () ->
            new RedeemerArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(2.0)
                    .attackSpeed(0.03)
                    .movementSpeed(0.02)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> REDEEMER_LEGGINGS = ITEMS.register("redeemer_leggings", () ->
            new RedeemerArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .movementSpeed(0.04)
                    .build()));

    public static final RegistryObject<Item> REDEEMER_BOOTS = ITEMS.register("redeemer_boots", () ->
            new RedeemerArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.02)
                    .build()));

    // TWINNED
    public static final RegistryObject<Item> TWINNED_HELMET = ITEMS.register("twinned_helmet", () ->
            new TwinnedArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .attackSpeed(0.1)
                    .movementSpeed(0.02)
                    .build()));

    public static final RegistryObject<Item> TWINNED_CHESTPLATE = ITEMS.register("twinned_chestplate", () ->
            new TwinnedArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.02)
                    .movementSpeed(0.02)
                    .build()));

    public static final RegistryObject<Item> TWINNED_LEGGINGS = ITEMS.register("twinned_leggings", () ->
            new TwinnedArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .attackSpeed(0.05)
                    .movementSpeed(0.02)
                    .build()));

    public static final RegistryObject<Item> TWINNED_BOOTS = ITEMS.register("twinned_boots", () ->
            new TwinnedArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.05)
                    .movementSpeed(0.02)
                    .build()));

    // GILDED_HUNT
    public static final RegistryObject<Item> GILDED_HUNT_HELMET = ITEMS.register("gilded_hunt_helmet", () ->
            new GildedHuntArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> GILDED_HUNT_CHESTPLATE = ITEMS.register("gilded_hunt_chestplate", () ->
            new GildedHuntArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .maxHealth(1.0)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> GILDED_HUNT_LEGGINGS = ITEMS.register("gilded_hunt_leggings", () ->
            new GildedHuntArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .build()));

    public static final RegistryObject<Item> GILDED_HUNT_BOOTS = ITEMS.register("gilded_hunt_boots", () ->
            new GildedHuntArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .build()));

    // LADY_MARIA
    public static final RegistryObject<Item> LADY_MARIA_HELMET = ITEMS.register("lady_maria_helmet", () ->
            new LadyMariaArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.05)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> LADY_MARIA_CHESTPLATE = ITEMS.register("lady_maria_chestplate", () ->
            new LadyMariaArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .attackSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> LADY_MARIA_LEGGINGS = ITEMS.register("lady_maria_leggings", () ->
            new LadyMariaArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));

    public static final RegistryObject<Item> LADY_MARIA_BOOTS = ITEMS.register("lady_maria_boots", () ->
            new LadyMariaArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.03)
                    .build()));

    // CRUCIBLE_KNIGHT
    public static final RegistryObject<Item> CRUCIBLE_KNIGHT_HELMET = ITEMS.register("crucible_knight_helmet", () ->
            new CrucibleKnightArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .build()));

    public static final RegistryObject<Item> CRUCIBLE_KNIGHT_CHESTPLATE = ITEMS.register("crucible_knight_chestplate", () ->
            new CrucibleKnightArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .maxHealth(2.0)
                    .build()));

    public static final RegistryObject<Item> CRUCIBLE_KNIGHT_LEGGINGS = ITEMS.register("crucible_knight_leggings", () ->
            new CrucibleKnightArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.2)
                    .build()));

    public static final RegistryObject<Item> CRUCIBLE_KNIGHT_BOOTS = ITEMS.register("crucible_knight_boots", () ->
            new CrucibleKnightArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .build()));

    // EVENING_GHOST
    public static final RegistryObject<Item> EVENING_GHOST_HELMET = ITEMS.register("evening_ghost_helmet", () ->
            new EveningGhostArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> EVENING_GHOST_CHESTPLATE = ITEMS.register("evening_ghost_chestplate", () ->
            new EveningGhostArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.03)
                    .luck(1.0)
                    .build()));

    public static final RegistryObject<Item> EVENING_GHOST_LEGGINGS = ITEMS.register("evening_ghost_leggings", () ->
            new EveningGhostArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.04)
                    .build()));

    public static final RegistryObject<Item> EVENING_GHOST_BOOTS = ITEMS.register("evening_ghost_boots", () ->
            new EveningGhostArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));

    // RONIN
    public static final RegistryObject<Item> RONIN_HELMET = ITEMS.register("ronin_helmet", () ->
            new RoninArmorItem(ArmorItem.Type.HELMET, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> RONIN_CHESTPLATE = ITEMS.register("ronin_chestplate", () ->
            new RoninArmorItem(ArmorItem.Type.CHESTPLATE, FAArmorAttributes.builder()
                    .armor(8.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .attackSpeed(0.1)
                    .build()));

    public static final RegistryObject<Item> RONIN_LEGGINGS = ITEMS.register("ronin_leggings", () ->
            new RoninArmorItem(ArmorItem.Type.LEGGINGS, FAArmorAttributes.builder()
                    .armor(6.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.03)
                    .build()));

    public static final RegistryObject<Item> RONIN_BOOTS = ITEMS.register("ronin_boots", () ->
            new RoninArmorItem(ArmorItem.Type.BOOTS, FAArmorAttributes.builder()
                    .armor(3.0)
                    .armorToughness(3.0)
                    .knockbackResistance(0.1)
                    .movementSpeed(0.05)
                    .build()));


    private FAItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
