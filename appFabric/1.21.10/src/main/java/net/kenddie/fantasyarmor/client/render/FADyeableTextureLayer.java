package net.kenddie.fantasyarmor.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.TextureLayerGeoLayer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

public class FADyeableTextureLayer<
        T extends net.minecraft.world.item.Item & software.bernie.geckolib.animatable.GeoItem,
        R extends net.minecraft.client.renderer.entity.state.HumanoidRenderState & GeoRenderState
        > extends TextureLayerGeoLayer<T, GeoArmorRenderer.RenderData, R> {

    public static final DataTicket<Boolean> HAS_DYE_TICKET =
            DataTicket.create("fantasyarmor_has_dye", Boolean.class);
    public static final DataTicket<Integer> DYE_COLOR_TICKET =
            DataTicket.create("fantasyarmor_dye_color", Integer.class);
    public static final DataTicket<ResourceLocation> OVERLAY_TEX_TICKET =
            DataTicket.create("fantasyarmor_overlay_tex", ResourceLocation.class);

    public FADyeableTextureLayer(GeoArmorRenderer<T, R> renderer) {
        super(renderer, net.minecraft.client.renderer.texture.MissingTextureAtlasSprite.getLocation());
    }

    @Override
    protected ResourceLocation getTextureResource(R renderState) {
        ResourceLocation overlayTex = renderState.getOrDefaultGeckolibData(OVERLAY_TEX_TICKET, null);
        if (overlayTex != null)
            return overlayTex;

        return net.minecraft.client.renderer.texture.MissingTextureAtlasSprite.getLocation();
    }

    @Override
    protected RenderType getRenderType(R renderState) {
        ResourceLocation tex = getTextureResource(renderState);
        return RenderType.armorCutoutNoCull(tex);
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

        RenderType type = getRenderType(renderState);
        if (type == null)
            return;

        this.renderer.buildRenderTask(
                renderState,
                poseStack,
                bakedModel,
                this.renderer.getGeoModel(),
                renderTasks.order(1),
                cameraState,
                type,
                packedLight,
                packedOverlay,
                dyeColor,
                null
        );
    }
}
