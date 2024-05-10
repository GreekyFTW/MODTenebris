package net.Greek.Tenebris.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;

import static net.Greek.Tenebris.block.ModBlocks.REI_PLUSH_ITEM;

import static net.Greek.Tenebris.item.ModItems.CLAYMORE_TRANSFOMED;
import static net.Greek.Tenebris.item.ModItems.SHAFT;


public class ModCreativeModeTab {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
                DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tenebris.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> Tenebrae = CREATIVE_MODE_TABS.register("tenebrae",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.tenebrae"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> SHAFT.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(SHAFT.get());
                        output.accept(REI_PLUSH_ITEM.get());
                        output.accept(CLAYMORE_TRANSFOMED.get());
                    })
                    .build()

    );


    public static void  register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
