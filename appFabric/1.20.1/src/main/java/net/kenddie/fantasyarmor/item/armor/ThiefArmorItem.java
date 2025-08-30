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

public class ThiefArmorItem extends FAArmorItem {
    public ThiefArmorItem(Type type, FAArmorAttributes armorAttributes) {
        super(type, armorAttributes);
    }

    @Override
    public List<MobEffectInstance> getFullSetEffects() {
        return List.of(
                new MobEffectInstance(MobEffects.JUMP, 442)
        );
    }


    @Override
    @Environment(EnvType.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/thief_armor.geo.json",
                "textures/armor/thief_armor.png"
        ));
    }
}
