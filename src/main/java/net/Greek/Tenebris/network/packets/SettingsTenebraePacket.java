package net.Greek.Tenebris.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.Greek.Tenebris.util.Utils;
import java.util.Iterator;

public class SettingsTenebraePacket implements CustomPacketPayload {

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return null;
    }
}
