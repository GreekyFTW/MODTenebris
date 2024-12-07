package net.Greek.Tenebris.network;

import net.Greek.Tenebris.network.packets.SyncTenebraePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class PayloadHandler {

    @SubscribeEvent
    public static void register(final   RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(MOD_ID).versioned("1.0.0").optional();


        payloadRegistrar.playToClient(SyncTenebraePacket.TYPE, SyncTenebraePacket.STREAM_CODEC, SyncTenebraePacket::handle);
    }
}
