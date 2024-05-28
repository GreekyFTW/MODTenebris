package net.Greek.Tenebris.block.entity.plushies;

import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class BasePlushBlockEntity extends BlockEntity {
    public BasePlushBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BASE_BE.get(), pPos, pBlockState);
    }

}
