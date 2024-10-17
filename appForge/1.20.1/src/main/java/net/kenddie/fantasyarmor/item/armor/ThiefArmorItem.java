package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.entity.client.armor.render.lib.FAArmorRenderer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class ThiefArmorItem extends FAArmorItem {
    public ThiefArmorItem(Type type, double knockbackResistance, double movementSpeed, double maxHealth, double attackDamage, double attackSpeed, double luck) {
        super(type, knockbackResistance, movementSpeed, maxHealth, attackDamage, attackSpeed, luck);
    }

    @Override
    public List<MobEffectInstance> getFullSetEffects() {
        return List.of(
                new MobEffectInstance(MobEffects.JUMP, 239)
        );
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/thief_armor.geo.json",
                "textures/armor/thief_armor.png"
        ));
    }
}
