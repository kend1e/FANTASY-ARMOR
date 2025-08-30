package net.kenddie.fantasyarmor.item;

import net.kenddie.fantasyarmor.config.FAArmorAttributesConfig;
import net.kenddie.fantasyarmor.config.FAArmorConfig;
import net.kenddie.fantasyarmor.item.armor.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class FAArmorItems {
    // EnumEntry -> (ArmorPiece -> RegistryObject<Item> )
    public static final Map<FAArmorSet, Map<ArmorItem.Type, RegistryObject<Item>>> ARMOR_ITEMS = new HashMap<>();

    public static void register(IEventBus eventBus) {
        for (FAArmorSet set : FAArmorSet.values()) {
            Map<ArmorItem.Type, RegistryObject<Item>> setPieces = new HashMap<>();

            for (ArmorItem.Type type : ArmorItem.Type.values()) {
                String name = set.getName() + "_" + type.getName();

                FAArmorAttributesConfig config = FAArmorConfig.ARMOR_CONFIGS.get(set.getName()).get(type.getName());

                Supplier<FAArmorAttributes> attributesSupplier = () -> new FAArmorAttributes.Builder()
                        .armor(config.armor.get())
                        .armorToughness(config.armorToughness.get())
                        .knockbackResistance(config.knockbackResistance.get())
                        .movementSpeed(config.movementSpeed.get())
                        .maxHealth(config.maxHealth.get())
                        .attackDamage(config.attackDamage.get())
                        .attackSpeed(config.attackSpeed.get())
                        .luck(config.luck.get())
                        .build();

                RegistryObject<Item> item = FAItems.ITEMS.register(name, () -> set.create(type, attributesSupplier));
                setPieces.put(type, item);
            }


            ARMOR_ITEMS.put(set, setPieces);
        }
    }

    public static RegistryObject<Item> getArmorItem(FAArmorSet set, ArmorItem.Type type) {
        return ARMOR_ITEMS.get(set).get(type);
    }

    private FAArmorItems() {}
}
