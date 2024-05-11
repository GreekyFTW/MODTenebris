package net.Greek.Tenebris.block.entityrenderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.custom.ReiAyanamiPlushBlock;
import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.Greek.Tenebris.block.entity.ReiAyanamiPlushBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.data.ModelData;

public class ReiAyanamiPlushBlockRenderer implements BlockEntityRenderer<ReiAyanamiPlushBlockEntity> {
    public static final ResourceLocation MODEL_LOCATION = new ResourceLocation(Tenebris.MOD_ID, "block/rei_plush");
    private final ModelBlockRenderer modelRenderer;
    private final ModelManager modelManager;

    public ReiAyanamiPlushBlockRenderer(BlockEntityRendererProvider.Context context) {
        BlockRenderDispatcher blockRenderDispatcher = context.getBlockRenderDispatcher();
        this.modelRenderer = blockRenderDispatcher.getModelRenderer();
        this.modelManager = blockRenderDispatcher.getBlockModelShaper().getModelManager();
    }

    @Override
    public void render(ReiAyanamiPlushBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        BakedModel model = modelManager.getModel(MODEL_LOCATION);
        BlockState blockState = pBlockEntity.getBlockState();
        ModelData modelData = pBlockEntity.getModelData();
        pPoseStack.pushPose();
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(-RotationSegment.convertToDegrees(blockState.getValue(BlockStateProperties.ROTATION_16))), 0.5f, 0, 0.5f);
        RenderType renderType = RenderType.entitySolid(InventoryMenu.BLOCK_ATLAS);
        modelRenderer.renderModel(pPoseStack.last(), pBuffer.getBuffer(renderType), blockState, model, 1, 1, 1, pPackedLight, pPackedOverlay, modelData, renderType);
        pPoseStack.popPose();
    }

}
