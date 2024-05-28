package net.Greek.Tenebris.block.entity;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.ModBlocks;

import net.Greek.Tenebris.block.entity.plushies.BasePlushBlockEntity;
//import net.Greek.Tenebris.block.entity.plushies.ReiAyanamiPlushBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE,Tenebris.MOD_ID);


    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<BasePlushBlockEntity>> BASE_BE =
            BLOCK_ENTITIES.register("basebe",
                    () -> BlockEntityType.Builder.of(BasePlushBlockEntity::new,
                            ModBlocks.REI_CHIQUITA.get(),
                            ModBlocks.ASUKA_CHIQUITA.get(),
                            ModBlocks.SHINJI_CHIQUITA.get(),
                            ModBlocks.KALLEN_CHIQUITA.get())
                            .build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }


}
