package net.kenddie.fantasyarmor.client.render;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;

public class FAClientEventHandler implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerItemColors();
        registerDyedPredicate();
    }

    private static void registerItemColors() {
        for (FAArmorSet set : FAArmorSet.values()) {
            for (ArmorItem.Type type : FAArmorItems.VALID_ARMOR_TYPES) {
                Item item = FAArmorItems.getArmorItem(set, type);

                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex != 1) return 0xFFFFFFFF;

                    DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
                    if (color == null) return 0xFFFFFFFF;

                    return 0xFF000000 | color.rgb();
                }, item);
            }
        }
    }

    private static void registerDyedPredicate() {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, "dyed");

        for (FAArmorSet set : FAArmorSet.values()) {
            for (ArmorItem.Type type : FAArmorItems.VALID_ARMOR_TYPES) {
                Item item = FAArmorItems.getArmorItem(set, type);

                ItemProperties.register(item, id, (stack, level, entity, seed) -> {
                    return stack.has(DataComponents.DYED_COLOR) ? 1.0F : 0.0F;
                });
            }
        }
    }
}