/*package net.Greek.Tenebris.block.entity;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.ModBlocks;

import net.Greek.Tenebris.block.custom.ReiAyanamiPlushBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE,Tenebris.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<ReiAyanamiPlushBlock>> REI_BE =
            BLOCK_ENTITIES.register("reiBE",
                    () -> BlockEntityType.Builder.of(ReiAyanamiPlushBlock::new, ModBlocks.REI_CHIQUITA.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }


}*/
