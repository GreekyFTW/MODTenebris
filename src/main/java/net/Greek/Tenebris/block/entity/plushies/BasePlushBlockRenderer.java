package net.Greek.Tenebris.block.entity.plushies;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;

import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.Greek.Tenebris.block.ModBlocks;

import static net.Greek.Tenebris.Tenebris.rl;
import static net.Greek.Tenebris.block.custom.plushies.BasePlushBlock.LIT;
//import static net.minecraft.resources.ResourceLocation.rl;

public class BasePlushBlockRenderer implements BlockEntityRenderer<BasePlushBlockEntity> {

    /*public static final ResourceLocation MODEL_LOCATION_REI = new ResourceLocation("block/rei_plush");
    public static final ResourceLocation MODEL_LOCATION_ASUKA = new ResourceLocation("block/asuka_plush");
    public static final ResourceLocation MODEL_LOCATION_SHINJI = new ResourceLocation("block/shinji_plush");*/

    private final ModelBlockRenderer modelRenderer;
    private final ModelManager modelManager;

    public BasePlushBlockRenderer(BlockEntityRendererProvider.Context context) {
        BlockRenderDispatcher blockRenderDispatcher = context.getBlockRenderDispatcher();
        this.modelRenderer = blockRenderDispatcher.getModelRenderer();
        this.modelManager = blockRenderDispatcher.getBlockModelShaper().getModelManager();
    }

    @Override
    public void render(BasePlushBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        BakedModel model;
        BlockState blockState = pBlockEntity.getBlockState();
        Block block = blockState.getBlock();

        if(!pBlockEntity.getBlockState().getValue(LIT)) {


            if (block == ModBlocks.REI_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/rei_plush")));
            else if (block == ModBlocks.ASUKA_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/asuka_plush")));
            else if (block == ModBlocks.SHINJI_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/shinji_plush")));
            else if (block == ModBlocks.KALLEN_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/kallen_plush")));
            else if (block == ModBlocks.DENJI_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/denji_plush")));
            else if (block == ModBlocks.CSM_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/csm_plush")));
            else if (block == ModBlocks.GRACE_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/grace_plush")));
            else if (block == ModBlocks.NAVIA_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/navia_plush")));
            else
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/rei_plush")));


        }else{


            if (block == ModBlocks.REI_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/rei_plush")));
            else if (block == ModBlocks.ASUKA_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/asuka_plush")));
            else if (block == ModBlocks.SHINJI_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/shinji_plush")));
            else if (block == ModBlocks.KALLEN_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/kallen_plush")));
            else if (block == ModBlocks.DENJI_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/csm_plush")));
            else if (block == ModBlocks.CSM_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/denji_plush")));
            else if (block == ModBlocks.GRACE_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/grace_plush")));
            else if (block == ModBlocks.NAVIA_CHIQUITA.get())
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/navia_plush")));
            else
                model = modelManager.getModel(ModelResourceLocation.standalone(rl("block/rei_plush")));
        }


        ModelData modelData = pBlockEntity.getModelData();
        pPoseStack.pushPose();
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(-RotationSegment.convertToDegrees(blockState.getValue(BlockStateProperties.ROTATION_16))), 0.5f, 0, 0.5f);
        RenderType renderType = RenderType.entitySolid(InventoryMenu.BLOCK_ATLAS);
        modelRenderer.renderModel(pPoseStack.last(), pBuffer.getBuffer(renderType), blockState, model, 1, 1, 1, pPackedLight, pPackedOverlay, modelData, renderType);
        pPoseStack.popPose();
    }

    }
