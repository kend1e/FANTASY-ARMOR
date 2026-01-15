package net.kenddie.fantasyarmor.client.render;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

@EventBusSubscriber(
        modid = FantasyArmor.MOD_ID,
        value = Dist.CLIENT
)
public class FAClientEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        for (FAArmorSet set : FAArmorSet.values()) {
            for (ArmorItem.Type type : FAArmorItems.VALID_ARMOR_TYPES) {
                Supplier<? extends Item> itemSupplier = FAArmorItems.getArmorItem(set, type);
                if (itemSupplier != null) {
                    registerColorForItem(itemSupplier.get(), itemColors);
                }
            }
        }
    }

    private static void registerColorForItem(Item item, ItemColors itemColors) {
        itemColors.register((stack, tintIndex) -> {
            if (tintIndex != 1) return 0xFFFFFFFF;

            DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
            if (color == null) return 0xFFFFFFFF;

            return 0xFF000000 | color.rgb();
        }, item);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (FAArmorSet set : FAArmorSet.values()) {
                for (ArmorItem.Type type : FAArmorItems.VALID_ARMOR_TYPES) {
                    Supplier<? extends Item> itemSupplier = FAArmorItems.getArmorItem(set, type);
                    if (itemSupplier != null) {
                        Item item = itemSupplier.get();
                        ItemProperties.register(
                                item,
                                ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, "dyed"),
                                (stack, level, entity, seed) ->
                                        stack.has(DataComponents.DYED_COLOR) ? 1.0F : 0.0F
                        );
                    }
                }
            }
        });
    }
}