package net.kenddie.fantasyarmor.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.config.FAClientConfig;
import net.kenddie.fantasyarmor.item.armor.FAArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.state.BoneSnapshot;
import software.bernie.geckolib.cache.model.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.BoneSnapshots;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.RenderPassInfo;

import java.util.HashSet;
import java.util.Set;

public class FAArmorRenderer<T extends FAArmorItem, R extends HumanoidRenderState & GeoRenderState>
        extends GeoArmorRenderer<T, R> {
    private static final String[] EXTRA_BONES = {
            "armorCape", "armorCapeEpicFight", "armorFrontCape", "armorLeftLegCloth", "armorRightLegCloth", "armorBraid"
    };
    private final boolean dyeable;

    public FAArmorRenderer(GeoModel<T> model, boolean dyeable) {
        super(model);
        this.dyeable = dyeable;
        if (dyeable) withRenderLayer(new FADyeableGeoLayer<>(this));
    }

    @Override
    public int getRenderColor(T animatable, RenderData data, float partialTick) { return 0xFFFFFFFF; }

    @Override
    public void captureDefaultRenderState(T animatable, RenderData data, R state, float partialTick) {
        super.captureDefaultRenderState(animatable, data, state, partialTick);
        boolean dyed = dyeable && animatable.hasCustomColor(data.itemStack());
        state.addGeckolibData(FADyeableGeoLayer.HAS_DYE_TICKET, dyed);
        if (dyed) {
            state.addGeckolibData(FADyeableGeoLayer.DYE_COLOR_TICKET, 0xFF000000 | animatable.getColor(data.itemStack()));
            state.addGeckolibData(FADyeableGeoLayer.OVERLAY_TEX_TICKET,
                    Identifier.fromNamespaceAndPath(FantasyArmor.MOD_ID, animatable.getArmorSet().getOverlayPath()));
        }
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<R> pass, BoneSnapshots snapshots) {
        super.adjustModelBonesForRender(pass, snapshots);
        R renderState = pass.renderState();
        HumanoidModel<?> baseModel = renderState.getGeckolibData(DataTickets.HUMANOID_MODEL);
        if (baseModel == null) return;
        EquipmentSlot slot = renderState.getGeckolibData(DataTickets.EQUIPMENT_SLOT);
        BoneSnapshot epicFightCape = snapshots.get("armorCapeEpicFight").orElse(null);
        BoneSnapshot cape = snapshots.get("armorCape").orElse(null);
        BoneSnapshot frontCape = snapshots.get("armorFrontCape").orElse(null);
        BoneSnapshot leftLegCloth = snapshots.get("armorLeftLegCloth").orElse(null);
        BoneSnapshot rightLegCloth = snapshots.get("armorRightLegCloth").orElse(null);
        BoneSnapshot braid = snapshots.get("armorBraid").orElse(null);

        boolean epicCape = FantasyArmor.isEpicFightLoaded && !FAClientConfig.EPIC_FIGHT_NOT_STATIC_CAPES.get();

        // Visibility control
        switch (slot) {
            case HEAD -> {
                setHiddenSafe(braid, false);
                setHiddenSafe(cape, true);
                setHiddenSafe(epicFightCape, true);
                setHiddenSafe(frontCape, true);
                setHiddenSafe(leftLegCloth, true);
                setHiddenSafe(rightLegCloth, true);
            }
            case CHEST -> {
                setHiddenSafe(cape, !FAClientConfig.SHOW_CAPES.get() || epicCape);
                setHiddenSafe(epicFightCape, !FAClientConfig.SHOW_CAPES.get() || !epicCape);
                setHiddenSafe(frontCape, false);
                setHiddenSafe(leftLegCloth, false);
                setHiddenSafe(rightLegCloth, false);
                setHiddenSafe(braid, true);
            }
            case LEGS, FEET -> {
                setHiddenSafe(braid, true);
                setHiddenSafe(cape, true);
                setHiddenSafe(epicFightCape, true);
                setHiddenSafe(frontCape, true);
                setHiddenSafe(leftLegCloth, true);
                setHiddenSafe(rightLegCloth, true);
            }
            default -> {}
        }

        boolean crouching = renderState.isCrouching;

        // Cape positioning and rotation
        if (cape != null && !cape.isHidden()) {
            ModelPart bodyPart = baseModel.body;
            float yPos = crouching ? bodyPart.y - 5.5f : bodyPart.y;
            cape.setTranslation(bodyPart.x, yPos, bodyPart.z);

            if (renderState instanceof AvatarRenderState avatarState) {
                FARenderUtils.applyCapeRotation(avatarState, cape);
            } else {
                cape.setRotation((float) -Math.toRadians(5.0F), 0.0F, 0.0F);
            }
        }

        // Front cape positioning
        if (frontCape != null && !frontCape.isHidden()) {
            ModelPart leftLegPart = baseModel.leftLeg;
            ModelPart rightLegPart = baseModel.rightLeg;

            frontCape.setTranslation(leftLegPart.x - 1.95f, 13 - leftLegPart.y, leftLegPart.z - 0.1f);

            BoneSnapshot leftLegBone = snapshots.get("armorLeftLeg").orElse(null);
            BoneSnapshot rightLegBone = snapshots.get("armorRightLeg").orElse(null);

            if (leftLegBone != null)  FARenderUtils.matchModelPartRot(leftLegPart, leftLegBone);
            if (rightLegBone != null) FARenderUtils.matchModelPartRot(rightLegPart, rightLegBone);

            FARenderUtils.setFrontLegCapeAngle(leftLegBone, rightLegBone, frontCape);
        }

        // Left leg cloth
        if (leftLegCloth != null && !leftLegCloth.isHidden()) {
            ModelPart leftLegPart = baseModel.leftLeg;
            FARenderUtils.matchModelPartRot(leftLegPart, leftLegCloth);
            leftLegCloth.setTranslation(leftLegPart.x - 2, 12 - leftLegPart.y, leftLegPart.z);
        }

        // Right leg cloth
        if (rightLegCloth != null && !rightLegCloth.isHidden()) {
            ModelPart rightLegPart = baseModel.rightLeg;
            FARenderUtils.matchModelPartRot(rightLegPart, rightLegCloth);
            rightLegCloth.setTranslation(rightLegPart.x + 2, 12 - rightLegPart.y, rightLegPart.z);
        }

        // Braid
        if (braid != null && !braid.isHidden() && renderState instanceof AvatarRenderState avatarState) {
            FARenderUtils.applyBraidRotation(avatarState, braid);
        }
    }

    @Override
    public void submitRenderTasks(RenderPassInfo<R> pass, OrderedSubmitNodeCollector tasks, @Nullable RenderType type) {
        submitArmorGeometry(pass, tasks, type, pass.renderColor());
    }

    void submitArmorGeometry(RenderPassInfo<R> pass, OrderedSubmitNodeCollector tasks,
                             @Nullable RenderType type, int color) {
        if (type == null) return;
        EquipmentSlot slot = pass.renderState().getGeckolibData(DataTickets.EQUIPMENT_SLOT);
        Set<String> roots = new HashSet<>();
        for (ArmorSegment segment : getSegmentsForSlot(pass.renderState(), slot))
            roots.add(getBoneNameForSegment(pass.renderState(), segment));
        for (String extra : EXTRA_BONES) roots.add(extra);

        tasks.submitCustomGeometry(pass.poseStack(), type, (pose, buffer) -> {
            PoseStack poses = pass.poseStack();
            poses.pushPose();
            poses.last().set(pose);
            pass.renderPosed(() -> {
                for (ArmorSegment segment : getSegmentsForSlot(pass.renderState(), slot)) {
                    pass.model().getBone(getBoneNameForSegment(pass.renderState(), segment)).ifPresent(bone ->
                            bone.positionAndRender(pass, buffer, pass.packedLight(), pass.packedOverlay(), color));
                }
                for (String extra : EXTRA_BONES) {
                    pass.model().getBone(extra).ifPresent(bone -> {
                        if (!hasRenderedParent(bone, roots))
                            bone.positionAndRender(pass, buffer, pass.packedLight(), pass.packedOverlay(), color);
                    });
                }
            });
            poses.popPose();
        });
    }

    private static boolean hasRenderedParent(GeoBone bone, Set<String> roots) {
        for (GeoBone parent = bone.parent(); parent != null; parent = parent.parent()) {
            if (roots.contains(parent.name())) return true;
        }
        return false;
    }

    private static void setHiddenSafe(@Nullable BoneSnapshot bone, boolean hidden) {
        if (bone != null) bone.skipRender(hidden).skipChildrenRender(hidden);
    }
}
