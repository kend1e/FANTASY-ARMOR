package net.kenddie.fantasyarmor.client.armor.render;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.FAArmorItems;
import net.kenddie.fantasyarmor.item.FAItems;
import net.kenddie.fantasyarmor.item.armor.FAArmorItem;
import net.kenddie.fantasyarmor.item.armor.FAArmorSet;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
            for (ArmorItem.Type type : ArmorItem.Type.values()) {
                Item item = FAArmorItems.getArmorItem(set, type).get();

                registerColorForItem(item, event.getItemColors());
            }
        }
    }

    // TODO : Transparent
    private static void registerColorForItem(Item item, ItemColors itemColors) {
        itemColors.register((stack, tintIndex) -> {
            if (tintIndex != 1)
                return 0xFFFFFFFF;

            if (!(stack.getItem() instanceof DyeableLeatherItem dyeable) || !dyeable.hasCustomColor(stack)) {
                return 0x00FFFFFF;
            }

            return dyeable.getColor(stack);
        }, item);
    }
}
