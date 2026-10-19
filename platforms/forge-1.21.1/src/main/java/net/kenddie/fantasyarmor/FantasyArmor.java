package net.kenddie.fantasyarmor;

import net.kenddie.fantasyarmor.config.FAClientConfig;
import net.kenddie.fantasyarmor.config.FAArmorConfig;
import net.kenddie.fantasyarmor.config.FAArmorEffectsConfig;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FantasyArmor.MOD_ID)
public class FantasyArmor {

    public static final String MOD_ID = net.kenddie.fantasyarmor.shared.ModConstants.MOD_ID;
    public static final String EPIC_FIGHT_MOD_ID = "epicfight";
    public static boolean isEpicFightLoaded;

    @SuppressWarnings("removal")
    public FantasyArmor() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modLoadingContext.registerConfig(ModConfig.Type.SERVER, FAConfig.SPEC, "fantasy_armor-server.toml");
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, FAClientConfig.SPEC, "fantasy_armor-client.toml");
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, FAArmorConfig.SPEC, "fantasy_armor-armor_attributes.toml");
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, FAArmorEffectsConfig.SPEC, "fantasy_armor-armor_effects.toml");

        FAItems.register(modEventBus);
        FAArmorItems.register(modEventBus);
        FACreativeModTabs.register(modEventBus);

        isEpicFightLoaded = ModList.get().isLoaded(EPIC_FIGHT_MOD_ID);

    }
}
