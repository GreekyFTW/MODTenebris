package net.Greek.Tenebris.entity.ability;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Greek.Tenebris.entity.ability.model.PaintBallEntityModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static net.Greek.Tenebris.Tenebris.rl;

public class PaintBallRenderer<T extends PaintBallEntity> extends EntityRenderer<T> {
    private static final ResourceLocation PAINTBALL_TEXTURE = rl("textures/entity/texture.png");
    PaintBallEntityModel<PaintBallEntity> model;

    public PaintBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new PaintBallEntityModel<>(context.bakeLayer(PaintBallEntityModel.LAYER_LOCATION));
    }


    @Override
        public void render(T entity, float yaw, float partialTicks,PoseStack poseStack, MultiBufferSource buffer, int light){
        VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(PAINTBALL_TEXTURE));
        this.model.renderToBuffer(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,0x00FFFFFF);

        super.render(entity, yaw, partialTicks, poseStack, buffer, light);
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(PaintBallEntity entity) {
        return rl("textures/entity/texture.png");
    }


}