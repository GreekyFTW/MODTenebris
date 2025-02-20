//God's in his heaven. All's right with the world.

package net.Greek.Tenebris;

import com.mojang.serialization.Codec;
import net.Greek.Tenebris.api.magic.TenebraeHelper;
import net.Greek.Tenebris.block.ModBlocks;
import net.Greek.Tenebris.block.entity.ModBlockEntities;
import net.Greek.Tenebris.block.entity.ModEntities;
import net.Greek.Tenebris.capabilities.TenebraeManager;
import net.Greek.Tenebris.common.lib.Version;
import net.Greek.Tenebris.config.ClientConfigs;
import net.Greek.Tenebris.dataAttachments.DataAttachmentRegistry;
import net.Greek.Tenebris.event.DashEvent;
import net.Greek.Tenebris.event.client.DashPlayerEvent;
import net.Greek.Tenebris.event.client.PlayerEvents;
import net.Greek.Tenebris.item.ModCreativeModeTab;
import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.network.PacketHandler;
import net.Greek.Tenebris.registry.AttributeRegistry;
import net.Greek.Tenebris.registry.EntityRegistry;
import net.Greek.Tenebris.sound.ModSounds;

//import net.Greek.Tenebris.client.entityrenderers.plushies.ReiAyanamiPlushBlockRenderer;

//import net.Greek.Tenebris.util.ItemRenderer.ClaymoreItemRenderer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static net.Greek.Tenebris.event.client.handler.Attachments.ATTACHMENTS;
import static net.Greek.Tenebris.init.DataComponentRegistry.COMPONENTS;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Tenebris.MOD_ID)
public class Tenebris
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tenebris";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static TenebraeManager TENEBRAE_MANAGER;



    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Tenebris(IEventBus modEventBus, ModContainer modContainer)
    {

        instance = this;
        versionNumber = new Version(modContainer);
        packetHandler = new PacketHandler(modEventBus, versionNumber);

        ModSetup.setup();

        TENEBRAE_MANAGER = new TenebraeManager();
        TenebraeHelper.Tenebrae_MANAGER = TENEBRAE_MANAGER;

        modEventBus.addListener(ModSetup::init);


        COMPONENTS.register(modEventBus);
        ATTACHMENTS.register(modEventBus);

        ModCreativeModeTab.register(modEventBus); //creative mode tab


        ModEntities.register(modEventBus);//entities
        ModItems.register(modEventBus); //items
        ModBlocks.register(modEventBus); //blocks

        AttributeRegistry.register(modEventBus);//attributes
        DataAttachmentRegistry.register(modEventBus);//Data Attachments

        ModBlockEntities.register(modEventBus); //block entities

        //ModSounds.register(modEventBus); //sounds
        //ModSounds.SOUND_EVENTS.register(modEventBus);

        ModSounds.SOUND_EVENTS.register(modEventBus);
        //NeoForge.EVENT_BUS.register(MusicEvent.class);

        NeoForge.EVENT_BUS.register(PlayerEvents.class);
        //NeoForge.EVENT_BUS.register(DashPlayerEvent.class);

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfigs.SPEC, String.format("%s-client.toml", MOD_ID));

    }
    public static Tenebris instance;
    public final Version versionNumber;
    private final PacketHandler packetHandler;


    public static PacketHandler packetHandler() {
        return instance.packetHandler;
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
    public void onServerStarting(ServerStartingEvent event) {}

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(Tenebris.MOD_ID, path);
    }


}

