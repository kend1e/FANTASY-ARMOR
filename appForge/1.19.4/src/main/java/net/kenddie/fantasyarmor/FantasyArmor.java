package net.kenddie.fantasyarmor;

import com.mojang.logging.LogUtils;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {

    public static final String MOD_ID = "fantasy_armor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FantasyArmor() {
        /*IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        if(FAConfig.exists()) {
            FAConfig.load();
        } else {
            FAConfig.save();
        }

        FAItems.register(modEventBus);
        FACreativeModTabs.register(modEventBus);*/
    }
}
