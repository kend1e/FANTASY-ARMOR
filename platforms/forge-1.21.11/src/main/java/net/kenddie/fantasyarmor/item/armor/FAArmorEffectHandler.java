package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.shared.config.EffectEntry;
import net.kenddie.fantasyarmor.config.FAConfig;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = FantasyArmor.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FAArmorEffectHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent.Pre event) {
        if (event.side() == net.minecraftforge.fml.LogicalSide.SERVER) {
            Player player = event.player();

            if (FAConfig.applyArmorEffects() && hasFullSet(player)) {
                applyFullSetEffects(player);
            }
        }
    }

    @SubscribeEvent
    public static void onEquipmentChanged(net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent event) {
        FAArmorComponents.update(event.getTo());
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

        for (var effectInstance : armorItem.getFullSetEffects()) {
            Holder<MobEffect> effectHolder = effectInstance.getEffect();

            MobEffectInstance existingEffect = player.getEffect(effectHolder);

            if (existingEffect == null || EffectEntry.shouldRefresh(existingEffect.getDuration(), FAConfig.effectsInterval())) {
                player.addEffect(new MobEffectInstance(
                        effectHolder,
                        effectInstance.getDuration(),
                        effectInstance.getAmplifier(),
                        true,
                        FAConfig.showParticles(),
                        FAConfig.showEffectIcon()
                ));
            }
        }
    }
}
