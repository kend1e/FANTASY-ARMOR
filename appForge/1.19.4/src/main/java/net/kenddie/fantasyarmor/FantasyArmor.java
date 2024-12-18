package net.kenddie.fantasyarmor;

import com.mojang.logging.LogUtils;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {

    public static final String MOD_ID = "fantasy_armor";

    public FantasyArmor(FMLJavaModLoadingContext modLoadingContext) {
        IEventBus modEventBus = modLoadingContext.getModEventBus();

        modLoadingContext.registerConfig(ModConfig.Type.COMMON, FAConfig.SPEC, "fantasy_armor-common.toml");

        FAItems.register(modEventBus);
        modEventBus.addListener(this::onConfigLoad);
        modEventBus.addListener(this::onConfigReload);
    }

    @SubscribeEvent
    public void onConfigLoad(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == FAConfig.SPEC) {
            FAConfig.applyConfigValues();
        }
    }

    @SubscribeEvent
    public void onConfigReload(final ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == FAConfig.SPEC) {
            FAConfig.applyConfigValues();
        }
    }
}
