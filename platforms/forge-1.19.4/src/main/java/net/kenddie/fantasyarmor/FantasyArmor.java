package net.kenddie.fantasyarmor;

import net.kenddie.fantasyarmor.config.FAArmorConfig;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {

    public static final String MOD_ID = net.kenddie.fantasyarmor.shared.ModConstants.MOD_ID;

    public static boolean isEpicFightLoaded;

    public FantasyArmor(FMLJavaModLoadingContext modLoadingContext) {
        isEpicFightLoaded = net.minecraftforge.fml.ModList.get().isLoaded("epicfight");
        IEventBus modEventBus = modLoadingContext.getModEventBus();

        modLoadingContext.registerConfig(ModConfig.Type.SERVER, FAConfig.SPEC, "fantasy_armor-server.toml");
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, FAConfig.CLIENT_SPEC, "fantasy_armor-client.toml");
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, FAArmorConfig.SPEC, "fantasy_armor-armor_attributes.toml");
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, FAArmorEffectsConfig.SPEC, "fantasy_armor-armor_effects.toml");

        FAItems.register(modEventBus);
        FAArmorItems.register(modEventBus);
        modEventBus.addListener(this::onConfigLoad);
        modEventBus.addListener(this::onConfigReload);
        modEventBus.addListener(this::onConfigUnload);
    }

    @SubscribeEvent
    public void onConfigLoad(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == FAConfig.CLIENT_SPEC) {
            FAConfig.applyClientValues();
        }
        if (event.getConfig().getSpec() == FAConfig.SPEC) {
            FAConfig.applyConfigValues();
        }
    }

    @SubscribeEvent
    public void onConfigReload(final ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == FAConfig.CLIENT_SPEC) {
            FAConfig.applyClientValues();
        }
        if (event.getConfig().getSpec() == FAConfig.SPEC) {
            FAConfig.applyConfigValues();
        }
    }
    private void onConfigUnload(final ModConfigEvent.Unloading event) {
        if (event.getConfig().getSpec() == FAConfig.SPEC) FAConfig.resetServerValues();
    }
}
