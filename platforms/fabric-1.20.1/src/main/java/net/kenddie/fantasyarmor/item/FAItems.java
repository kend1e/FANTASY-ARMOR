package net.kenddie.fantasyarmor.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collection;

public final class FAItems {
    public static final Collection<Item> ITEMS = new ArrayList<>();

    public static final Item MOON_CRYSTAL = new Item(new FabricItemSettings());

    private FAItems() {
    }

    public static void register() {
        register("moon_crystal", MOON_CRYSTAL);
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(FantasyArmor.MOD_ID, name), item);
        ITEMS.add(item);
    }
}