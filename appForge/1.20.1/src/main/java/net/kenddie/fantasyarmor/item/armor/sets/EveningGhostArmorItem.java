package net.kenddie.fantasyarmor.item.armor.sets;

import net.kenddie.fantasyarmor.item.armor.lib.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorSet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.List;
import java.util.function.Supplier;

import static net.kenddie.fantasyarmor.config.FAConfig.showParticles;

public class EveningGhostArmorItem extends FAArmorItem {
    public EveningGhostArmorItem(FAArmorSet armorSet, Type type, Supplier<FAArmorAttributes> armorAttributes) {
        super(armorSet, type, armorAttributes);
    }

    @Override
    public List<MobEffectInstance> getFullSetEffects() {
        return List.of(
                new MobEffectInstance(MobEffects.REGENERATION, 239, 0, true, showParticles, true),
                new MobEffectInstance(MobEffects.NIGHT_VISION, 239, 0, true, showParticles, true)
        );
    }
}