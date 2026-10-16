package net.kenddie.fantasyarmor.item.armor;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kenddie.fantasyarmor.config.FAConfigs;
import net.minecraft.core.Holder;
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
            if (FAConfigs.getMainConfig().applyArmorEffects && hasFullSet(player)) {
                applyFullSetEffects(player);
            }
        }
    }

    private static boolean hasFullSet(Player player) {
        if (!(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof FAArmorItem helmet)
                || !(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof FAArmorItem chestplate)
                || !(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof FAArmorItem leggings)
                || !(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof FAArmorItem boots)) {
            return false;
        }
        return helmet.getArmorType() == net.minecraft.world.item.equipment.ArmorType.HELMET
                && chestplate.getArmorType() == net.minecraft.world.item.equipment.ArmorType.CHESTPLATE
                && leggings.getArmorType() == net.minecraft.world.item.equipment.ArmorType.LEGGINGS
                && boots.getArmorType() == net.minecraft.world.item.equipment.ArmorType.BOOTS
                && helmet.getArmorSet().equals(chestplate.getArmorSet())
                && helmet.getArmorSet().equals(leggings.getArmorSet())
                && helmet.getArmorSet().equals(boots.getArmorSet());
    }

    private static void applyFullSetEffects(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        FAArmorItem armorItem = (FAArmorItem) helmet.getItem();

        for (MobEffectInstance effectInstance : armorItem.getFullSetEffects()) {
            Holder<MobEffect> effectHolder = effectInstance.getEffect();
            MobEffectInstance existingEffect = player.getEffect(effectHolder);

            if (existingEffect == null || existingEffect.getDuration() < FAConfigs.getMainConfig().effectsInterval) {
                player.addEffect(new MobEffectInstance(
                        effectHolder,
                        effectInstance.getDuration(),
                        effectInstance.getAmplifier(),
                        true,  // ambient
                        FAConfigs.getMainConfig().showParticles,
                        FAConfigs.getMainConfig().showEffectIcon
                ));
            }
        }
    }
}
