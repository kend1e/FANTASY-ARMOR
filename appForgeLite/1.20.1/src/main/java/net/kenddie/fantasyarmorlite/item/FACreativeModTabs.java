package net.kenddie.fantasyarmorlite.item;

import net.kenddie.fantasyarmorlite.FantasyArmor;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
public class FACreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FantasyArmor.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FANTASY_ARMOR_TAB =
            CREATIVE_MODE_TABS.register(
                    "fa_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(FAItems.HERO_HELMET.get()))
                            .title(Component.translatable("itemGroup." + FantasyArmor.MOD_ID + ".fa_tab"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(FAItems.ECLIPSE_SOLDIER_HELMET.get());
                                pOutput.accept(FAItems.ECLIPSE_SOLDIER_CHESTPLATE.get());
                                pOutput.accept(FAItems.ECLIPSE_SOLDIER_LEGGINGS.get());
                                pOutput.accept(FAItems.ECLIPSE_SOLDIER_BOOTS.get());
                                pOutput.accept(FAItems.DRAGONSLAYER_HELMET.get());
                                pOutput.accept(FAItems.DRAGONSLAYER_CHESTPLATE.get());
                                pOutput.accept(FAItems.DRAGONSLAYER_LEGGINGS.get());
                                pOutput.accept(FAItems.DRAGONSLAYER_BOOTS.get());
                                pOutput.accept(FAItems.HERO_HELMET.get());
                                pOutput.accept(FAItems.HERO_CHESTPLATE.get());
                                pOutput.accept(FAItems.HERO_LEGGINGS.get());
                                pOutput.accept(FAItems.HERO_BOOTS.get());
                                pOutput.accept(FAItems.GOLDEN_HORNS_HELMET.get());
                                pOutput.accept(FAItems.GOLDEN_HORNS_CHESTPLATE.get());
                                pOutput.accept(FAItems.GOLDEN_HORNS_LEGGINGS.get());
                                pOutput.accept(FAItems.GOLDEN_HORNS_BOOTS.get());
                                pOutput.accept(FAItems.THIEF_HELMET.get());
                                pOutput.accept(FAItems.THIEF_CHESTPLATE.get());
                                pOutput.accept(FAItems.THIEF_LEGGINGS.get());
                                pOutput.accept(FAItems.THIEF_BOOTS.get());
                                pOutput.accept(FAItems.WANDERING_WIZARD_HELMET.get());
                                pOutput.accept(FAItems.WANDERING_WIZARD_CHESTPLATE.get());
                                pOutput.accept(FAItems.WANDERING_WIZARD_LEGGINGS.get());
                                pOutput.accept(FAItems.WANDERING_WIZARD_BOOTS.get());
                                pOutput.accept(FAItems.CHESS_BOARD_HELMET.get());
                                pOutput.accept(FAItems.CHESS_BOARD_CHESTPLATE.get());
                                pOutput.accept(FAItems.CHESS_BOARD_LEGGINGS.get());
                                pOutput.accept(FAItems.CHESS_BOARD_BOOTS.get());
                                pOutput.accept(FAItems.DARK_LORD_HELMET.get());
                                pOutput.accept(FAItems.DARK_LORD_CHESTPLATE.get());
                                pOutput.accept(FAItems.DARK_LORD_LEGGINGS.get());
                                pOutput.accept(FAItems.DARK_LORD_BOOTS.get());
                                pOutput.accept(FAItems.SUNSET_WINGS_HELMET.get());
                                pOutput.accept(FAItems.SUNSET_WINGS_CHESTPLATE.get());
                                pOutput.accept(FAItems.SUNSET_WINGS_LEGGINGS.get());
                                pOutput.accept(FAItems.SUNSET_WINGS_BOOTS.get());
                            })
                            .build()
            );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
