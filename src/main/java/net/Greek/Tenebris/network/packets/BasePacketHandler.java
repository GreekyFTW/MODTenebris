package net.Greek.Tenebris.network.packets;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.common.lib.Version;
import net.Greek.Tenebris.network.ITenebrisPacket;
import net.Greek.Tenebris.network.PacketMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public abstract class BasePacketHandler {

    protected BasePacketHandler(IEventBus modEventBus, Version version) {
        modEventBus.addListener(RegisterPayloadHandlersEvent.class, event -> {
            PayloadRegistrar registrar = event.registrar(version.toString());
            registerClientToServer(new PacketRegistrar(registrar, true));
            registerServerToClient(new PacketRegistrar(registrar, false));
        });
    }

    protected abstract void registerClientToServer(PacketRegistrar registrar);

    protected abstract void registerServerToClient(PacketRegistrar registrar);

    protected record SimplePacketPayLoad(CustomPacketPayload.Type<CustomPacketPayload> type) implements CustomPacketPayload {

        private SimplePacketPayLoad(ResourceLocation id) {
            this(new CustomPacketPayload.Type<>(id));
        }
    }

    protected record PacketRegistrar(PayloadRegistrar registrar, boolean toServer) {

        public <MSG extends ITenebrisPacket> void configuration(CustomPacketPayload.Type<MSG> type, StreamCodec<? super FriendlyByteBuf, MSG> reader) {
            if (toServer) {
                registrar.configurationToServer(type, reader, ITenebrisPacket::handle);
            } else {
                registrar.configurationToClient(type, reader, ITenebrisPacket::handle);
            }
        }

        public <MSG extends ITenebrisPacket> void play(CustomPacketPayload.Type<MSG> type, StreamCodec<? super RegistryFriendlyByteBuf, MSG> reader) {
            if (toServer) {
                registrar.playToServer(type, reader, ITenebrisPacket::handle);
            } else {
                registrar.playToClient(type, reader, ITenebrisPacket::handle);
            }
        }

        public SimplePacketPayLoad playInstanced(ResourceLocation id, IPayloadHandler<CustomPacketPayload> handler) {
            SimplePacketPayLoad payload = new SimplePacketPayLoad(id);
            if (toServer) {
                registrar.playToServer(payload.type(), StreamCodec.unit(payload), handler);
            } else {
                registrar.playToClient(payload.type(), StreamCodec.unit(payload), handler);
            }
            return payload;
        }
    }
}
