package net.kenddie.fantasyarmor.registry;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FACreativeModTabsRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FantasyArmor.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FANTASY_ARMOR_TAB =
            CREATIVE_MODE_TABS.register(
                    "fa_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(FAItemsRegistry.MOON_CRYSTAL.get()))
                            .title(Component.translatable("itemGroup." + FantasyArmor.MOD_ID + ".fa_tab"))
                            .displayItems((pParameters, pOutput) -> pOutput.accept(FAItemsRegistry.MOON_CRYSTAL.get()))
                            .build()
            );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}