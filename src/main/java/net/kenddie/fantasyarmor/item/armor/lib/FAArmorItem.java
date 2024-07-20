package net.kenddie.fantasyarmor.item.armor.lib;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;

public class FAArmorItem extends ArmorItem {
    public FAArmorItem(Type type) {
        super(ArmorMaterials.NETHERITE, type, new Properties());
    }
}