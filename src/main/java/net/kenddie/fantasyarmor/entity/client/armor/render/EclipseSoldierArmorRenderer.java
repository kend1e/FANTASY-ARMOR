package net.kenddie.fantasyarmor.entity.client.armor.render;

import net.kenddie.fantasyarmor.entity.client.armor.model.EclipseSoldierArmorModel;
import net.kenddie.fantasyarmor.item.armor.EclipseSoldierArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EclipseSoldierArmorRenderer extends GeoArmorRenderer<EclipseSoldierArmorItem> {
    public EclipseSoldierArmorRenderer() {
        super(new EclipseSoldierArmorModel());
    }
}
