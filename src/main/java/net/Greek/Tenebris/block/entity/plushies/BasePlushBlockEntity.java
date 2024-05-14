package net.Greek.Tenebris.block.entity.plushies;

import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasePlushBlockEntity extends BlockEntity {
    public BasePlushBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BASE_BE.get(), pPos, pBlockState);
    }
}
