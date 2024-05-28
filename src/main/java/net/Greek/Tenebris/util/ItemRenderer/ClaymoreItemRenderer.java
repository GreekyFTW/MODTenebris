package net.Greek.Tenebris.util.ItemRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
//import net.minecraft.world.item.ItemDisplayContext;
//import net.minecraft.world.item.ItemStack;
//
//public class ClaymoreItemRenderer extends BlockEntityWithoutLevelRenderer {
//
//    private ClaymoreModel model;
//    //private final EntityModelSet set;
//
//    public ClaymoreItemRenderer() {
//
//        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
//        this.model = new ClaymoreModel();
//    }
//
//    @Override
//    public void renderByItem(ItemStack stack, ItemDisplayContext display, PoseStack poseStack, MultiBufferSource source, int light, int overlay)
//    {
//        // Hack to remove transforms created by ItemRenderer#render
//        poseStack.popPose();
//        BakedModel model;
//
//        poseStack.pushPose();
//
//        poseStack.popPose();
//
//        // Push the stack again since we popped the pose prior
//        poseStack.pushPose();
//    }
//
//}
