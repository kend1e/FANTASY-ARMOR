package net.kenddie.fantasyarmor.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kenddie.fantasyarmor.FantasyArmor;

import net.kenddie.fantasyarmor.item.armor.*;
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

    public static final Item ECLIPSE_SOLDIER_HELMET = new EclipseSoldierArmorItem(ArmorItem.Type.HELMET, 0.1, 0.1, 0, 0, 0, 0);
    public static final Item ECLIPSE_SOLDIER_CHESTPLATE = new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE, 0.2, 0, 0, 0, 0.1, 0);
    public static final Item ECLIPSE_SOLDIER_LEGGINGS = new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS, 0.2, 0, 0, 0, 0, 0);
    public static final Item ECLIPSE_SOLDIER_BOOTS = new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS, 0.1, 0, 0, 0, 0, 0);

    public static final Item DRAGONSLAYER_HELMET = new DragonslayerArmorItem(ArmorItem.Type.HELMET, 0.2, 0, 0, 0, 0, 0);
    public static final Item DRAGONSLAYER_CHESTPLATE = new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE, 0.2, 0, 0, 0.1, 0, 0);
    public static final Item DRAGONSLAYER_LEGGINGS = new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS, 0.2, 0, 0, 0.05, 0, 0);
    public static final Item DRAGONSLAYER_BOOTS = new DragonslayerArmorItem(ArmorItem.Type.BOOTS, 0.1, 0, 0, 0.05, 0, 0);

    public static final Item HERO_HELMET = new HeroArmorItem(ArmorItem.Type.HELMET, 0.1, 0, 0, 0.1, 0.1, 0);
    public static final Item HERO_CHESTPLATE = new HeroArmorItem(ArmorItem.Type.CHESTPLATE, 0.1, 0, 0, 0.1, 0.1, 0);
    public static final Item HERO_LEGGINGS = new HeroArmorItem(ArmorItem.Type.LEGGINGS, 0.1, 0, 0, 0, 0, 0);
    public static final Item HERO_BOOTS = new HeroArmorItem(ArmorItem.Type.BOOTS, 0.1, 0, 0, 0, 0, 0);

    public static final Item GOLDEN_HORNS_HELMET = new GoldenHornsArmorItem(ArmorItem.Type.HELMET, 0.1, 0.03, 0, 0, 0.1, 0);
    public static final Item GOLDEN_HORNS_CHESTPLATE = new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE, 0.1, 0.01, 0, 0, 0.1, 0);
    public static final Item GOLDEN_HORNS_LEGGINGS = new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS, 0.1, 0.02, 0, 0, 0, 0);
    public static final Item GOLDEN_HORNS_BOOTS = new GoldenHornsArmorItem(ArmorItem.Type.BOOTS, 0.1, 0.04, 0, 0, 0, 0);

    public static final Item THIEF_HELMET = new ThiefArmorItem(ArmorItem.Type.HELMET, 0.1, 0.05, 0, 0, 0.1, 1.0);
    public static final Item THIEF_CHESTPLATE = new ThiefArmorItem(ArmorItem.Type.CHESTPLATE, 0.1, 0.05, 0, 0, 0.1, 0);
    public static final Item THIEF_LEGGINGS = new ThiefArmorItem(ArmorItem.Type.LEGGINGS, 0.1, 0.1, 0, 0, 0.1, 0);
    public static final Item THIEF_BOOTS = new ThiefArmorItem(ArmorItem.Type.BOOTS, 0.1, 0.1, 0, 0, 0.05, 0);

    public static final Item WANDERING_WIZARD_HELMET = new WanderingWizardArmorItem(ArmorItem.Type.HELMET, 0.1, 0.05, 0, 0, 0, 2.0);
    public static final Item WANDERING_WIZARD_CHESTPLATE = new WanderingWizardArmorItem(ArmorItem.Type.CHESTPLATE, 0.1, 0.05, 0, 0, 0, 0);
    public static final Item WANDERING_WIZARD_LEGGINGS = new WanderingWizardArmorItem(ArmorItem.Type.LEGGINGS, 0.1, 0.05, 0, 0, 0, 0);
    public static final Item WANDERING_WIZARD_BOOTS = new WanderingWizardArmorItem(ArmorItem.Type.BOOTS, 0.1, 0.05, 0, 0, 0, 0);

    public static final Item CHESS_BOARD_KNIGHT_HELMET = new ChessBoardKnightArmorItem(ArmorItem.Type.HELMET, 0.2, 0, 0, 0, 0, 0);
    public static final Item CHESS_BOARD_KNIGHT_CHESTPLATE = new ChessBoardKnightArmorItem(ArmorItem.Type.CHESTPLATE, 0.2, 0, 2.0, 0, 0, 0);
    public static final Item CHESS_BOARD_KNIGHT_LEGGINGS = new ChessBoardKnightArmorItem(ArmorItem.Type.LEGGINGS, 0.2, 0, 0, 0, 0, 0);
    public static final Item CHESS_BOARD_KNIGHT_BOOTS = new ChessBoardKnightArmorItem(ArmorItem.Type.BOOTS, 0.2, 0, 2.0, 0, 0, 0);

    public static final Item DARK_LORD_HELMET = new DarkLordArmorItem(ArmorItem.Type.HELMET, 0.1, 0, 0, 0.02, 0, 0);
    public static final Item DARK_LORD_CHESTPLATE = new DarkLordArmorItem(ArmorItem.Type.CHESTPLATE, 0.2, 0, 0, 0.03, 0, 0);
    public static final Item DARK_LORD_LEGGINGS = new DarkLordArmorItem(ArmorItem.Type.LEGGINGS, 0.2, 0, 0, 0.03, 0, 0);
    public static final Item DARK_LORD_BOOTS = new DarkLordArmorItem(ArmorItem.Type.BOOTS, 0.1, 0, 0, 0.02, 0, 0);

    public static final Item SUNSET_WINGS_HELMET = new SunsetWingsArmorItem(ArmorItem.Type.HELMET, 0.1, 0.05, 1.0, 0, 0, 0);
    public static final Item SUNSET_WINGS_CHESTPLATE = new SunsetWingsArmorItem(ArmorItem.Type.CHESTPLATE, 0.1, 0.05, 1.0, 0, 0, 0);
    public static final Item SUNSET_WINGS_LEGGINGS = new SunsetWingsArmorItem(ArmorItem.Type.LEGGINGS, 0.1, 0.05, 1.0, 0, 0, 0);
    public static final Item SUNSET_WINGS_BOOTS = new SunsetWingsArmorItem(ArmorItem.Type.BOOTS, 0.1, 0.05, 1.0, 0, 0, 0);

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
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(FantasyArmor.MOD_ID, name), item);
        ITEMS.add(item);
    }
}