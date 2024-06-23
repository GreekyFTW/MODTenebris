package net.Greek.Tenebris.block;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.custom.plushies.BasePlushBlock;
//import net.Greek.Tenebris.block.custom.plushies.AsukaPlushBlock;
//import net.Greek.Tenebris.block.custom.plushies.ShinjiPlushBlock;
//import net.Greek.Tenebris.block.custom.plushies.KallenPlushBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.Greek.Tenebris.item.ModItems.ITEMS;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Tenebris.MOD_ID);

    public static final DeferredBlock<Block> REI_CHIQUITA = BLOCKS.register("rei_plush", BasePlushBlock::new );
    public static final DeferredBlock<Block> ASUKA_CHIQUITA = BLOCKS.register("asuka_plush",  BasePlushBlock::new );
    public static final DeferredBlock<Block> SHINJI_CHIQUITA = BLOCKS.register("shinji_plush",  BasePlushBlock::new );
    public static final DeferredBlock<Block> KALLEN_CHIQUITA = BLOCKS.register("kallen_plush",  BasePlushBlock::new );
    public static final DeferredBlock<Block> DENJI_CHIQUITA = BLOCKS.register("denji_plush",  BasePlushBlock::new );
    public static final DeferredBlock<Block> CSM_CHIQUITA = BLOCKS.register("csm_plush",  BasePlushBlock::new );


    public static final DeferredItem<BlockItem> REI_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("rei_plush", REI_CHIQUITA);
    public static final DeferredItem<BlockItem> ASUKA_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("asuka_plush", ASUKA_CHIQUITA);
    public static final DeferredItem<BlockItem> SHINJI_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("shinji_plush", SHINJI_CHIQUITA);
    public static final DeferredItem<BlockItem> KALLEN_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("kallen_plush", KALLEN_CHIQUITA);
    public static final DeferredItem<BlockItem> DENJI_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("denji_plush", DENJI_CHIQUITA);
    public static final DeferredItem<BlockItem> CSM_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("csm_plush", CSM_CHIQUITA);

public static void register(IEventBus eventBus){
    BLOCKS.register(eventBus);
}

}
