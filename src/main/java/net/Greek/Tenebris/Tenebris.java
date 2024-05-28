package net.Greek.Tenebris;

import net.Greek.Tenebris.block.ModBlocks;
import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.Greek.Tenebris.item.ModCreativeModeTab;
import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.sound.ModSounds;

import net.Greek.Tenebris.client.entityrenderers.plushies.BasePlushBlockRenderer;
//import net.Greek.Tenebris.client.entityrenderers.plushies.ReiAyanamiPlushBlockRenderer;

//import net.Greek.Tenebris.util.ItemRenderer.ClaymoreItemRenderer;
import net.Greek.Tenebris.util.KeyBindings;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.sound.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static net.Greek.Tenebris.client.event.EventKeyInput.IsTransformed;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Tenebris.MOD_ID)
public class Tenebris
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tenebris";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Tenebris(IEventBus modEventBus, ModContainer modContainer)
    {

        ModCreativeModeTab.register(modEventBus); //creative mode tab

        ModItems.register(modEventBus); //items
        ModBlocks.register(modEventBus); //bq           locks

        ModBlockEntities.register(modEventBus); //block entities

        //ModSounds.register(modEventBus); //sounds
        //ModSounds.SOUND_EVENTS.register(modEventBus);

        ModSounds.SOUND_EVENTS.register(modEventBus);
        //NeoForge.EVENT_BUS.register(MusicEvent.class);

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the ingredients tab
    private void addCreative(BuildCreativeModeTabContentsEvent event){
//        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
//            event.accept(ModItems.SHAFT);
//            event.accept(ModItems.CHROMATIC_COMPOUND);
//            event.accept(ModItems.CLAYMORE);
//            event.accept(ModItems.TRANSFORMER);
//        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() ->
            {
                ItemProperties.register(ModItems.CLAYMORE.get(),
                        new ResourceLocation(Tenebris.MOD_ID, "transform"),
                        (stack, level, living, id) -> {
                            if (IsTransformed) return 0.0F;
                            else return 1F;
                        });
            });
        }
    }

    @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientSetup {
        @SubscribeEvent
        static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.BASE_BE.get(), BasePlushBlockRenderer::new);


        }

//        public static class ClientBusEvents {
//            @SubscribeEvent
//            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
//                event.register(KeyBinding.TRANFORM_KEY);
//            }
//        }

        @SubscribeEvent
        static void registerModels(ModelEvent.RegisterAdditional event) {
            event.register(new ResourceLocation(Tenebris.MOD_ID, "block/rei_plush"));
            event.register(new ResourceLocation(Tenebris.MOD_ID, "block/asuka_plush"));
            event.register(new ResourceLocation(Tenebris.MOD_ID, "block/shinji_plush"));
            event.register(new ResourceLocation(Tenebris.MOD_ID, "block/kallen_plush"));
            event.register(new ResourceLocation(Tenebris.MOD_ID, "item/shaft"));
            event.register(new ResourceLocation(Tenebris.MOD_ID, "item/claymore"));

        }

    }
}

