package net.Greek.Tenebris.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ReiAyanamiPlushBlockEntity extends BlockEntity {
    public ReiAyanamiPlushBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.REI_BE.get(), pPos, pBlockState);
    }
}
