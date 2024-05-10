package net.Greek.Tenebris.block;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.custom.ReiAyanamiPlushBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.Greek.Tenebris.item.ModItems.ITEMS;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Tenebris.MOD_ID);

    public static final DeferredBlock<Block> REI_CHIQUITA = BLOCKS.register("rei_plush", ReiAyanamiPlushBlock::new );
    
    public static final DeferredItem<BlockItem> REI_PLUSH_ITEM = ITEMS.registerSimpleBlockItem("rei_plush", REI_CHIQUITA);

public static void register(IEventBus eventBus){
    BLOCKS.register(eventBus);
}

}
