package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
public final class FAItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyArmor.MOD_ID);

    public static final RegistryObject<Item> MOON_CRYSTAL = ITEMS.register("moon_crystal", () -> new Item(new Item.Properties().setId(ITEMS.key("moon_crystal"))));

    private FAItems() {}

    public static void register(BusGroup eventBus) {
        ITEMS.register(eventBus);
    }
}
