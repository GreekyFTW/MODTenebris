package net.Greek.Tenebris;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.Greek.Tenebris.capabilities.magic.TenebraeEvents;

public class ModSetup {
    public static void setup() {
        IEventBus bus = NeoForge.EVENT_BUS;

        bus.addListener(TenebraeEvents::onWorldTick);
    }

    public static void init(FMLCommonSetupEvent event) {

    }
}
