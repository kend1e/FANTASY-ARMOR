package net.kenddie.fantasyarmor.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.config.FAConfigs;
import net.kenddie.fantasyarmor.item.armor.FAArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderer;
import software.bernie.geckolib.renderer.base.RenderModelPositioner;
import software.bernie.geckolib.util.RenderUtil;

import java.util.Optional;

public class FAArmorRenderer<T extends FAArmorItem, R extends HumanoidRenderState & GeoRenderState>
        extends GeoArmorRenderer<T, R> {

    private final boolean hasOverlayTextureFile;

    public FAArmorRenderer(GeoModel<T> model, boolean hasOverlayTextureFile) {
        super(model);
        this.hasOverlayTextureFile = hasOverlayTextureFile;

        if (hasOverlayTextureFile) {
            this.withRenderLayer(r -> new FADyeableTextureLayer<>((GeoArmorRenderer) r));
        }
    }

    @Override
    public int getRenderColor(T animatable, RenderData renderData, float partialTick) {
        if (hasOverlayTextureFile)
            return 0xFFFFFFFF;

        if (!animatable.hasCustomColor(renderData.itemStack()))
            return 0xFFFFFFFF;

        return 0xFF000000 | animatable.getColor(renderData.itemStack());
    }

    @Override
    public R captureDefaultRenderState(T animatable, RenderData renderData, R renderState, float partialTick) {
        R out = super.captureDefaultRenderState(animatable, renderData, renderState, partialTick);

        if (!hasOverlayTextureFile)
            return out;

        boolean dyed = animatable.hasCustomColor(renderData.itemStack());
        out.addGeckolibData(FADyeableTextureLayer.HAS_DYE_TICKET, dyed);

        if (dyed) {
            int dyeColor = 0xFF000000 | animatable.getColor(renderData.itemStack());
            out.addGeckolibData(FADyeableTextureLayer.DYE_COLOR_TICKET, dyeColor);

            ResourceLocation overlayTex = ResourceLocation.fromNamespaceAndPath(
                    FantasyArmor.MOD_ID,
                    animatable.getArmorSet().getOverlayPath()
            );
            out.addGeckolibData(FADyeableTextureLayer.OVERLAY_TEX_TICKET, overlayTex);
        }

        return out;
    }

    @Override
    public void buildRenderTask(
            R renderState,
            PoseStack poseStack,
            BakedGeoModel bakedModel,
            GeoModel<T> model,
            OrderedSubmitNodeCollector renderTasks,
            CameraRenderState cameraState,
            @Nullable RenderType renderType,
            int packedLight,
            int packedOverlay,
            int renderColor,
            @Nullable RenderModelPositioner<R> modelPositioner
    ) {
        if (renderType == null)
            return;

        RenderModelPositioner<R> callback = RenderModelPositioner.add(modelPositioner, (rs, bm) -> {
            model.handleAnimations(createAnimationState(rs));
            applyExtraBonesStatic(rs, bakedModel);
        });

        renderTasks.submitCustomGeometry(poseStack, renderType, (pose, vertexConsumer) -> {
            final EquipmentSlot slot = renderState.getGeckolibData(DataTickets.EQUIPMENT_SLOT);

            final HumanoidModel<?> baseModelAny = renderState.getGeckolibData(DataTickets.HUMANOID_MODEL);
            if (baseModelAny == null)
                return;

            @SuppressWarnings("unchecked")
            final HumanoidModel<R> baseModel = (HumanoidModel<R>) baseModelAny;

            final PoseStack localPose = new PoseStack();
            localPose.last().set(pose);

            callback.run(renderState, bakedModel);
            baseModel.setupAnim(renderState);

            for (ArmorSegment segment : getSegmentsForSlot(renderState, slot)) {
                bakedModel.getBone(getBoneNameForSegment(renderState, segment)).ifPresent(bone -> {
                    ModelPart modelPart = segment.modelPartGetter.apply(baseModel);
                    Vector3f bonePos = segment.modelPartMatcher.apply(new Vector3f(modelPart.x, modelPart.y, modelPart.z));

                    RenderUtil.matchModelPartRot(modelPart, bone);
                    bone.updatePosition(bonePos.x, bonePos.y, bonePos.z);

                    renderBone(renderState, localPose, bone, vertexConsumer, cameraState, packedLight, packedOverlay, renderColor);
                });
            }

            renderExtraBonesStatic(
                    this, renderState, localPose, bakedModel, vertexConsumer, cameraState,
                    packedLight, packedOverlay, renderColor,
                    slot,
                    false
            );
        });
    }

    public static <R extends HumanoidRenderState & GeoRenderState> void applyExtraBonesStatic(R renderState, BakedGeoModel bakedModel) {
        final HumanoidModel<?> baseModelAny = renderState.getGeckolibData(DataTickets.HUMANOID_MODEL);
        if (baseModelAny == null)
            return;

        @SuppressWarnings("unchecked")
        final HumanoidModel<R> baseModel = (HumanoidModel<R>) baseModelAny;

        final EquipmentSlot slot = renderState.getGeckolibData(DataTickets.EQUIPMENT_SLOT);

        GeoBone cape = bakedModel.getBone("armorCape").orElse(null);
        GeoBone frontCape = bakedModel.getBone("armorFrontCape").orElse(null);
        GeoBone leftLegCloth = bakedModel.getBone("armorLeftLegCloth").orElse(null);
        GeoBone rightLegCloth = bakedModel.getBone("armorRightLegCloth").orElse(null);
        GeoBone braid = bakedModel.getBone("armorBraid").orElse(null);

        switch (slot) {
            case HEAD -> {
                setHiddenSafe(braid, false);
                setHiddenSafe(cape, true);
                setHiddenSafe(frontCape, true);
                setHiddenSafe(leftLegCloth, true);
                setHiddenSafe(rightLegCloth, true);
            }
            case CHEST -> {
                setHiddenSafe(cape, !FAConfigs.getMainConfig().showCapes);
                setHiddenSafe(frontCape, false);
                setHiddenSafe(leftLegCloth, false);
                setHiddenSafe(rightLegCloth, false);
                setHiddenSafe(braid, true);
            }
            case LEGS, FEET -> {
                setHiddenSafe(braid, true);
                setHiddenSafe(cape, true);
                setHiddenSafe(frontCape, true);
                setHiddenSafe(leftLegCloth, true);
                setHiddenSafe(rightLegCloth, true);
            }
            default -> {}
        }

        if (cape != null && !cape.isHidden()) {
            ModelPart bodyPart = baseModel.body;

            boolean crouching = renderState.isCrouching;
            float yPos = crouching ? bodyPart.y - 5.5f : bodyPart.y;

            cape.updatePosition(bodyPart.x, yPos, bodyPart.z);

            if (renderState instanceof AvatarRenderState avatarState) {
                FARenderUtils.applyCapeRotation(avatarState, cape);
            } else {
                cape.updateRotation((float) -Math.toRadians(5.0F), 0.0F, 0.0F);
            }
        }

        if (frontCape != null && !frontCape.isHidden()) {
            ModelPart leftLegPart = baseModel.leftLeg;
            frontCape.updatePosition(leftLegPart.x - 1.95f, 13 - leftLegPart.y, leftLegPart.z - 0.1f);

            Optional<GeoBone> leftLegBone = bakedModel.getBone("armorLeftLeg");
            Optional<GeoBone> rightLegBone = bakedModel.getBone("armorRightLeg");
            FARenderUtils.setFrontLegCapeAngle(leftLegBone.orElse(null), rightLegBone.orElse(null), frontCape);
        }

        if (leftLegCloth != null && !leftLegCloth.isHidden()) {
            ModelPart leftLegPart = baseModel.leftLeg;
            RenderUtil.matchModelPartRot(leftLegPart, leftLegCloth);
            leftLegCloth.updatePosition(leftLegPart.x - 2, 12 - leftLegPart.y, leftLegPart.z);
        }

        if (rightLegCloth != null && !rightLegCloth.isHidden()) {
            ModelPart rightLegPart = baseModel.rightLeg;
            RenderUtil.matchModelPartRot(rightLegPart, rightLegCloth);
            rightLegCloth.updatePosition(rightLegPart.x + 2, 12 - rightLegPart.y, rightLegPart.z);
        }

        if (braid != null && !braid.isHidden() && renderState instanceof AvatarRenderState avatarState) {
            FARenderUtils.applyBraidRotation(avatarState, braid);
        }
    }

    public static <T extends net.minecraft.world.item.Item & software.bernie.geckolib.animatable.GeoItem,
            R extends HumanoidRenderState & GeoRenderState>
    void renderExtraBonesStatic(
            GeoRenderer<T, GeoArmorRenderer.RenderData, R> renderer,
            R renderState,
            PoseStack poseStack,
            BakedGeoModel bakedModel,
            VertexConsumer buffer,
            CameraRenderState cameraState,
            int packedLight,
            int packedOverlay,
            int renderColor,
            EquipmentSlot slot,
            boolean forceRenderEvenIfDescendant
    ) {
        renderExtraBone(renderer, renderState, poseStack, bakedModel, buffer, cameraState, packedLight, packedOverlay, renderColor, slot, forceRenderEvenIfDescendant, "armorCape");
        renderExtraBone(renderer, renderState, poseStack, bakedModel, buffer, cameraState, packedLight, packedOverlay, renderColor, slot, forceRenderEvenIfDescendant, "armorFrontCape");
        renderExtraBone(renderer, renderState, poseStack, bakedModel, buffer, cameraState, packedLight, packedOverlay, renderColor, slot, forceRenderEvenIfDescendant, "armorLeftLegCloth");
        renderExtraBone(renderer, renderState, poseStack, bakedModel, buffer, cameraState, packedLight, packedOverlay, renderColor, slot, forceRenderEvenIfDescendant, "armorRightLegCloth");
        renderExtraBone(renderer, renderState, poseStack, bakedModel, buffer, cameraState, packedLight, packedOverlay, renderColor, slot, forceRenderEvenIfDescendant, "armorBraid");
    }

    private static <T extends net.minecraft.world.item.Item & software.bernie.geckolib.animatable.GeoItem,
            R extends HumanoidRenderState & GeoRenderState>
    void renderExtraBone(
            GeoRenderer<T, GeoArmorRenderer.RenderData, R> renderer,
            R renderState,
            PoseStack poseStack,
            BakedGeoModel bakedModel,
            VertexConsumer buffer,
            CameraRenderState cameraState,
            int packedLight,
            int packedOverlay,
            int renderColor,
            EquipmentSlot slot,
            boolean forceRenderEvenIfDescendant,
            String boneName
    ) {
        Optional<GeoBone> boneOpt = bakedModel.getBone(boneName);
        if (boneOpt.isEmpty())
            return;

        GeoBone bone = boneOpt.get();
        if (bone.isHidden())
            return;

        if (!forceRenderEvenIfDescendant && isRenderedBySegmentTree(renderState, slot, bone)) {
            return;
        }

        renderer.renderBone(renderState, poseStack, bone, buffer, cameraState, packedLight, packedOverlay, renderColor);
    }

    private static <R extends HumanoidRenderState & GeoRenderState> boolean isRenderedBySegmentTree(
            R renderState,
            EquipmentSlot slot,
            GeoBone bone
    ) {
        final String[] roots = switch (slot) {
            case HEAD -> new String[]{"armorHead"};
            case CHEST -> new String[]{"armorBody", "armorLeftArm", "armorRightArm"};
            case LEGS -> new String[]{"armorLeftLeg", "armorRightLeg"};
            case FEET -> new String[]{"armorLeftBoot", "armorRightBoot"};
            default -> new String[0];
        };

        GeoBone cursor = bone;
        while (cursor != null) {
            String name = cursor.getName();
            for (String root : roots) {
                if (root.equals(name)) {
                    return true;
                }
            }
            cursor = cursor.getParent();
        }

        return false;
    }

    private static void setHiddenSafe(@Nullable GeoBone bone, boolean hidden) {
        if (bone != null)
            bone.setHidden(hidden);
    }
}
