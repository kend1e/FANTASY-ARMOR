package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.entity.client.armor.render.lib.FAArmorRenderer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorAttributes;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

import static net.kenddie.fantasyarmor.config.FAConfig.showParticles;

public class WindWorshipperArmorItem extends FAArmorItem {

    public WindWorshipperArmorItem(Type type, FAArmorAttributes armorAttributes) {
        super(type, armorAttributes);
    }

    @Override
    public List<MobEffectInstance> getFullSetEffects() {
        return List.of(
                new MobEffectInstance(MobEffects.JUMP, 239, 0, true, showParticles, true)
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/wind_worshipper_armor.geo.json",
                "textures/armor/wind_worshipper_armor.png"
        ));
    }
}
