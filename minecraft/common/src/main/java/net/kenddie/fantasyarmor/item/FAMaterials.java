package net.kenddie.fantasyarmor.item;

import net.minecraft.world.item.Item;

public final class FAMaterials {
    public static Item create(Item.Properties properties) {
        return new Item(properties);
    }

    private FAMaterials() {}
}
