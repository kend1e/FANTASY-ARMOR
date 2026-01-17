package net.kenddie.fantasyarmor.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import org.joml.Vector3f;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.util.RenderUtil;

public class FADyeableGeoLayer<
        T extends net.minecraft.world.item.Item & software.bernie.geckolib.animatable.GeoItem,
        R extends HumanoidRenderState & GeoRenderState
        > extends GeoRenderLayer<T, GeoArmorRenderer.RenderData, R> {

    public static final DataTicket<Boolean> HAS_DYE_TICKET =
            DataTicket.create("fantasyarmor_has_dye", Boolean.class);
    public static final DataTicket<Integer> DYE_COLOR_TICKET =
            DataTicket.create("fantasyarmor_dye_color", Integer.class);
    public static final DataTicket<ResourceLocation> OVERLAY_TEX_TICKET =
            DataTicket.create("fantasyarmor_overlay_tex", ResourceLocation.class);

    public FADyeableGeoLayer(GeoArmorRenderer<T, R> renderer) {
        super(renderer);
    }

    @Override
    public void submitRenderTask(
            R renderState,
            PoseStack poseStack,
            BakedGeoModel bakedModel,
            SubmitNodeCollector renderTasks,
            CameraRenderState cameraState,
            int packedLight,
            int packedOverlay,
            int renderColor,
            boolean didRenderModel
    ) {
        if (!didRenderModel)
            return;

        Boolean hasDye = renderState.getOrDefaultGeckolibData(HAS_DYE_TICKET, Boolean.FALSE);
        if (!Boolean.TRUE.equals(hasDye))
            return;

        ResourceLocation overlayTex = renderState.getOrDefaultGeckolibData(OVERLAY_TEX_TICKET, null);
        if (overlayTex == null)
            return;

        int dyeColor = renderState.getOrDefaultGeckolibData(DYE_COLOR_TICKET, 0xFFFFFFFF);
        RenderType overlayType = RenderType.armorCutoutNoCull(overlayTex);

        OrderedSubmitNodeCollector ordered = renderTasks.order(1);

        ordered.submitCustomGeometry(poseStack, overlayType, (pose, vertexConsumer) -> {
            final EquipmentSlot slot = renderState.getGeckolibData(DataTickets.EQUIPMENT_SLOT);

            final HumanoidModel<?> baseModelAny = renderState.getGeckolibData(DataTickets.HUMANOID_MODEL);
            if (baseModelAny == null)
                return;

            @SuppressWarnings("unchecked")
            final HumanoidModel<R> baseModel = (HumanoidModel<R>) baseModelAny;

            // 1) IMPORTANT: run GeckoLib animations for this pass too (same as base pass does)
            // This is what prevents "white static + colored moving" misalignment.
            this.getGeoModel().handleAnimations(this.renderer.createAnimationState(renderState));

            // 2) Vanilla pose
            baseModel.setupAnim(renderState);

            final PoseStack localPose = new PoseStack();
            localPose.last().set(pose);

            // 3) Apply extra-bone visibility/positions/rotations AFTER animations are applied
            FAArmorRenderer.applyExtraBonesStatic(renderState, bakedModel);

            // 4) Render standard segments (children get overlay too)
            for (GeoArmorRenderer.ArmorSegment segment : ((GeoArmorRenderer<?, R>) this.renderer).getSegmentsForSlot(renderState, slot)) {
                String boneName = ((GeoArmorRenderer<?, R>) this.renderer).getBoneNameForSegment(renderState, segment);

                bakedModel.getBone(boneName).ifPresent(bone -> {
                    ModelPart modelPart = segment.modelPartGetter.apply(baseModel);
                    Vector3f bonePos = segment.modelPartMatcher.apply(new Vector3f(modelPart.x, modelPart.y, modelPart.z));

                    RenderUtil.matchModelPartRot(modelPart, bone);
                    bone.updatePosition(bonePos.x, bonePos.y, bonePos.z);

                    this.renderer.renderBone(renderState, localPose, bone, vertexConsumer, cameraState, packedLight, packedOverlay, dyeColor);
                });
            }

            // 5) Render extra bones (only those not descendants of segment roots)
            FAArmorRenderer.renderExtraBonesStatic(
                    this.renderer, renderState, localPose, bakedModel, vertexConsumer, cameraState,
                    packedLight, packedOverlay, dyeColor,
                    slot,
                    false
            );
        });
    }
}
