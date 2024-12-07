package net.Greek.Tenebris.datagen;


import net.Greek.Tenebris.Tenebris;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD)

public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        ModBlockTagGen blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGen(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGen(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

    }

}
