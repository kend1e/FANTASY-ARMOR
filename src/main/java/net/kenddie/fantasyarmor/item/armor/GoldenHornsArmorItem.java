package net.kenddie.fantasyarmor.item.armor;

import net.kenddie.fantasyarmor.entity.client.armor.model.lib.FAArmorModel;
import net.kenddie.fantasyarmor.entity.client.armor.render.lib.FAArmorRenderer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GoldenHornsArmorItem extends FAArmorItem {
    public GoldenHornsArmorItem(Type type) {
        super(type);
    }

    @Override
    protected GeoArmorRenderer<? extends FAArmorItem> createArmorRenderer() {
        return new FAArmorRenderer<>(new FAArmorModel<>(
                "geo/golden_horns_armor.geo.json",
                "textures/armor/golden_horns_armor.png"
        ));
    }
}
