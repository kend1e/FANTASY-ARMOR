package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings({"unused"})
public final class FAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FantasyArmor.MOD_ID);

    public static final DeferredItem<Item> MOON_CRYSTAL = ITEMS.register("moon_crystal", () -> new Item(new Item.Properties()));

    private FAItems() {}

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
