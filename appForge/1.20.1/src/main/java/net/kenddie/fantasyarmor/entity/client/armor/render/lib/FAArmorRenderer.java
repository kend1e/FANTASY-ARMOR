package net.kenddie.fantasyarmor.entity.client.armor.render.lib;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kenddie.fantasyarmor.FantasyArmor;
import net.kenddie.fantasyarmor.config.FAConfig;
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
import software.bernie.geckolib.util.RenderUtils;

public class FAArmorRenderer<T extends FAArmorItem> extends GeoArmorRenderer<T> {
    protected GeoBone cape = null;
    protected GeoBone frontCape = null;
    protected GeoBone leftLegCloth = null;
    protected GeoBone rightLegCloth = null;
    protected GeoBone braid = null;
    protected GeoBone epicFightCape = null;

    public FAArmorRenderer(GeoModel<T> model) {
        super(model);
    }

    @Nullable
    public GeoBone getCapeBone(GeoModel<T> model) {
        return model.getBone("armorCape").orElse(null);
    }

    @Nullable
    public GeoBone getFrontCapeBone(GeoModel<T> model) {
        return model.getBone("armorFrontCape").orElse(null);
    }

    @Nullable
    public GeoBone getLeftLegClothBone(GeoModel<T> model) {
        return model.getBone("armorLeftLegCloth").orElse(null);
    }

    @Nullable
    public GeoBone getRightLegClothBone(GeoModel<T> model) {
        return model.getBone("armorRightLegCloth").orElse(null);
    }

    @Nullable
    public GeoBone getBraidBone(GeoModel<T> model) {
        return model.getBone("armorBraid").orElse(null);
    }

    @Nullable
    public GeoBone getEpicFightCapeBone(GeoModel<T> model) {
        return model.getBone("armorCapeEpicFight").orElse(null);
    }

    @Override
    protected void grabRelevantBones(BakedGeoModel bakedModel) {
        super.grabRelevantBones(bakedModel);

        GeoModel<T> model = getGeoModel();
        cape = getCapeBone(model);
        frontCape = getFrontCapeBone(model);
        leftLegCloth = getLeftLegClothBone(model);
        rightLegCloth = getRightLegClothBone(model);
        braid = getBraidBone(model);
        epicFightCape = getEpicFightCapeBone(model);
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        super.applyBoneVisibilityBySlot(currentSlot);

        if (currentSlot == EquipmentSlot.CHEST) {
            boolean showCape = FAConfig.showCapes;
            boolean useEpicFightCape = FantasyArmor.isEpicFightLoaded && !FAConfig.epicFightNotStaticCapes;

            setBoneVisible(cape, showCape && !useEpicFightCape);
            setBoneVisible(epicFightCape, showCape && useEpicFightCape);

            setBoneVisible(frontCape, true);
            setBoneVisible(leftLegCloth, true);
            setBoneVisible(rightLegCloth, true);
        } else if (currentSlot == EquipmentSlot.HEAD) {
            setBoneVisible(braid, true);
        }
    }

    @Override
    public void applyBoneVisibilityByPart(EquipmentSlot currentSlot, ModelPart currentPart, HumanoidModel<?> model) {
        super.applyBoneVisibilityByPart(currentSlot, currentPart, model);

        if (currentPart == model.body) {
            boolean showCape = FAConfig.showCapes;
            boolean useEpicFightCape = FantasyArmor.isEpicFightLoaded && !FAConfig.epicFightNotStaticCapes;

            if (cape != null) cape.setHidden(!(showCape && !useEpicFightCape));
            if (epicFightCape != null) epicFightCape.setHidden(!(showCape && useEpicFightCape));

            if (frontCape != null) frontCape.setHidden(false);
            if (leftLegCloth != null) leftLegCloth.setHidden(false);
            if (rightLegCloth != null) rightLegCloth.setHidden(false);
        } else if (currentSlot == EquipmentSlot.HEAD) {
            if (braid != null) braid.setHidden(false);
        }
    }

    @Override
    public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

        if (frontCape != null) {
            FARenderUtils.setFrontLegCapeAngle(this, frontCape);
        }

        if (cape != null) {
            if (currentEntity instanceof Player player) {
                FARenderUtils.applyCapeRotation(player, cape, partialTick);
            } else {
                cape.updateRotation((float) -Math.toRadians(5.0F), 0.0F, 0.0F);
            }
        }

        if (braid != null && currentEntity instanceof Player player) {
            FARenderUtils.applyBraidRotation(player, braid, partialTick);
        }
    }

    @Override
    protected void applyBaseTransformations(HumanoidModel<?> baseModel) {
        super.applyBaseTransformations(baseModel);

        if(cape != null) {
            ModelPart bodyPart = baseModel.body;

            cape.updatePosition(bodyPart.x, 1 - bodyPart.y, bodyPart.z);
        }

        if(frontCape != null) {
            ModelPart leftLegPart = baseModel.leftLeg;

            frontCape.updatePosition(leftLegPart.x - 1.95f, 13 - leftLegPart.y, leftLegPart.z - 0.1f);
        }

        if(leftLegCloth != null) {
            ModelPart leftLegPart = baseModel.leftLeg;

            RenderUtils.matchModelPartRot(leftLegPart, leftLegCloth);
            leftLegCloth.updatePosition(leftLegPart.x - 2, 12 - leftLegPart.y, leftLegPart.z);
        }

        if(rightLegCloth != null) {
            ModelPart rightLegPart = baseModel.rightLeg;

            RenderUtils.matchModelPartRot(rightLegPart, rightLegCloth);
            rightLegCloth.updatePosition(rightLegPart.x + 2, 12 - rightLegPart.y, rightLegPart.z);
        }
    }

    @Override
    public void setAllVisible(boolean pVisible) {
        super.setAllVisible(pVisible);

        boolean showCape = FAConfig.showCapes && pVisible;
        boolean useEpicFightCape = FantasyArmor.isEpicFightLoaded && !FAConfig.epicFightNotStaticCapes;

        setBoneVisible(cape, showCape && !useEpicFightCape);
        setBoneVisible(epicFightCape, showCape && useEpicFightCape);

        setBoneVisible(cape, pVisible);
        setBoneVisible(frontCape, pVisible);
        setBoneVisible(leftLegCloth, pVisible);
        setBoneVisible(rightLegCloth, pVisible);
        setBoneVisible(braid, pVisible);
        setBoneVisible(epicFightCape, pVisible);
    }
}