package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.entity.client.armor.render.FAArmorRenderer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class GoldenHornsArmorItem extends FAArmorItem {
    public GoldenHornsArmorItem(Type type, double knockbackResistance, double movementSpeed, double maxHealth, double attackDamage, double attackSpeed, double luck) {
        super(type, knockbackResistance, movementSpeed, maxHealth, attackDamage, attackSpeed, luck);
    }

    @Override
    public List<MobEffectInstance> getFullSetEffects() {
        return List.of(
                new MobEffectInstance(MobEffects.DIG_SPEED, 239, 1)
        );
    }


    @Override
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/golden_horns_armor.geo.json",
                "textures/armor/golden_horns_armor.png"
        ));
    }
}
