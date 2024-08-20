package net.kenddie.fantasyarmor.entity.client.armor.render.lib;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;

@Mod.EventBusSubscriber(modid = "fantasy_armor")
public class FAThirdPersonHandRenderHandler {

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);

        if (chestplate.getItem() instanceof FAArmorItem) {
            LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer = event.getRenderer();
            PlayerModel<AbstractClientPlayer> model = renderer.getModel();

            model.rightArm.visible = false;
            model.rightSleeve.visible = false;
            model.leftArm.visible = false;
            model.leftSleeve.visible = false;
        }
    }

    @SubscribeEvent
    public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);

        if (chestplate.getItem() instanceof FAArmorItem) {
            LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer = event.getRenderer();
            PlayerModel<AbstractClientPlayer> model = renderer.getModel();

            model.rightArm.visible = true;
            model.rightSleeve.visible = true;
            model.leftArm.visible = true;
            model.leftSleeve.visible = true;
            }
    }
}
