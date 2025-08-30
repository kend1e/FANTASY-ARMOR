package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.config.FAConfig;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.kenddie.fantasyarmor.config.FAConfig.showParticles;


@Mod.EventBusSubscriber
public class FAArmorEffectHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Player player = event.player;

            if (FAConfig.applyArmorEffects && hasFullSet(player)) {
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

            if (existingEffect == null || existingEffect.getDuration() < FAConfig.effectsInterval) {
                player.addEffect(new MobEffectInstance(
                    effect,
                    effectInstance.getDuration(),
                    effectInstance.getAmplifier(),
                    true,  // ambient
                    showParticles,  // showParticles
                    FAConfig.showEffectIcon    // showIcon
                ));
            }
        }
    }
}
