package net.kenddie.fantasyarmor.item.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.entity.client.armor.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class DeadGladiatorArmorItem extends FAArmorItem {

    public DeadGladiatorArmorItem(Type type, FAArmorAttributes armorAttributes) {
        super(type, armorAttributes);
    }

    @Override
    public List<MobEffectInstance> getFullSetEffects() {
        return List.of(
                new MobEffectInstance(MobEffects.REGENERATION, 442)
        );
    }

    @Override
    @Environment(EnvType.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/dead_gladiator_armor.geo.json",
                "textures/armor/dead_gladiator_armor.png"
        ));
    }
}
