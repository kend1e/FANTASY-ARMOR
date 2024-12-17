package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.*;
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

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_HELMET = ITEMS.register("eclipse_soldier_helmet", () -> new EclipseSoldierArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.1, 0, 0, 0, 0)));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_CHESTPLATE = ITEMS.register("eclipse_soldier_chestplate", () -> new EclipseSoldierArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0, 0.1, 0)));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_LEGGINGS = ITEMS.register("eclipse_soldier_leggings", () -> new EclipseSoldierArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0, 0, 0)));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_BOOTS = ITEMS.register("eclipse_soldier_boots", () -> new EclipseSoldierArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0)));

    public static final RegistryObject<Item> DRAGONSLAYER_HELMET = ITEMS.register("dragonslayer_helmet", () -> new DragonslayerArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 0, 0, 0, 0)));
    public static final RegistryObject<Item> DRAGONSLAYER_CHESTPLATE = ITEMS.register("dragonslayer_chestplate", () -> new DragonslayerArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0.1, 0, 0)));
    public static final RegistryObject<Item> DRAGONSLAYER_LEGGINGS = ITEMS.register("dragonslayer_leggings", () -> new DragonslayerArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0.05, 0, 0)));
    public static final RegistryObject<Item> DRAGONSLAYER_BOOTS = ITEMS.register("dragonslayer_boots", () -> new DragonslayerArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.05, 0, 0)));

    public static final RegistryObject<Item> HERO_HELMET = ITEMS.register("hero_helmet", () -> new HeroArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.1, 0.1, 0)));
    public static final RegistryObject<Item> HERO_CHESTPLATE = ITEMS.register("hero_chestplate", () -> new HeroArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 0, 0.1, 0.1, 0)));
    public static final RegistryObject<Item> HERO_LEGGINGS = ITEMS.register("hero_leggings", () -> new HeroArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0, 0, 0, 0, 0)));
    public static final RegistryObject<Item> HERO_BOOTS = ITEMS.register("hero_boots", () -> new HeroArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0)));

    public static final RegistryObject<Item> GOLDEN_HORNS_HELMET = ITEMS.register("golden_horns_helmet", () -> new GoldenHornsArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.03, 0, 0, 0.1, 0)));
    public static final RegistryObject<Item> GOLDEN_HORNS_CHESTPLATE = ITEMS.register("golden_horns_chestplate", () -> new GoldenHornsArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.01, 0, 0, 0.1, 0)));
    public static final RegistryObject<Item> GOLDEN_HORNS_LEGGINGS = ITEMS.register("golden_horns_leggings", () -> new GoldenHornsArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.02, 0, 0, 0, 0)));
    public static final RegistryObject<Item> GOLDEN_HORNS_BOOTS = ITEMS.register("golden_horns_boots", () -> new GoldenHornsArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.04, 0, 0, 0, 0)));

    public static final RegistryObject<Item> THIEF_HELMET = ITEMS.register("thief_helmet", () -> new ThiefArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0.1, 1.0)));
    public static final RegistryObject<Item> THIEF_CHESTPLATE = ITEMS.register("thief_chestplate", () -> new ThiefArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 0, 0, 0.1, 0)));
    public static final RegistryObject<Item> THIEF_LEGGINGS = ITEMS.register("thief_leggings", () -> new ThiefArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.1, 0, 0, 0.1, 0)));
    public static final RegistryObject<Item> THIEF_BOOTS = ITEMS.register("thief_boots", () -> new ThiefArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.1, 0, 0, 0.05, 0)));

    public static final RegistryObject<Item> WANDERING_WIZARD_HELMET = ITEMS.register("wandering_wizard_helmet", () -> new WanderingWizardArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 2.0)));
    public static final RegistryObject<Item> WANDERING_WIZARD_CHESTPLATE = ITEMS.register("wandering_wizard_chestplate", () -> new WanderingWizardArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 0, 0, 0, 0)));
    public static final RegistryObject<Item> WANDERING_WIZARD_LEGGINGS = ITEMS.register("wandering_wizard_leggings", () -> new WanderingWizardArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.05, 0, 0, 0, 0)));
    public static final RegistryObject<Item> WANDERING_WIZARD_BOOTS = ITEMS.register("wandering_wizard_boots", () -> new WanderingWizardArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 0)));

    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_HELMET = ITEMS.register("chess_board_knight_helmet", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 0, 0, 0, 0)));
    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_CHESTPLATE = ITEMS.register("chess_board_knight_chestplate", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 2.0, 0, 0, 0)));
    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_LEGGINGS = ITEMS.register("chess_board_knight_leggings", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0, 0, 0)));
    public static final RegistryObject<Item> CHESS_BOARD_KNIGHT_BOOTS = ITEMS.register("chess_board_knight_boots", () -> new ChessBoardKnightArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 2.0, 0, 0, 0)));

    public static final RegistryObject<Item> DARK_LORD_HELMET = ITEMS.register("dark_lord_helmet", () -> new DarkLordArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.02, 0, 0)));
    public static final RegistryObject<Item> DARK_LORD_CHESTPLATE = ITEMS.register("dark_lord_chestplate", () -> new DarkLordArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0.03, 0, 0)));
    public static final RegistryObject<Item> DARK_LORD_LEGGINGS = ITEMS.register("dark_lord_leggings", () -> new DarkLordArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0.03, 0, 0)));
    public static final RegistryObject<Item> DARK_LORD_BOOTS = ITEMS.register("dark_lord_boots", () -> new DarkLordArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.02, 0, 0)));

    public static final RegistryObject<Item> SUNSET_WINGS_HELMET = ITEMS.register("sunset_wings_helmet", () -> new SunsetWingsArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0)));
    public static final RegistryObject<Item> SUNSET_WINGS_CHESTPLATE = ITEMS.register("sunset_wings_chestplate", () -> new SunsetWingsArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0)));
    public static final RegistryObject<Item> SUNSET_WINGS_LEGGINGS = ITEMS.register("sunset_wings_leggings", () -> new SunsetWingsArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0)));
    public static final RegistryObject<Item> SUNSET_WINGS_BOOTS = ITEMS.register("sunset_wings_boots", () -> new SunsetWingsArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 1.0, 0, 0, 0)));

    public static final RegistryObject<Item> FOG_GUARD_HELMET = ITEMS.register("fog_guard_helmet", () -> new FogGuardArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0, 0.1, 0)));
    public static final RegistryObject<Item> FOG_GUARD_CHESTPLATE = ITEMS.register("fog_guard_chestplate", () -> new FogGuardArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 1.0, 0.01, 0, 0)));
    public static final RegistryObject<Item> FOG_GUARD_LEGGINGS = ITEMS.register("fog_guard_leggings", () -> new FogGuardArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0, 1.0, 0.02, 0, 0)));
    public static final RegistryObject<Item> FOG_GUARD_BOOTS = ITEMS.register("fog_guard_boots", () -> new FogGuardArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0.03, 0, 0)));

    public static final RegistryObject<Item> DARK_COVER_HELMET = ITEMS.register("dark_cover_helmet", () -> new DarkCoverArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 2.0, 0, 0, 0)));
    public static final RegistryObject<Item> DARK_COVER_CHESTPLATE = ITEMS.register("dark_cover_chestplate", () -> new DarkCoverArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 0, 0.1, 0, 0)));
    public static final RegistryObject<Item> DARK_COVER_LEGGINGS = ITEMS.register("dark_cover_leggings", () -> new DarkCoverArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 1.0, 0, 0, 0)));
    public static final RegistryObject<Item> DARK_COVER_BOOTS = ITEMS.register("dark_cover_boots", () -> new DarkCoverArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0)));

    public static final RegistryObject<Item> SPARK_OF_DAWN_HELMET = ITEMS.register("spark_of_dawn_helmet", () -> new SparkOfDawnArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0, 0.1, 0)));
    public static final RegistryObject<Item> SPARK_OF_DAWN_CHESTPLATE = ITEMS.register("spark_of_dawn_chestplate", () -> new SparkOfDawnArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 0, 0.05, 0, 0)));
    public static final RegistryObject<Item> SPARK_OF_DAWN_LEGGINGS = ITEMS.register("spark_of_dawn_leggings", () -> new SparkOfDawnArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0.05, 1.0, 0, 0.05, 0)));
    public static final RegistryObject<Item> SPARK_OF_DAWN_BOOTS = ITEMS.register("spark_of_dawn_boots", () -> new SparkOfDawnArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 0)));

    public static final RegistryObject<Item> GOLDEN_EXECUTION_HELMET = ITEMS.register("golden_execution_helmet", () -> new GoldenExecutionArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 2.0, 0.05, 0, 0)));
    public static final RegistryObject<Item> GOLDEN_EXECUTION_CHESTPLATE = ITEMS.register("golden_execution_chestplate", () -> new GoldenExecutionArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.2, 0, 4.0, 0.05, 0, 0)));
    public static final RegistryObject<Item> GOLDEN_EXECUTION_LEGGINGS = ITEMS.register("golden_execution_leggings", () -> new GoldenExecutionArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 1.0, 0, 0, 0)));
    public static final RegistryObject<Item> GOLDEN_EXECUTION_BOOTS = ITEMS.register("golden_execution_boots", () -> new GoldenExecutionArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 1.0, 0, 0, 0)));

    public static final RegistryObject<Item> FORGOTTEN_TRACE_HELMET = ITEMS.register("forgotten_trace_helmet", () -> new ForgottenTraceArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0.05, 0, 0, 0, 1.0)));
    public static final RegistryObject<Item> FORGOTTEN_TRACE_CHESTPLATE = ITEMS.register("forgotten_trace_chestplate", () -> new ForgottenTraceArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.03, 2.0, 0, 0, 0)));
    public static final RegistryObject<Item> FORGOTTEN_TRACE_LEGGINGS = ITEMS.register("forgotten_trace_leggings", () -> new ForgottenTraceArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.02, 1.0, 0, 0, 0)));
    public static final RegistryObject<Item> FORGOTTEN_TRACE_BOOTS = ITEMS.register("forgotten_trace_boots", () -> new ForgottenTraceArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.2, 0.05, 1.0, 0, 0, 1.0)));

    public static final RegistryObject<Item> REDEEMER_HELMET = ITEMS.register("redeemer_helmet", () -> new RedeemerArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 2.0, 0, 0.05, 1.0)));
    public static final RegistryObject<Item> REDEEMER_CHESTPLATE = ITEMS.register("redeemer_chestplate", () -> new RedeemerArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 2.0, 0.03, 0.02, 1.0)));
    public static final RegistryObject<Item> REDEEMER_LEGGINGS = ITEMS.register("redeemer_leggings", () -> new RedeemerArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0.04, 0, 0, 0, 0)));
    public static final RegistryObject<Item> REDEEMER_BOOTS = ITEMS.register("redeemer_boots", () -> new RedeemerArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.02, 0, 0, 0, 0)));

    public static final RegistryObject<Item> TWINNED_HELMET = ITEMS.register("twinned_helmet", () -> new TwinnedArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.2, 0, 0, 0.1, 0.02, 0)));
    public static final RegistryObject<Item> TWINNED_CHESTPLATE = ITEMS.register("twinned_chestplate", () -> new TwinnedArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 0, 0.02, 0.02, 0)));
    public static final RegistryObject<Item> TWINNED_LEGGINGS = ITEMS.register("twinned_leggings", () -> new TwinnedArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.2, 0, 0, 0.05, 0.02, 0)));
    public static final RegistryObject<Item> TWINNED_BOOTS = ITEMS.register("twinned_boots", () -> new TwinnedArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0.05, 0.02, 0)));

    public static final RegistryObject<Item> GILDED_HUNT_HELMET = ITEMS.register("gilded_hunt_helmet", () -> new GildedHuntArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 1.0, 0.1, 0, 0)));
    public static final RegistryObject<Item> GILDED_HUNT_CHESTPLATE = ITEMS.register("gilded_hunt_chestplate", () -> new GildedHuntArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0, 1.0, 0.1, 0, 0)));
    public static final RegistryObject<Item> GILDED_HUNT_LEGGINGS = ITEMS.register("gilded_hunt_leggings", () -> new GildedHuntArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0, 0, 0, 0, 0)));
    public static final RegistryObject<Item> GILDED_HUNT_BOOTS = ITEMS.register("gilded_hunt_boots", () -> new GildedHuntArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0, 0)));

    public static final RegistryObject<Item> LADY_MARIA_HELMET = ITEMS.register("lady_maria_helmet", () -> new LadyMariaArmorItem(ArmorItem.Type.HELMET, new FAArmorAttributes(3.0, 3.0, 0.1, 0, 0, 0, 0.05, 1.0)));
    public static final RegistryObject<Item> LADY_MARIA_CHESTPLATE = ITEMS.register("lady_maria_chestplate", () -> new LadyMariaArmorItem(ArmorItem.Type.CHESTPLATE, new FAArmorAttributes(8.0, 3.0, 0.1, 0.05, 0, 0, 0.05, 0)));
    public static final RegistryObject<Item> LADY_MARIA_LEGGINGS = ITEMS.register("lady_maria_leggings", () -> new LadyMariaArmorItem(ArmorItem.Type.LEGGINGS, new FAArmorAttributes(6.0, 3.0, 0.1, 0.05, 0, 0, 0, 0)));
    public static final RegistryObject<Item> LADY_MARIA_BOOTS = ITEMS.register("lady_maria_boots", () -> new LadyMariaArmorItem(ArmorItem.Type.BOOTS, new FAArmorAttributes(3.0, 3.0, 0.1, 0.03, 0, 0, 0, 0)));

    private FAItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
