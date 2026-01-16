package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collection;

public final class FAItems {
    public static final Collection<Item> ITEMS = new ArrayList<>();

    public static final Item MOON_CRYSTAL = new Item(new Item.Properties());

    private FAItems() {
    }

    public static void register() {
        register("moon_crystal", MOON_CRYSTAL);
    }

    private static void register(String name, Item item) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, name);
        Registry.register(BuiltInRegistries.ITEM, id, item);
        ITEMS.add(item);
    }
}