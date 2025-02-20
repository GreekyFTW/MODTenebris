package net.Greek.Tenebris.event.server;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.network.packets.SyncGameplayClassPacket;
import net.Greek.Tenebris.network.packets.SyncGameplayPlayerDataPacket;
import net.Greek.Tenebris.network.packets.SyncTenebraePacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;



@EventBusSubscriber
public class ServerPlayerEvents {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            var playerTenebraeData = TenebraeData.getPlayerTenebraeData(serverPlayer);
            var playerGameplayClassData = GameplayClassData.getGameplayClassData(serverPlayer);
            var playerGameplayData = GameplayPlayerData.getGameplayPlayerData(serverPlayer);

            PacketDistributor.sendToPlayer(serverPlayer, new SyncTenebraePacket(playerTenebraeData));
            PacketDistributor.sendToPlayer(serverPlayer, new SyncGameplayClassPacket(playerGameplayClassData));
            PacketDistributor.sendToPlayer(serverPlayer, new SyncGameplayPlayerDataPacket(playerGameplayData));

        }
    }

}
