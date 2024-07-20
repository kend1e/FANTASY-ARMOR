package net.kenddie.fantasyarmor.entity.client.armor.model;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.EclipseSoldierArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EclipseSoldierArmorModel extends GeoModel<EclipseSoldierArmorItem> {
    @Override
    public ResourceLocation getModelResource(EclipseSoldierArmorItem animatable) {
        return new ResourceLocation(FantasyArmor.MOD_ID, "geo/eclipse_soldier_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EclipseSoldierArmorItem animatable) {
        return new ResourceLocation(FantasyArmor.MOD_ID, "textures/armor/eclipse_soldier_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EclipseSoldierArmorItem animatable) {
        return new ResourceLocation(FantasyArmor.MOD_ID, "animations/eclipse_soldier_armor.animation.json");
    }
}
