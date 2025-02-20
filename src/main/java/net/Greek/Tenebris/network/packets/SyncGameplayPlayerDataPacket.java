package net.Greek.Tenebris.network.packets;

import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.Greek.Tenebris.event.client.ClientGameplayPlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

public class SyncGameplayPlayerDataPacket implements CustomPacketPayload {
    private String[] playerGameplaySpell;
    private int playerDashCount = 0;
    private int playerDashCooldown = 1;
    private String DashType= "dash";
    private GameplayPlayerData playerGameplayPlayerData = null;
    public static final CustomPacketPayload.Type<SyncGameplayPlayerDataPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sync_player_data"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncGameplayPlayerDataPacket> STREAM_CODEC = CustomPacketPayload.codec(SyncGameplayPlayerDataPacket::write, SyncGameplayPlayerDataPacket::new);

    public SyncGameplayPlayerDataPacket(GameplayPlayerData playerGameplayPlayerData) {
        //Server side only
        this.playerGameplayPlayerData = playerGameplayPlayerData;
    }

    public SyncGameplayPlayerDataPacket(FriendlyByteBuf buf) {
        //playerGameplaySpell = new String[]{buf.readUtf()};
        playerDashCount = buf.readInt();
        playerDashCooldown = buf.readInt();
        DashType = buf.readUtf();
    }


    public void write(FriendlyByteBuf buf) {
        //buf.writeUtf(Arrays.toString(playerGameplayPlayerData.getGameplayPlayerData()));
        buf.writeInt(playerGameplayPlayerData.getDashCount());
        buf.writeInt(playerGameplayPlayerData.getDashInternalCoolDown());
        buf.writeUtf(playerGameplayPlayerData.getDashType());
    }

    public static void handle(SyncGameplayPlayerDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            //ClientGameplayPlayerData.setPlayerGameplaySpellData(packet.playerGameplaySpell,packet.playerDashCount);
            ClientGameplayPlayerData.setPlayerGameplayDashCooldown(packet.playerDashCount,packet.playerDashCooldown);
            ClientGameplayPlayerData.setPlayerGameplayDashData(packet.playerDashCount);
            ClientGameplayPlayerData.setPlayerGameplayDashType(packet.DashType);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
