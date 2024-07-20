package net.kenddie.fantasyarmor;

import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {
    public static final String MOD_ID = "fantasy_armor";

    public FantasyArmor() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FAItems.register(modEventBus);
        FACreativeModTabs.register(modEventBus);
    }
}
