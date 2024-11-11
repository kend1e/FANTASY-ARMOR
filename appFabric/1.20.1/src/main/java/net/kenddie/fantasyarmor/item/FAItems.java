package net.kenddie.fantasyarmor.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kenddie.fantasyarmor.FantasyArmor;

import net.kenddie.fantasyarmor.item.armor.*;
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

    public static final Item ECLIPSE_SOLDIER_HELMET = new EclipseSoldierArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.1, 0, 0, 0, 0));
    public static final Item ECLIPSE_SOLDIER_CHESTPLATE = new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0, 0.1, 0));
    public static final Item ECLIPSE_SOLDIER_LEGGINGS = new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0, 0, 0));
    public static final Item ECLIPSE_SOLDIER_BOOTS = new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0));

    public static final Item DRAGONSLAYER_HELMET = new DragonslayerArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 0, 0, 0, 0));
    public static final Item DRAGONSLAYER_CHESTPLATE = new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0.1, 0, 0));
    public static final Item DRAGONSLAYER_LEGGINGS = new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0.05, 0, 0));
    public static final Item DRAGONSLAYER_BOOTS = new DragonslayerArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.05, 0, 0));

    public static final Item HERO_HELMET = new HeroArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.1, 0.1, 0));
    public static final Item HERO_CHESTPLATE = new HeroArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 0, 0.1, 0.1, 0));
    public static final Item HERO_LEGGINGS = new HeroArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0, 0, 0, 0, 0));
    public static final Item HERO_BOOTS = new HeroArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0));

    public static final Item GOLDEN_HORNS_HELMET = new GoldenHornsArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.03, 0, 0, 0.1, 0));
    public static final Item GOLDEN_HORNS_CHESTPLATE = new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.01, 0, 0, 0.1, 0));
    public static final Item GOLDEN_HORNS_LEGGINGS = new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.02, 0, 0, 0, 0));
    public static final Item GOLDEN_HORNS_BOOTS = new GoldenHornsArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.04, 0, 0, 0, 0));

    public static final Item THIEF_HELMET = new ThiefArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0.1, 1.0));
    public static final Item THIEF_CHESTPLATE = new ThiefArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 0, 0, 0.1, 0));
    public static final Item THIEF_LEGGINGS = new ThiefArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.1, 0, 0, 0.1, 0));
    public static final Item THIEF_BOOTS = new ThiefArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.1, 0, 0, 0.05, 0));

    public static final Item WANDERING_WIZARD_HELMET = new WanderingWizardArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 2.0));
    public static final Item WANDERING_WIZARD_CHESTPLATE = new WanderingWizardArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 0, 0, 0, 0));
    public static final Item WANDERING_WIZARD_LEGGINGS = new WanderingWizardArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.05, 0, 0, 0, 0));
    public static final Item WANDERING_WIZARD_BOOTS = new WanderingWizardArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 0));

    public static final Item CHESS_BOARD_KNIGHT_HELMET = new ChessBoardKnightArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 0, 0, 0, 0));
    public static final Item CHESS_BOARD_KNIGHT_CHESTPLATE = new ChessBoardKnightArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 2.0, 0, 0, 0));
    public static final Item CHESS_BOARD_KNIGHT_LEGGINGS = new ChessBoardKnightArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0, 0, 0));
    public static final Item CHESS_BOARD_KNIGHT_BOOTS = new ChessBoardKnightArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 2.0, 0, 0, 0));

    public static final Item DARK_LORD_HELMET = new DarkLordArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.02, 0, 0));
    public static final Item DARK_LORD_CHESTPLATE = new DarkLordArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0.03, 0, 0));
    public static final Item DARK_LORD_LEGGINGS = new DarkLordArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0.03, 0, 0));
    public static final Item DARK_LORD_BOOTS = new DarkLordArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.02, 0, 0));

    public static final Item SUNSET_WINGS_HELMET = new SunsetWingsArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0));
    public static final Item SUNSET_WINGS_CHESTPLATE = new SunsetWingsArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0));
    public static final Item SUNSET_WINGS_LEGGINGS = new SunsetWingsArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0));
    public static final Item SUNSET_WINGS_BOOTS = new SunsetWingsArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0));

    public static final Item FOG_GUARD_HELMET = new FogGuardArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0, 0.1, 0));
    public static final Item FOG_GUARD_CHESTPLATE = new FogGuardArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 1.0, 0.01, 0, 0));
    public static final Item FOG_GUARD_LEGGINGS = new FogGuardArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0, 1.0, 0.02, 0, 0));
    public static final Item FOG_GUARD_BOOTS = new FogGuardArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0.03, 0, 0));

    public static final Item DARK_COVER_HELMET = new DarkCoverArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 2.0, 0, 0, 0));
    public static final Item DARK_COVER_CHESTPLATE = new DarkCoverArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0.1, 0, 0));
    public static final Item DARK_COVER_LEGGINGS = new DarkCoverArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 1.0, 0, 0, 0));
    public static final Item DARK_COVER_BOOTS = new DarkCoverArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0));

    public static final Item SPARK_OF_DAWN_HELMET = new SparkOfDawnArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0, 0.1, 0));
    public static final Item SPARK_OF_DAWN_CHESTPLATE = new SparkOfDawnArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 0, 0.05, 0, 0));
    public static final Item SPARK_OF_DAWN_LEGGINGS = new SparkOfDawnArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0.05, 1.0, 0, 0.05, 0));
    public static final Item SPARK_OF_DAWN_BOOTS = new SparkOfDawnArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 0));

    public static final Item GOLDEN_EXECUTION_HELMET = new GoldenExecutionArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 2.0, 0.05, 0, 0));
    public static final Item GOLDEN_EXECUTION_CHESTPLATE = new GoldenExecutionArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 4.0, 0.05, 0, 0));
    public static final Item GOLDEN_EXECUTION_LEGGINGS = new GoldenExecutionArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 1.0, 0, 0, 0));
    public static final Item GOLDEN_EXECUTION_BOOTS = new GoldenExecutionArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 1.0, 0, 0, 0));

    public static final Item FORGOTTEN_TRACE_HELMET = new ForgottenTraceArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.5, 0, 0, 0, 1.0));
    public static final Item FORGOTTEN_TRACE_CHESTPLATE = new ForgottenTraceArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.03, 2.0, 0, 0, 0));
    public static final Item FORGOTTEN_TRACE_LEGGINGS = new ForgottenTraceArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.02, 1.0, 0, 0, 0));
    public static final Item FORGOTTEN_TRACE_BOOTS = new ForgottenTraceArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.2, 0.05, 1.0, 0, 0, 1.0));

    public static final Item REDEEMER_HELMET = new RedeemerArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 2.0, 0, 0.05, 1.0));
    public static final Item REDEEMER_CHESTPLATE = new RedeemerArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 2.0, 0.03, 0.02, 1.0));
    public static final Item REDEEMER_LEGGINGS = new RedeemerArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0.04, 0, 0, 0, 0));
    public static final Item REDEEMER_BOOTS = new RedeemerArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.02, 0, 0, 0, 0));

    public static final Item TWINNED_HELMET = new TwinnedArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 0, 0.1, 0.02, 0));
    public static final Item TWINNED_CHESTPLATE = new TwinnedArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 0, 0.02, 0.02, 0));
    public static final Item TWINNED_LEGGINGS = new TwinnedArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0.05, 0.02, 0));
    public static final Item TWINNED_BOOTS = new TwinnedArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.05, 0.02, 0));

    public static final Item GILDED_HUNT_HELMET = new GildedHuntArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0.1, 0, 0));
    public static final Item GILDED_HUNT_CHESTPLATE = new GildedHuntArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 1.0, 0.1, 0, 0));
    public static final Item GILDED_HUNT_LEGGINGS = new GildedHuntArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0, 0, 0, 0, 0));
    public static final Item GILDED_HUNT_BOOTS = new GildedHuntArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0));

    public static final Item LADY_MARIA_HELMET = new LadyMariaArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0.05, 1.0));
    public static final Item LADY_MARIA_CHESTPLATE = new LadyMariaArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 0, 0, 0.05, 0));
    public static final Item LADY_MARIA_LEGGINGS = new LadyMariaArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.05, 0, 0, 0, 0));
    public static final Item LADY_MARIA_BOOTS = new LadyMariaArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.03, 0, 0, 0, 0));

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
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(FantasyArmor.MOD_ID, name), item);
        ITEMS.add(item);
    }
}