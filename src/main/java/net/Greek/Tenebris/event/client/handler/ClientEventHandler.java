
package net.Greek.Tenebris.event.client.handler;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.Greek.Tenebris.block.entity.ModEntities;
import net.Greek.Tenebris.entity.ability.PaintBallEntity;
import net.Greek.Tenebris.entity.ability.PaintBallRenderer;
import net.Greek.Tenebris.entity.ability.model.PaintBallEntityModel;
import net.Greek.Tenebris.block.entity.plushies.BasePlushBlockRenderer;
import net.Greek.Tenebris.event.client.PlayerEvents;
import net.Greek.Tenebris.item.ModItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.common.NeoForge;

import static net.Greek.Tenebris.Tenebris.rl;

@Mod(Tenebris.MOD_ID)
public class ClientEventHandler {

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(PlayerEvents.class);
            //NeoForge.EVENT_BUS.register(DashPlayerEvent.class);
            event.enqueueWork(ModItemProperties::addCustomItemProperties);
        }
    }

    @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientSetup {

        @SubscribeEvent
        public static void onRegisterLayerDefinitionsEvent(final EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(PaintBallEntityModel.LAYER_LOCATION, PaintBallEntityModel::createBodyLayer);
        }

        @SubscribeEvent
        static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.BASE_BE.get(), BasePlushBlockRenderer::new);
            event.registerEntityRenderer(ModEntities.PAINTBALL_ENTITY.get(), PaintBallRenderer<PaintBallEntity>::new);
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


