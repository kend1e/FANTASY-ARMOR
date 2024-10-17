package net.kenddie.fantasyarmor;

import net.fabricmc.api.ModInitializer;
import net.kenddie.fantasyarmor.item.FACreativeModTabs;
import net.kenddie.fantasyarmor.item.FAItems;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorEffectHandler;

public class FantasyArmor implements ModInitializer {
	public static final String MOD_ID = "fantasy_armor";

	@Override
	public void onInitialize() {
		FAItems.register();
		FACreativeModTabs.register();
		FAArmorEffectHandler.register();
	}
}