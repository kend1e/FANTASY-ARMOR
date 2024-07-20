package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
public final class FAItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyArmor.MOD_ID);

    public static final RegistryObject<Item> MOON_CRYSTAL = ITEMS.register("moon_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ECLIPSE_SOLDIER_HELMET = ITEMS.register("eclipse_soldier_helmet", () -> new FAArmorItem(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_CHESTPLATE = ITEMS.register("eclipse_soldier_chestplate", () -> new FAArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_LEGGINGS = ITEMS.register("eclipse_soldier_leggings", () -> new FAArmorItem(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ECLIPSE_SOLDIER_BOOTS = ITEMS.register("eclipse_soldier_boots", () -> new FAArmorItem(ArmorItem.Type.BOOTS));

    private FAItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
