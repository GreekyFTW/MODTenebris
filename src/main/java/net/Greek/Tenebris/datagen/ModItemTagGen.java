package net.Greek.Tenebris.datagen;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGen extends ItemTagsProvider {


    public ModItemTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags,@Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider){
        this.tag(ItemTags.SWORDS).add(ModItems.CLAYMORE.get());
    };
}
