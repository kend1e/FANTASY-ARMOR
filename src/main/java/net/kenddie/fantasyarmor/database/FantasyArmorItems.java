package net.kenddie.fantasyarmor.database;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FantasyArmorItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyArmor.MODID);

    public static final RegistryObject<Item> MOON_CRYSTAL = REGISTRY.register("moon_crystal", () -> new Item(new Item.Properties()));

    public static void register(IEventBus modEventBus) {
        REGISTRY.register(modEventBus);
    }
}
