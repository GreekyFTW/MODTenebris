package net.Greek.Tenebris.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public interface PacketMessage extends CustomPacketPayload {

    void handle(IPayloadContext context);
}
