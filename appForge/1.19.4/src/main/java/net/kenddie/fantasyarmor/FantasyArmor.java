package net.kenddie.fantasyarmor;

import com.mojang.logging.LogUtils;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {

    public static final String MOD_ID = "fantasy_armor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FantasyArmor(FMLJavaModLoadingContext modLoadingContext) {
        IEventBus modEventBus = modLoadingContext.getModEventBus();

        if(FAConfig.exists()) {
            FAConfig.load();
        } else {
            FAConfig.save();
        }

        FAItems.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
