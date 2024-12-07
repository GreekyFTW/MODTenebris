
package net.Greek.Tenebris.event.client.handler;

import net.Greek.Tenebris.GUI.overlay.SpellSlotOverlay;
import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.Greek.Tenebris.block.entity.plushies.BasePlushBlockRenderer;
import net.Greek.Tenebris.event.client.PlayerEvents;
import net.Greek.Tenebris.init.DataComponentRegistry;
import net.Greek.Tenebris.item.ModItemProperties;
import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

import static net.Greek.Tenebris.Tenebris.rl;
import static net.Greek.Tenebris.init.DataComponentRegistry.TRANSFORMED;

@Mod(Tenebris.MOD_ID)
public class ClientEventHandler {

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(PlayerEvents.class);
            event.enqueueWork(ModItemProperties::addCustomItemProperties);
        }
    }

    @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientSetup {
        @SubscribeEvent
        static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.BASE_BE.get(), BasePlushBlockRenderer::new);

        }

        @SubscribeEvent
        static void registerCapabilities(RegisterCapabilitiesEvent event) {
        }

        @SubscribeEvent
        static void registerModels(ModelEvent.RegisterAdditional event) {
            event.register(ModelResourceLocation.standalone(rl("block/rei_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/asuka_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/shinji_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/kallen_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/denji_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/csm_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/grace_plush")));
            event.register(ModelResourceLocation.standalone(rl("block/navia_plush")));



            event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(Tenebris.MOD_ID, "item/shaft")));
            event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(Tenebris.MOD_ID, "item/claymore")));

        }

    }

}


