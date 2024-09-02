package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.*;
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

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_HELMET = ITEMS.register("eclipse_soldier_helmet", () -> new EclipseSoldierArmorItem(ArmorItem.Type.HELMET,    0.1, 0.1, 0, 0, 0, 0));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_CHESTPLATE = ITEMS.register("eclipse_soldier_chestplate", () -> new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE,    0.2, 0, 0, 0, 0.1, 0));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_LEGGINGS = ITEMS.register("eclipse_soldier_leggings", () -> new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS,    0.2, 0, 0, 0, 0, 0));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_BOOTS = ITEMS.register("eclipse_soldier_boots", () -> new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS,    0.1, 0, 0, 0, 0, 0));

    public static final RegistryObject<Item> DRAGONSLAYER_HELMET = ITEMS.register("dragonslayer_helmet", () -> new DragonslayerArmorItem(ArmorItem.Type.HELMET,    0.2, 0, 0, 0, 0, 0));
    public static final RegistryObject<Item> DRAGONSLAYER_CHESTPLATE = ITEMS.register("dragonslayer_chestplate", () -> new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE,    0.2, 0, 0, 0.1, 0, 0));
    public static final RegistryObject<Item> DRAGONSLAYER_LEGGINGS = ITEMS.register("dragonslayer_leggings", () -> new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS,    0.2, 0, 0, 0.05, 0, 0));
    public static final RegistryObject<Item> DRAGONSLAYER_BOOTS = ITEMS.register("dragonslayer_boots", () -> new DragonslayerArmorItem(ArmorItem.Type.BOOTS,    0.1, 0, 0, 0.05, 0, 0));

    public static final RegistryObject<Item> HERO_HELMET = ITEMS.register("hero_helmet", () -> new HeroArmorItem(ArmorItem.Type.HELMET,    0.1, 0, 0, 0.1, 0.1, 0));
    public static final RegistryObject<Item> HERO_CHESTPLATE = ITEMS.register("hero_chestplate", () -> new HeroArmorItem(ArmorItem.Type.CHESTPLATE,    0.1, 0, 0, 0.1, 0.1, 0));
    public static final RegistryObject<Item> HERO_LEGGINGS = ITEMS.register("hero_leggings", () -> new HeroArmorItem(ArmorItem.Type.LEGGINGS,    0.1, 0, 0, 0, 0, 0));
    public static final RegistryObject<Item> HERO_BOOTS = ITEMS.register("hero_boots", () -> new HeroArmorItem(ArmorItem.Type.BOOTS,    0.1, 0, 0, 0, 0, 0));

    public static final RegistryObject<Item> GOLDEN_HORNS_HELMET = ITEMS.register("golden_horns_helmet", () -> new GoldenHornsArmorItem(ArmorItem.Type.HELMET,    0.1, 0.03, 0, 0, 0.1, 0));
    public static final RegistryObject<Item> GOLDEN_HORNS_CHESTPLATE = ITEMS.register("golden_horns_chestplate", () -> new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE,    0.1, 0.01, 0, 0, 0.1, 0));
    public static final RegistryObject<Item> GOLDEN_HORNS_LEGGINGS = ITEMS.register("golden_horns_leggings", () -> new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS,    0.1, 0.02, 0, 0, 0, 0));
    public static final RegistryObject<Item> GOLDEN_HORNS_BOOTS = ITEMS.register("golden_horns_boots", () -> new GoldenHornsArmorItem(ArmorItem.Type.BOOTS,    0.1, 0.04, 0, 0, 0, 0));

    public static final RegistryObject<Item> THIEF_HELMET = ITEMS.register("thief_helmet", () -> new ThiefArmorItem(ArmorItem.Type.HELMET,    0.1, 0.05, 0, 0, 0.1, 1.0));
    public static final RegistryObject<Item> THIEF_CHESTPLATE = ITEMS.register("thief_chestplate", () -> new ThiefArmorItem(ArmorItem.Type.CHESTPLATE,    0.1, 0.05, 0, 0, 0.1, 0));
    public static final RegistryObject<Item> THIEF_LEGGINGS = ITEMS.register("thief_leggings", () -> new ThiefArmorItem(ArmorItem.Type.LEGGINGS,    0.1, 0.1, 0, 0, 0.1, 0));
    public static final RegistryObject<Item> THIEF_BOOTS = ITEMS.register("thief_boots", () -> new ThiefArmorItem(ArmorItem.Type.BOOTS,    0.1, 0.1, 0, 0, 0.05, 0));

    public static final RegistryObject<Item> WANDERING_WIZARD_HELMET = ITEMS.register("wandering_wizard_helmet", () -> new WanderingWizardArmorItem(ArmorItem.Type.HELMET,    0.1, 0.05, 0, 0, 0, 2.0));
    public static final RegistryObject<Item> WANDERING_WIZARD_CHESTPLATE = ITEMS.register("wandering_wizard_chestplate", () -> new WanderingWizardArmorItem(ArmorItem.Type.CHESTPLATE,    0.1, 0.05, 0, 0, 0, 0));
    public static final RegistryObject<Item> WANDERING_WIZARD_LEGGINGS = ITEMS.register("wandering_wizard_leggings", () -> new WanderingWizardArmorItem(ArmorItem.Type.LEGGINGS,    0.1, 0.05, 0, 0, 0, 0));
    public static final RegistryObject<Item> WANDERING_WIZARD_BOOTS = ITEMS.register("wandering_wizard_boots", () -> new WanderingWizardArmorItem(ArmorItem.Type.BOOTS,    0.1, 0.05, 0, 0, 0, 0));

    public static final RegistryObject<Item> CHESS_BOARD_HELMET = ITEMS.register("chess_board_knight_helmet", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.HELMET,    0.2, 0, 0, 0, 0, 0));
    public static final RegistryObject<Item> CHESS_BOARD_CHESTPLATE = ITEMS.register("chess_board_knight_chestplate", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.CHESTPLATE,    0.2, 0, 2.0, 0, 0, 0));
    public static final RegistryObject<Item> CHESS_BOARD_LEGGINGS = ITEMS.register("chess_board_knight_leggings", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.LEGGINGS,    0.2, 0, 0, 0, 0, 0));
    public static final RegistryObject<Item> CHESS_BOARD_BOOTS = ITEMS.register("chess_board_knight_boots", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.BOOTS,    0.2, 0, 2.0, 0, 0, 0));

    public static final RegistryObject<Item> DARK_LORD_HELMET = ITEMS.register("dark_lord_helmet", () -> new DarkLordArmorItem(ArmorItem.Type.HELMET,    0.1, 0, 0, 0.02, 0, 0));
    public static final RegistryObject<Item> DARK_LORD_CHESTPLATE = ITEMS.register("dark_lord_chestplate", () -> new DarkLordArmorItem(ArmorItem.Type.CHESTPLATE,    0.2, 0, 0, 0.03, 0, 0));
    public static final RegistryObject<Item> DARK_LORD_LEGGINGS = ITEMS.register("dark_lord_leggings", () -> new DarkLordArmorItem(ArmorItem.Type.LEGGINGS,    0.2, 0, 0, 0.03, 0, 0));
    public static final RegistryObject<Item> DARK_LORD_BOOTS = ITEMS.register("dark_lord_boots", () -> new DarkLordArmorItem(ArmorItem.Type.BOOTS,    0.1, 0, 0, 0.02, 0, 0));

    public static final RegistryObject<Item> SUNSET_WINGS_HELMET = ITEMS.register("sunset_wings_helmet", () -> new SunsetWingsArmorItem(ArmorItem.Type.HELMET,  0.1, 0.05, 1.0, 0, 0, 0));
    public static final RegistryObject<Item> SUNSET_WINGS_CHESTPLATE = ITEMS.register("sunset_wings_chestplate", () -> new SunsetWingsArmorItem(ArmorItem.Type.CHESTPLATE,  0.1, 0.05, 1.0, 0, 0, 0));
    public static final RegistryObject<Item> SUNSET_WINGS_LEGGINGS = ITEMS.register("sunset_wings_leggings", () -> new SunsetWingsArmorItem(ArmorItem.Type.LEGGINGS,  0.1, 0.05, 1.0, 0, 0, 0));
    public static final RegistryObject<Item> SUNSET_WINGS_BOOTS = ITEMS.register("sunset_wings_boots", () -> new SunsetWingsArmorItem(ArmorItem.Type.BOOTS,  0.1, 0.05, 1.0, 0, 0, 0));

    private FAItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
