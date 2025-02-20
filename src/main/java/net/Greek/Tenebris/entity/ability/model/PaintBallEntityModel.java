package net.Greek.Tenebris.entity.ability.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

import static net.Greek.Tenebris.Tenebris.rl;

public class PaintBallEntityModel <T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation( rl("paintball_model.json"), "main");
    private final ModelPart bb_main;

    public PaintBallEntityModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("bb_main",
                CubeListBuilder.create().texOffs(0, 0).
                        addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F,
                                new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    }

    @Override
    public void renderToBuffer( PoseStack pPoseStack,  VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        bb_main.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
    }

}
