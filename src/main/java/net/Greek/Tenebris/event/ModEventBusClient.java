/*package net.Greek.Tenebris.event;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.Greek.Tenebris.block.entityrenderers.ReiAyanamiPlushBlockRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClient {
    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.REI_BE.get(), ReiAyanamiPlushBlockRenderer::new);
    }
    @SubscribeEvent
    static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(ReiAyanamiPlushBlockRenderer.MODEL_LOCATION);
    }
}*/
