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
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.RenderModelPositioner;
import software.bernie.geckolib.util.RenderUtil;

import java.util.Optional;

public class FAArmorRenderer<T extends FAArmorItem, R extends net.minecraft.client.renderer.entity.state.HumanoidRenderState & GeoRenderState>
        extends GeoArmorRenderer<T, R> {

    private final boolean hasOverlayTextureFile;

    private ItemStack currentStack = ItemStack.EMPTY;
    private EquipmentSlot currentSlot = EquipmentSlot.CHEST;
    private LivingEntity currentEntity = null;

    public FAArmorRenderer(GeoModel<T> model, boolean hasOverlayTextureFile) {
        super(model);
        this.hasOverlayTextureFile = hasOverlayTextureFile;
    }

    public ItemStack getCurrentStack() {
        return currentStack;
    }

    @Override
    public R captureDefaultRenderState(T animatable, RenderData renderData, R renderState, float partialTick) {
        R out = super.captureDefaultRenderState(animatable, renderData, renderState, partialTick);

        this.currentStack = renderData.itemStack();
        this.currentSlot = renderData.slot();
        this.currentEntity = renderData.entity();

        return out;
    }

    @Override
    public int getRenderColor(T animatable, RenderData stackAndSlot, float partialTick) {
        return 0xFFFFFFFF;
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
        if (renderType == null) return;

        final EquipmentSlot slot = renderState.getGeckolibData(DataTickets.EQUIPMENT_SLOT);
        final HumanoidModel<R> baseModel = renderState.getGeckolibData(DataTickets.HUMANOID_MODEL);
        if (baseModel == null) return;

        final boolean dyed = isDyed();

        RenderModelPositioner<R> callback = RenderModelPositioner.add(modelPositioner, (rs, bm) -> {
            model.handleAnimations(createAnimationState(rs));
            applyExtraBones(rs, bakedModel);
        });

        renderTasks.submitCustomGeometry(poseStack, renderType, (pose, vertexConsumer) -> {
            final PoseStack poseStack2 = new PoseStack();
            poseStack2.last().set(pose);

            callback.run(renderState, bakedModel);

            baseModel.setupAnim(renderState);

            for (ArmorSegment segment : getSegmentsForSlot(renderState, slot)) {
                bakedModel.getBone(getBoneNameForSegment(renderState, segment)).ifPresent(bone -> {
                    ModelPart modelPart = segment.modelPartGetter.apply(baseModel);

                    RenderUtil.matchModelPartRot(modelPart, bone);

                    Vector3f bonePos = segment.modelPartMatcher.apply(new Vector3f(modelPart.x, modelPart.y, modelPart.z));
                    bone.updatePosition(bonePos.x, bonePos.y, bonePos.z);

                    renderBone(renderState, poseStack2, bone, vertexConsumer, cameraState, packedLight, packedOverlay, renderColor);
                });
            }

            if (!dyed) {
                renderExtraBones(renderState, bakedModel, poseStack2, vertexConsumer, cameraState, packedLight, packedOverlay, renderColor);
            }
        });

        submitOverlayPass(renderState, poseStack, bakedModel, model, renderTasks, cameraState, packedLight, packedOverlay, modelPositioner);
    }

    private void submitOverlayPass(
            R renderState,
            PoseStack poseStack,
            BakedGeoModel bakedModel,
            GeoModel<T> model,
            OrderedSubmitNodeCollector renderTasks,
            CameraRenderState cameraState,
            int packedLight,
            int packedOverlay,
            @Nullable RenderModelPositioner<R> modelPositioner
    ) {
        if (!hasOverlayTextureFile) return;
        if (!isDyed()) return;

        final FAArmorItem armorItem = (FAArmorItem) currentStack.getItem();

        ResourceLocation overlayTex = ResourceLocation.fromNamespaceAndPath(
                FantasyArmor.MOD_ID, armorItem.getArmorSet().getOverlayPath()
        );
        RenderType overlayType = RenderType.armorCutoutNoCull(overlayTex);

        int dyeColor = 0xFF000000 | armorItem.getColor(currentStack);

        RenderModelPositioner<R> callback = RenderModelPositioner.add(modelPositioner, (rs, bm) -> {
            model.handleAnimations(createAnimationState(rs));
            applyExtraBones(rs, bakedModel);
        });

        super.buildRenderTask(
                renderState,
                poseStack,
                bakedModel,
                model,
                renderTasks,
                cameraState,
                overlayType,
                packedLight,
                packedOverlay,
                dyeColor,
                callback
        );

        renderTasks.submitCustomGeometry(poseStack, overlayType, (pose, vertexConsumer) -> {
            final PoseStack poseStack2 = new PoseStack();
            poseStack2.last().set(pose);

            callback.run(renderState, bakedModel);

            renderExtraBones(renderState, bakedModel, poseStack2, vertexConsumer, cameraState, packedLight, packedOverlay, dyeColor);
        });
    }

    private boolean isDyed() {
        if (!hasOverlayTextureFile) return false;
        if (currentStack == null || currentStack.isEmpty()) return false;
        if (!(currentStack.getItem() instanceof FAArmorItem armorItem)) return false;

        return armorItem.hasCustomColor(currentStack);
    }

    private void renderExtraBones(
            R renderState,
            BakedGeoModel bakedModel,
            PoseStack poseStack,
            VertexConsumer buffer,
            CameraRenderState cameraState,
            int packedLight,
            int packedOverlay,
            int renderColor
    ) {
        renderExtraBone(renderState, bakedModel, "armorCape", poseStack, buffer, cameraState, packedLight, packedOverlay, renderColor);
        renderExtraBone(renderState, bakedModel, "armorFrontCape", poseStack, buffer, cameraState, packedLight, packedOverlay, renderColor);
        renderExtraBone(renderState, bakedModel, "armorLeftLegCloth", poseStack, buffer, cameraState, packedLight, packedOverlay, renderColor);
        renderExtraBone(renderState, bakedModel, "armorRightLegCloth", poseStack, buffer, cameraState, packedLight, packedOverlay, renderColor);
        renderExtraBone(renderState, bakedModel, "armorBraid", poseStack, buffer, cameraState, packedLight, packedOverlay, renderColor);
    }

    private void renderExtraBone(
            R renderState,
            BakedGeoModel bakedModel,
            String boneName,
            PoseStack poseStack,
            VertexConsumer buffer,
            CameraRenderState cameraState,
            int packedLight,
            int packedOverlay,
            int renderColor
    ) {
        bakedModel.getBone(boneName).ifPresent(bone -> {
            if (bone.isHidden()) return;
            renderBone(renderState, poseStack, bone, buffer, cameraState, packedLight, packedOverlay, renderColor);
        });
    }

    private void applyExtraBones(R renderState, BakedGeoModel bakedModel) {
        final HumanoidModel<?> baseModel = renderState.getGeckolibData(DataTickets.HUMANOID_MODEL);
        if (baseModel == null) return;

        GeoBone cape = bakedModel.getBone("armorCape").orElse(null);
        GeoBone frontCape = bakedModel.getBone("armorFrontCape").orElse(null);
        GeoBone leftLegCloth = bakedModel.getBone("armorLeftLegCloth").orElse(null);
        GeoBone rightLegCloth = bakedModel.getBone("armorRightLegCloth").orElse(null);
        GeoBone braid = bakedModel.getBone("armorBraid").orElse(null);

        switch (currentSlot) {
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
            default -> { }
        }

        if (cape != null) {
            ModelPart bodyPart = baseModel.body;
            float yPos = (currentEntity != null && currentEntity.isCrouching()) ? bodyPart.y - 5.5f : bodyPart.y;
            cape.updatePosition(bodyPart.x, yPos, bodyPart.z);

            if (currentEntity instanceof Player && renderState instanceof AvatarRenderState avatarState) {
                FARenderUtils.applyCapeRotation(avatarState, cape);
            } else {
                cape.updateRotation((float) -Math.toRadians(5.0F), 0.0F, 0.0F);
            }
        }

        if (frontCape != null) {
            ModelPart leftLegPart = baseModel.leftLeg;
            frontCape.updatePosition(leftLegPart.x - 1.95f, 13 - leftLegPart.y, leftLegPart.z - 0.1f);

            Optional<GeoBone> leftLegBone = bakedModel.getBone(getBoneNameForSegment(renderState, ArmorSegment.LEFT_LEG));
            Optional<GeoBone> rightLegBone = bakedModel.getBone(getBoneNameForSegment(renderState, ArmorSegment.RIGHT_LEG));
            FARenderUtils.setFrontLegCapeAngle(leftLegBone.orElse(null), rightLegBone.orElse(null), frontCape);
        }

        if (leftLegCloth != null) {
            ModelPart leftLegPart = baseModel.leftLeg;
            RenderUtil.matchModelPartRot(leftLegPart, leftLegCloth);
            leftLegCloth.updatePosition(leftLegPart.x - 2, 12 - leftLegPart.y, leftLegPart.z);
        }

        if (rightLegCloth != null) {
            ModelPart rightLegPart = baseModel.rightLeg;
            RenderUtil.matchModelPartRot(rightLegPart, rightLegCloth);
            rightLegCloth.updatePosition(rightLegPart.x + 2, 12 - rightLegPart.y, rightLegPart.z);
        }

        if (braid != null && currentEntity instanceof Player && renderState instanceof AvatarRenderState avatarState) {
            FARenderUtils.applyBraidRotation(avatarState, braid);
        }
    }

    private static void setHiddenSafe(@Nullable GeoBone bone, boolean hidden) {
        if (bone != null) bone.setHidden(hidden);
    }
}