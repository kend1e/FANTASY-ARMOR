package net.kenddie.fantasyarmor.entity.client.armor.render.lib;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kenddie.fantasyarmor.item.armor.lib.FAArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FAArmorRendererCape <T extends FAArmorItem> extends GeoArmorRenderer<T> {
    protected GeoBone cape = null;

    public FAArmorRendererCape(GeoModel<T> model) {
        super(model);
    }

    @Nullable
    public GeoBone getCapeBone(GeoModel<T> model) {
        return model.getBone("armorCape").orElse(null);
    }

    @Override
    protected void grabRelevantBones(BakedGeoModel bakedModel) {
        super.grabRelevantBones(bakedModel);

        GeoModel<T> model = getGeoModel();
        cape = getCapeBone(model);
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        super.applyBoneVisibilityBySlot(currentSlot);

        if(currentSlot == EquipmentSlot.CHEST) {
            setBoneVisible(cape, true);
        }
    }

    public void applyBoneVisibilityByPart(EquipmentSlot currentSlot, ModelPart currentPart, HumanoidModel<?> model) {
        super.applyBoneVisibilityByPart(currentSlot, currentPart, model);

        if(currentPart == model.body) {
            cape.setHidden(false);
        }
    }

    @Override
    public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

        if(cape != null && currentEntity instanceof Player player) {
            RenderUtils.applyCapeRotation(player, cape, partialTick);
        }
    }

    @Override
    protected void applyBaseTransformations(HumanoidModel<?> baseModel) {
        super.applyBaseTransformations(baseModel);

        if(cape != null) {
            ModelPart bodyPart = baseModel.body;
            cape.updatePosition(bodyPart.x, -bodyPart.y, bodyPart.z);
        }
    }

    @Override
    public void setAllVisible(boolean pVisible) {
        super.setAllVisible(pVisible);

        setBoneVisible(cape, pVisible);
    }
}
