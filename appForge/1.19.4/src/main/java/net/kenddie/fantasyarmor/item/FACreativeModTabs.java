package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FantasyArmor.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FACreativeModTabs {
    public static CreativeModeTab FANTASY_ARMOR_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        FANTASY_ARMOR_TAB = event.registerCreativeModeTab(new ResourceLocation(FantasyArmor.MOD_ID, "fa_tab"),
                builder -> builder.icon(() -> new ItemStack(FAItems.HERO_HELMET.get()))
                        .title(Component.translatable("itemGroup." + FantasyArmor.MOD_ID + ".fa_tab")));
    }

    @SubscribeEvent
    public static void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == FACreativeModTabs.FANTASY_ARMOR_TAB) {
            event.accept(FAItems.MOON_CRYSTAL.get());
            event.accept(FAItems.ECLIPSE_SOLDIER_HELMET.get());
            event.accept(FAItems.ECLIPSE_SOLDIER_CHESTPLATE.get());
            event.accept(FAItems.ECLIPSE_SOLDIER_LEGGINGS.get());
            event.accept(FAItems.ECLIPSE_SOLDIER_BOOTS.get());
            event.accept(FAItems.DRAGONSLAYER_HELMET.get());
            event.accept(FAItems.DRAGONSLAYER_CHESTPLATE.get());
            event.accept(FAItems.DRAGONSLAYER_LEGGINGS.get());
            event.accept(FAItems.DRAGONSLAYER_BOOTS.get());
            event.accept(FAItems.HERO_HELMET.get());
            event.accept(FAItems.HERO_CHESTPLATE.get());
            event.accept(FAItems.HERO_LEGGINGS.get());
            event.accept(FAItems.HERO_BOOTS.get());
            event.accept(FAItems.GOLDEN_HORNS_HELMET.get());
            event.accept(FAItems.GOLDEN_HORNS_CHESTPLATE.get());
            event.accept(FAItems.GOLDEN_HORNS_LEGGINGS.get());
            event.accept(FAItems.GOLDEN_HORNS_BOOTS.get());
            event.accept(FAItems.THIEF_HELMET.get());
            event.accept(FAItems.THIEF_CHESTPLATE.get());
            event.accept(FAItems.THIEF_LEGGINGS.get());
            event.accept(FAItems.THIEF_BOOTS.get());
            event.accept(FAItems.WANDERING_WIZARD_HELMET.get());
            event.accept(FAItems.WANDERING_WIZARD_CHESTPLATE.get());
            event.accept(FAItems.WANDERING_WIZARD_LEGGINGS.get());
            event.accept(FAItems.WANDERING_WIZARD_BOOTS.get());
            event.accept(FAItems.CHESS_BOARD_KNIGHT_HELMET.get());
            event.accept(FAItems.CHESS_BOARD_KNIGHT_CHESTPLATE.get());
            event.accept(FAItems.CHESS_BOARD_KNIGHT_LEGGINGS.get());
            event.accept(FAItems.CHESS_BOARD_KNIGHT_BOOTS.get());
            event.accept(FAItems.DARK_LORD_HELMET.get());
            event.accept(FAItems.DARK_LORD_CHESTPLATE.get());
            event.accept(FAItems.DARK_LORD_LEGGINGS.get());
            event.accept(FAItems.DARK_LORD_BOOTS.get());
            event.accept(FAItems.SUNSET_WINGS_HELMET.get());
            event.accept(FAItems.SUNSET_WINGS_CHESTPLATE.get());
            event.accept(FAItems.SUNSET_WINGS_LEGGINGS.get());
            event.accept(FAItems.SUNSET_WINGS_BOOTS.get());
            event.accept(FAItems.FOG_GUARD_HELMET.get());
            event.accept(FAItems.FOG_GUARD_CHESTPLATE.get());
            event.accept(FAItems.FOG_GUARD_LEGGINGS.get());
            event.accept(FAItems.FOG_GUARD_BOOTS.get());
            event.accept(FAItems.DARK_COVER_HELMET.get());
            event.accept(FAItems.DARK_COVER_CHESTPLATE.get());
            event.accept(FAItems.DARK_COVER_LEGGINGS.get());
            event.accept(FAItems.DARK_COVER_BOOTS.get());
            event.accept(FAItems.SPARK_OF_DAWN_HELMET.get());
            event.accept(FAItems.SPARK_OF_DAWN_CHESTPLATE.get());
            event.accept(FAItems.SPARK_OF_DAWN_LEGGINGS.get());
            event.accept(FAItems.SPARK_OF_DAWN_BOOTS.get());
            event.accept(FAItems.GOLDEN_EXECUTION_HELMET.get());
            event.accept(FAItems.GOLDEN_EXECUTION_CHESTPLATE.get());
            event.accept(FAItems.GOLDEN_EXECUTION_LEGGINGS.get());
            event.accept(FAItems.GOLDEN_EXECUTION_BOOTS.get());
            event.accept(FAItems.FORGOTTEN_TRACE_HELMET.get());
            event.accept(FAItems.FORGOTTEN_TRACE_CHESTPLATE.get());
            event.accept(FAItems.FORGOTTEN_TRACE_LEGGINGS.get());
            event.accept(FAItems.FORGOTTEN_TRACE_BOOTS.get());
            event.accept(FAItems.REDEEMER_HELMET.get());
            event.accept(FAItems.REDEEMER_CHESTPLATE.get());
            event.accept(FAItems.REDEEMER_LEGGINGS.get());
            event.accept(FAItems.REDEEMER_BOOTS.get());
            event.accept(FAItems.TWINNED_HELMET.get());
            event.accept(FAItems.TWINNED_CHESTPLATE.get());
            event.accept(FAItems.TWINNED_LEGGINGS.get());
            event.accept(FAItems.TWINNED_BOOTS.get());
            event.accept(FAItems.GILDED_HUNT_HELMET.get());
            event.accept(FAItems.GILDED_HUNT_CHESTPLATE.get());
            event.accept(FAItems.GILDED_HUNT_LEGGINGS.get());
            event.accept(FAItems.GILDED_HUNT_BOOTS.get());
            event.accept(FAItems.LADY_MARIA_HELMET.get());
            event.accept(FAItems.LADY_MARIA_CHESTPLATE.get());
            event.accept(FAItems.LADY_MARIA_LEGGINGS.get());
            event.accept(FAItems.LADY_MARIA_BOOTS.get());
            event.accept(FAItems.CRUCIBLE_KNIGHT_HELMET.get());
            event.accept(FAItems.CRUCIBLE_KNIGHT_CHESTPLATE.get());
            event.accept(FAItems.CRUCIBLE_KNIGHT_LEGGINGS.get());
            event.accept(FAItems.CRUCIBLE_KNIGHT_BOOTS.get());
            event.accept(FAItems.RONIN_HELMET.get());
            event.accept(FAItems.RONIN_CHESTPLATE.get());
            event.accept(FAItems.RONIN_LEGGINGS.get());
            event.accept(FAItems.RONIN_BOOTS.get());
            event.accept(FAItems.EVENING_GHOST_HELMET.get());
            event.accept(FAItems.EVENING_GHOST_CHESTPLATE.get());
            event.accept(FAItems.EVENING_GHOST_LEGGINGS.get());
            event.accept(FAItems.EVENING_GHOST_BOOTS.get());
        }
    }
}