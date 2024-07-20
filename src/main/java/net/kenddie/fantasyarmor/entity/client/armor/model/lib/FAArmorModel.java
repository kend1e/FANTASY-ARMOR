package net.kenddie.fantasyarmor.entity.client.armor.model.lib;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.EclipseSoldierArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FAArmorModel extends GeoModel<EclipseSoldierArmorItem> {
    private final String modelPath;
    private final String texturePath;
    private final String animationPath;

    public FAArmorModel(String modelPath, String texturePath) {
        this(modelPath, texturePath, "animations/armor.animation.json");
    }

    public FAArmorModel(String modelPath, String texturePath, String animationPath) {
        this.modelPath = modelPath;
        this.texturePath = texturePath;
        this.animationPath = animationPath;
    }

    @Override
    public ResourceLocation getModelResource(EclipseSoldierArmorItem animatable) {
        return new ResourceLocation(FantasyArmor.MOD_ID, modelPath);
    }

    @Override
    public ResourceLocation getTextureResource(EclipseSoldierArmorItem animatable) {
        return new ResourceLocation(FantasyArmor.MOD_ID, texturePath);
    }

    @Override
    public ResourceLocation getAnimationResource(EclipseSoldierArmorItem animatable) {
        return new ResourceLocation(FantasyArmor.MOD_ID, animationPath);
    }
}