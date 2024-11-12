package net.kenddie.fantasyarmor.entity.client.armor.render.lib;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

@OnlyIn(Dist.CLIENT)
public final class FARenderUtils {
    public static void applyCapeRotation(Player player, GeoBone bone, float partialTick) {
        // See net.minecraft.client.renderer.entity.layers.CapeLayer

        double d0 = Mth.lerp(partialTick, player.xCloakO, player.xCloak)
                - Mth.lerp(partialTick, player.xo, player.getX());
        double d1 = Mth.lerp(partialTick, player.yCloakO, player.yCloak)
                - Mth.lerp(partialTick, player.yo, player.getY());
        double d2 = Mth.lerp(partialTick, player.zCloakO, player.zCloak)
                - Mth.lerp(partialTick, player.zo, player.getZ());

        float f = Mth.rotLerp(partialTick, player.yBodyRotO, player.yBodyRot);
        double d3 = Mth.sin(f * (float) (Math.PI / 180.0));
        double d4 = -Mth.cos(f * (float) (Math.PI / 180.0));

        float f1 = (float)d1 * 10.0F;
        f1 = Mth.clamp(f1, -6.0F, 32.0F);
        float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
        f2 = Mth.clamp(f2, 0.0F, 150.0F);
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
        f3 = Mth.clamp(f3, -20.0F, 20.0F);
        if(f2 < 0.0F) {
            f2 = 0.0F;
        }

        float f4 = Mth.lerp(partialTick, player.oBob, player.bob);
        f1 += Mth.sin(Mth.lerp(partialTick, player.walkDistO, player.walkDist) * 6.0F) * 32.0F * f4;
        if(player.isCrouching()) {
            f1 += 25.0F;
        }

        bone.updateRotation(
                (float) -Math.toRadians(6.0F + f2 / 2.0F + f1),
                (float) Math.toRadians(f3 / 2.0F),
                (float) Math.toRadians(f3 / 2.0F)
        );
    }

    public static <T extends Item & GeoItem> void setFrontLegCapeAngle(GeoArmorRenderer<T> renderer, GeoBone bone) {
        if(renderer.getLeftLegBone() == null || renderer.getRightLegBone() == null) {
            return;
        }
        float legRot = Math.min(renderer.getLeftLegBone().getRotX(), renderer.getRightLegBone().getRotX());
        bone.setRotX((legRot > 0 ? 0 : legRot) * -1.2f);
    }

    //TODO: Set cloak visibility false if armor has a cloak.
    public static <T extends LivingEntity> void setArmsVisibility(PlayerModel<T> model, boolean visible) {
        model.rightArm.visible = visible;
        model.rightSleeve.visible = visible;
        model.leftArm.visible = visible;
        model.leftSleeve.visible = visible;
    }
}
