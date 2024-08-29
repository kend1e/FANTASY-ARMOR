package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.entity.client.armor.render.lib.FAArmorRenderer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SunsetWingsArmorItem extends FAArmorItem {
    public SunsetWingsArmorItem(Type type, double knockbackResistance, double movementSpeed, double maxHealth, double attackDamage, double attackSpeed, double luck) {
        super(type, knockbackResistance, movementSpeed, maxHealth, attackDamage, attackSpeed, luck);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/sunset_wings_armor.geo.json",
                "textures/armor/sunset_wings_armor.png"
        ));
    }
}
