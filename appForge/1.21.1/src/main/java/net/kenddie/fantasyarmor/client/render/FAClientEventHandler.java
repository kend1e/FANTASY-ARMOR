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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(
        modid = FantasyArmor.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class FAClientEventHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {
        for (FAArmorSet set : FAArmorSet.values()) {
            for (ArmorItem.Type type : FAArmorItems.VALID_ARMOR_TYPES) {
                RegistryObject<Item> itemRO = FAArmorItems.getArmorItem(set, type);
                if (itemRO != null && itemRO.isPresent()) {
                    Item item = itemRO.get();
                    registerColorForItem(item, event.getItemColors());
                }
            }
        }
    }

    private static void registerColorForItem(Item item, ItemColors itemColors) {
        itemColors.register((stack, tintIndex) -> {
            if (tintIndex != 1) {
                return 0xFFFFFFFF;
            }

            DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
            if (color == null) {
                return 0xFFFFFFFF;
            }

            return color.rgb();
        }, item);

    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        for (FAArmorSet set : FAArmorSet.values()) {
            for (ArmorItem.Type type : FAArmorItems.VALID_ARMOR_TYPES) {
                RegistryObject<Item> itemRO = FAArmorItems.getArmorItem(set, type);
                if (itemRO != null && itemRO.isPresent()) {
                    Item item = itemRO.get();
                    event.enqueueWork(() -> ItemProperties.register(
                            item,
                            ResourceLocation.fromNamespaceAndPath(FantasyArmor.MOD_ID, "dyed"),
                            (stack, level, entity, seed) -> {
                                return stack.has(DataComponents.DYED_COLOR) ? 1.0F : 0.0F;
                            }
                    ));
                }
            }
        }
    }
}
