package net.kenddie.fantasyarmor.client.render;

import net.kenddie.fantasyarmor.shared.client.CapePhysics;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;

public final class FARenderUtils {

    /**
     * In 1.21.10+ cloak motion is precomputed into AvatarRenderState (capeFlap/capeLean/capeLean2).
     * We reuse the same "final rotation idea" you used previously, but based on state values.
     */
    public static void applyCapeRotation(AvatarRenderState state, GeoBone bone) {
        CapePhysics.CapeRotation rot = CapePhysics.computeCapeRotation(
                state.capeFlap, state.capeLean, state.capeLean2, state.isCrouching);
        bone.updateRotation(rot.rotX(), rot.rotY(), rot.rotZ());
    }

    public static void setFrontLegCapeAngle(@Nullable GeoBone leftLeg, @Nullable GeoBone rightLeg, GeoBone frontCape) {
        if (leftLeg == null || rightLeg == null) return;

        frontCape.setRotX(CapePhysics.computeFrontCapeAngle(leftLeg.getRotX(), rightLeg.getRotX()));
    }

    public static void applyBraidRotation(AvatarRenderState state, GeoBone braid) {
        applyCapeRotation(state, braid);

        float xRot = state.xRot;
        if (xRot > 60) {
            braid.setRotX(0);
        } else if (xRot < -25) {
            braid.setRotX((float) Math.toRadians(xRot));
        }
    }

    public static void setArmsVisibility(PlayerModel model, boolean visible) {
        model.rightArm.visible = visible;
        model.rightSleeve.visible = visible;
        model.leftArm.visible = visible;
        model.leftSleeve.visible = visible;
    }

    private FARenderUtils() {}
}
