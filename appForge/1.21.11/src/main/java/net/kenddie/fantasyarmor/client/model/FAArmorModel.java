package net.kenddie.fantasyarmor.client.model;

import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.item.armor.FAArmorItem;
import net.minecraft.resources.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class FAArmorModel <T extends FAArmorItem> extends GeoModel<T> {
    private final String modelPath;
    private final String texturePath;
    private final String animationPath;

    public FAArmorModel(String modelPath, String texturePath) {
        this(modelPath, texturePath, null);
    }

    public FAArmorModel(String modelPath, String texturePath, String animationPath) {
        this.modelPath = modelPath;
        this.texturePath = texturePath;
        this.animationPath = animationPath;
    }

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, modelPath);
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, texturePath);
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        if (animationPath != null) {
            return Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, animationPath);
        }
        return null;
    }
}
