package net.Greek.Tenebris.network.packets;

import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.event.client.ClientTenebraeData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

public class SyncTenebraePacket implements CustomPacketPayload {

    private int playerTenebrae = 0;
    private TenebraeData playerTenebraeData = null;
    public static final CustomPacketPayload.Type<SyncTenebraePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sync_mana"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncTenebraePacket> STREAM_CODEC = CustomPacketPayload.codec(SyncTenebraePacket::write, SyncTenebraePacket::new);

    public SyncTenebraePacket(TenebraeData playerTenebraeData) {
        //Server side only
        this.playerTenebraeData = playerTenebraeData;
    }

    public SyncTenebraePacket(FriendlyByteBuf buf) {
        playerTenebrae = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt((int) playerTenebraeData.getTenebrae());
    }

    public static void handle(SyncTenebraePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientTenebraeData.setTenebrae(packet.playerTenebrae);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
