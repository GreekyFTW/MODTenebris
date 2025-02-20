package net.Greek.Tenebris.network.packets;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.event.client.ClientGameplayClassData;
import net.Greek.Tenebris.event.client.ClientTenebraeData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

public class SyncGameplayClassPacket implements CustomPacketPayload {

    private String playerGameplayClass;
    private GameplayClassData playerGameplayClassData = null;
    public static final CustomPacketPayload.Type<SyncGameplayClassPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sync_class_data"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncGameplayClassPacket> STREAM_CODEC = CustomPacketPayload.codec(SyncGameplayClassPacket::write, SyncGameplayClassPacket::new);

    public SyncGameplayClassPacket(GameplayClassData playerGameplayClassData) {
        //Server side only
        this.playerGameplayClassData = playerGameplayClassData;
    }

    public SyncGameplayClassPacket(FriendlyByteBuf buf) {
        playerGameplayClass = buf.readUtf();

    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(playerGameplayClassData.getGameplayClass());
        //buf.writeUtf(playerGameplayClass);
    }

    public static void handle(SyncGameplayClassPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientGameplayClassData.setPlayerGameplayClassData(packet.playerGameplayClass);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
