package net.kenddie.fantasyarmor.item.armor.lib;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FAArmorEffectHandler {

    public static void register() {
        ServerTickEvents.START_WORLD_TICK.register(FAArmorEffectHandler::onWorldTick);
    }

    private static void onWorldTick(ServerLevel world) {
        for (ServerPlayer player : world.players()) {
            if (FAConfig.getValues().applyArmorEffects() && hasFullSet(player)) {
                applyFullSetEffects(player);
            }
        }
    }

    private static boolean hasFullSet(Player player) {
        ItemStack helmetItemStack = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplateItemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggingsItemStack = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack bootsItemStack = player.getItemBySlot(EquipmentSlot.FEET);

        if (helmetItemStack.getItem() instanceof FAArmorItem &&
                chestplateItemStack.getItem() instanceof FAArmorItem &&
                leggingsItemStack.getItem() instanceof FAArmorItem &&
                bootsItemStack.getItem() instanceof FAArmorItem) {

            Class<?> armorClass = helmetItemStack.getItem().getClass();
            return armorClass.isInstance(chestplateItemStack.getItem()) &&
                    armorClass.isInstance(leggingsItemStack.getItem()) &&
                    armorClass.isInstance(bootsItemStack.getItem());
        }

        return false;
    }

    private static void applyFullSetEffects(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        FAArmorItem armorItem = (FAArmorItem) helmet.getItem();

        for (MobEffectInstance effectInstance : armorItem.getFullSetEffects()) {
            MobEffect effect = effectInstance.getEffect();
            MobEffectInstance existingEffect = player.getEffect(effect);

            if (existingEffect == null || existingEffect.getDuration() < 221) {
                player.addEffect(new MobEffectInstance(
                    effect,
                    effectInstance.getDuration(),
                    effectInstance.getAmplifier(),
                    true,  // ambient
                    FAConfig.getValues().showParticles(),  // showParticles
                    true    // showIcon
                ));
            }
        }
    }
}
