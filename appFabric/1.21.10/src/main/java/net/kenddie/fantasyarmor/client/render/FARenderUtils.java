package net.kenddie.fantasyarmor.client.render;

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
        float flap = state.capeFlap;
        float lean = state.capeLean;
        float lean2 = state.capeLean2;

        bone.updateRotation(
                (float) -Math.toRadians(6.0F + lean / 2.0F + flap),
                (float) Math.toRadians(lean2 / 2.0F),
                (float) Math.toRadians(lean2 / 2.0F)
        );
    }

    public static void setFrontLegCapeAngle(@Nullable GeoBone leftLeg, @Nullable GeoBone rightLeg, GeoBone frontCape) {
        if (leftLeg == null || rightLeg == null) return;

        float legRot = Math.min(leftLeg.getRotX(), rightLeg.getRotX());
        frontCape.setRotX((legRot > 0 ? 0 : legRot) * -1.2f);
    }

    public static void applyBraidRotation(AvatarRenderState state, GeoBone braid) {
        applyCapeRotation(state, braid);

        float xRot = state.xRot;
        if (xRot > 60) {
            braid.setRotX(0);
        } else if (xRot < -35) {
            braid.setRotX((float) Math.toRadians(30));
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
