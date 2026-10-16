package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.config.FAConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static net.kenddie.fantasyarmor.config.FAConfig.showParticles;


@EventBusSubscriber
public class FAArmorEffectHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        if (FAConfig.applyArmorEffects && hasFullSet(player)) {
            applyFullSetEffects(player);
        }
    }

    private static boolean hasFullSet(Player player) {
        if (!(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof FAArmorItem helmet)
                || !(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof FAArmorItem chestplate)
                || !(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof FAArmorItem leggings)
                || !(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof FAArmorItem boots)) {
            return false;
        }
        return helmet.getType() == net.minecraft.world.item.ArmorItem.Type.HELMET
                && chestplate.getType() == net.minecraft.world.item.ArmorItem.Type.CHESTPLATE
                && leggings.getType() == net.minecraft.world.item.ArmorItem.Type.LEGGINGS
                && boots.getType() == net.minecraft.world.item.ArmorItem.Type.BOOTS
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

            if (existingEffect == null || existingEffect.getDuration() < FAConfig.effectsInterval) {
                player.addEffect(new MobEffectInstance(
                        effectHolder,
                        effectInstance.getDuration(),
                        effectInstance.getAmplifier(),
                        true,
                        showParticles,
                        FAConfig.showEffectIcon
                ));
            }
        }
    }
}
