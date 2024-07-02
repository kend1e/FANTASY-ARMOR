package net.kenddie.fantasyarmor;

import net.kenddie.fantasyarmor.registry.FACreativeModTabsRegistry;
import net.kenddie.fantasyarmor.registry.FAItemsRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {

    public static final String MOD_ID = "fantasy_armor";

    public FantasyArmor() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FACreativeModTabsRegistry.register(modEventBus);
        FAItemsRegistry.register(modEventBus);
    }
}
