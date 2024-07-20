package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EclipseSoldierArmorItem extends FAArmorItem {
    public EclipseSoldierArmorItem(Type type) {
        super(type);
    }

    @Override
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new GeoArmorRenderer<>(new FAArmorModel(
                "geo/eclipse_soldier_armor.geo.json",
                "textures/armor/eclipse_soldier_armor.png"
        ));
    }
}
