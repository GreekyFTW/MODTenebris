
package net.Greek.Tenebris.block.custom.plushies;

import com.mojang.serialization.MapCodec;
import net.Greek.Tenebris.block.ModBlocks;
import net.Greek.Tenebris.block.entity.plushies.BasePlushBlockEntity;
import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.util.CustomBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.Greek.Tenebris.sound.ModSounds;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class BasePlushBlock extends BaseEntityBlock {

    private static final VoxelShape COLLISION_SHAPE = Block.box(3.5, 0, 3.5, 12.5, 13.3, 12.5);
    //private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final int MAX = RotationSegment.getMaxSegmentIndex();
    private static final int ROTATIONS = MAX + 1;
    private static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    //private static final MapCodec<BasePlushBlock> CODEC = simpleCodec(RedstoneLampBlock::new);
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty HASCOLLISION = CustomBlockState.HASCOLLISION;

    public BasePlushBlock() {
        super(BlockBehaviour.Properties.of()
                .noOcclusion()
                .lightLevel($->1)
                .sound(SoundType.WOOL)
                .destroyTime(1)
                .dynamicShape());
        //this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        this.registerDefaultState(this.defaultBlockState().setValue(ROTATION, Integer.valueOf(0)));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
        this.registerDefaultState(this.defaultBlockState().setValue(HASCOLLISION, Boolean.valueOf(true)));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        //return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
        //return super.getStateForPlacement(pContext).setValue(ROTATION, Integer.valueOf(RotationSegment.convertToSegment(pContext.getRotation()))) ;

        return this.defaultBlockState()
                .setValue(ROTATION, Integer.valueOf(RotationSegment.convertToSegment(pContext.getRotation())))
                .setValue(LIT, Boolean.valueOf(pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())));

    }

    @Override
    protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(LIT);
            if (flag != pLevel.hasNeighborSignal(pPos)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 0);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(LIT), 2);
                }
            }
        }
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        if (pState.getValue(LIT) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(LIT), 2);
        }

    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext gContext){
        return COLLISION_SHAPE;
    }

    @Nullable //remember to override
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BasePlushBlockEntity(pPos, pState);
    }

    /*@Override
    protected BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }*/

    @Override
    protected BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(ROTATION, Integer.valueOf(pRotation.rotate(pState.getValue(ROTATION), ROTATIONS)));
    }

    @Override
    protected BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.setValue(ROTATION, Integer.valueOf(pMirror.mirror(pState.getValue(ROTATION), ROTATIONS)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ROTATION);
        pBuilder.add(LIT);
        pBuilder.add(HASCOLLISION);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (state.getBlock() == ModBlocks.CSM_CHIQUITA.get() || state.getBlock()== ModBlocks.DENJI_CHIQUITA.get()) {
            //level.playSound(player, pos, ModSounds.CHAINSAWREV.get(), SoundSource.BLOCKS);
        }else{
            level.playSound(player, pos, ModSounds.SQUEEK.get(), SoundSource.BLOCKS);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(HASCOLLISION)) {
            return COLLISION_SHAPE;
        } else {
            return Shapes.empty();
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
    {

        if(player.getItemInHand(interactionHand).is(ModItems.STUFFING.get())) {

            if(!state.getValue(HASCOLLISION)) {
                //state.setValue(HASCOLLISION, Boolean.TRUE);
                level.setBlock(pos, state.cycle(HASCOLLISION), 2);
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal(" yes col"+ state.getValue(HASCOLLISION)));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }


        }else if(player.getItemInHand(interactionHand).is(Items.SHEARS)) {

            if(state.getValue(HASCOLLISION)) {
                level.setBlock(pos, state.cycle(HASCOLLISION), 2);
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal(" no col "+  state.getValue(HASCOLLISION)));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

        } else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }




        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }






}