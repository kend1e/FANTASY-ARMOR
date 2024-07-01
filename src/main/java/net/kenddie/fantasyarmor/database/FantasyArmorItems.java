package net.kenddie.fantasyarmor.database;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.ItemFantasyArmor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FantasyArmorItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyArmor.MODID);

    public static final RegistryObject<Item> MOON_CRYSTAL = REGISTRY.register("moon_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<ArmorItem> ECLIPSE_SOLDIER_HELMET = REGISTRY.register("eclipse_soldier_helmet", () -> new ItemFantasyArmor(ArmorItem.Type.HELMET));
    public static final RegistryObject<ArmorItem> ECLIPSE_SOLDIER_CHESTPLATE = REGISTRY.register("eclipse_soldier_chestplate", () -> new ItemFantasyArmor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<ArmorItem> ECLIPSE_SOLDIER_LEGGINGS = REGISTRY.register("eclipse_soldier_leggings", () -> new ItemFantasyArmor(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<ArmorItem> ECLIPSE_SOLDIER_BOOTS = REGISTRY.register("eclipse_soldier_boots", () -> new ItemFantasyArmor(ArmorItem.Type.BOOTS));

    public static void register(IEventBus modEventBus) {
        REGISTRY.register(modEventBus);
    }
}
