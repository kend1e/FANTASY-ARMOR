package net.kenddie.fantasyarmor.client.render;

import net.kenddie.fantasyarmor.shared.client.CapePhysics;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class FARenderUtils {
    public static void applyCapeRotation(Player player, GeoBone bone, float partialTick) {
        double dx = Mth.lerp(partialTick, player.xCloakO, player.xCloak)
                - Mth.lerp(partialTick, player.xo, player.getX());
        double dy = Mth.lerp(partialTick, player.yCloakO, player.yCloak)
                - Mth.lerp(partialTick, player.yo, player.getY());
        double dz = Mth.lerp(partialTick, player.zCloakO, player.zCloak)
                - Mth.lerp(partialTick, player.zo, player.getZ());

        float bodyRot = Mth.rotLerp(partialTick, player.yBodyRotO, player.yBodyRot);
        float walkBob = Mth.lerp(partialTick, player.oBob, player.bob);
        float walkDist = Mth.lerp(partialTick, player.walkDistO, player.walkDist);

        float[] motion = CapePhysics.computeCapeMotion(dx, dy, dz, bodyRot, walkBob, walkDist);
        CapePhysics.CapeRotation rot = CapePhysics.computeCapeRotation(motion[0], motion[1], motion[2], false);
        bone.updateRotation(rot.rotX(), rot.rotY(), rot.rotZ());
    }

    public static <T extends Item & GeoItem> void setFrontLegCapeAngle(GeoArmorRenderer<T> renderer, GeoBone bone) {
        if(renderer.getLeftLegBone(renderer.getGeoModel()) == null || renderer.getRightLegBone(renderer.getGeoModel()) == null) {
            return;
        }
        GeoBone leftLegBone = renderer.getLeftLegBone(renderer.getGeoModel());
        GeoBone rightLegBone = renderer.getRightLegBone(renderer.getGeoModel());
        if (leftLegBone == null || rightLegBone == null) return;
        bone.setRotX(CapePhysics.computeFrontCapeAngle(leftLegBone.getRotX(), rightLegBone.getRotX()));
    }

    public static void applyBraidRotation(Player player, GeoBone braid, float partialTick) {
        applyCapeRotation(player, braid, partialTick);
        if (player.getXRot() > 60) {
            braid.setRotX(0);
        } else if (player.getXRot() < -35) {
            braid.setRotX(30);
        }
    }

    //TODO: Set cloak visibility false if armor has a cloak.
    public static <T extends LivingEntity> void setArmsVisibility(PlayerModel<T> model, boolean visible) {
        model.rightArm.visible = visible;
        model.rightSleeve.visible = visible;
        model.leftArm.visible = visible;
        model.leftSleeve.visible = visible;
    }
}
