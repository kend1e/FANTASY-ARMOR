package net.kenddie.fantasyarmor;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorEffectHandler;
import org.slf4j.Logger;

public class FantasyArmor implements ModInitializer {
	public static final String MOD_ID = "fantasy_armor";
	public static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public void onInitialize() {
		if(FAConfig.exists()) {
			FAConfig.load();
		} else {
			FAConfig.save();
		}

		FAItems.register();
		FACreativeModTabs.register();
		FAArmorEffectHandler.register();
	}
}