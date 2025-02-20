package net.Greek.Tenebris.capabilities;

import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.Greek.Tenebris.network.packets.SyncGameplayPlayerDataPacket;
import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.api.Spells.AbstractSpell;
import net.Greek.Tenebris.api.Spells.CastSource;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.network.packets.SyncTenebraePacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;

import static net.Greek.Tenebris.registry.AttributeRegistry.*;

public class TenebraeManager implements ITenebraeManager{
    public static final int MANA_REGEN_TICKS = 10;
    public static final int CONTINUOUS_CAST_TICK_INTERVAL = 10;
    public static int Cooldown=20;

    public boolean regenPlayerTenebrae(ServerPlayer serverPlayer, TenebraeData playerTenebraeData) {
        int playerMaxTenebrae = (int) serverPlayer.getAttributeValue(MAX_MANA);
        var mana = playerTenebraeData.getTenebrae();
        if (mana != playerMaxTenebrae) {
            float playerTenebraeRegenMultiplier = (float) serverPlayer.getAttributeValue(MANA_REGEN);
            var increment = playerMaxTenebrae * playerTenebraeRegenMultiplier * .01f;
            playerTenebraeData.setTenebrae(Mth.clamp(playerTenebraeData.getTenebrae() + increment, 0, playerMaxTenebrae));
            return true;
        } else {
            return false;
        }
    }

    public boolean regenPlayerDash(ServerPlayer serverPlayer, GameplayPlayerData playerData) {
        int playerMaxDash = (int) serverPlayer.getAttributeValue(MAX_DASH_COUNT);
        var dashCount = playerData.getDashCount();
        var dashCooldown = playerData.getDashInternalCoolDown();

        if (dashCount < playerMaxDash && dashCooldown==0) {
            //float playerTenebraeRegenMultiplier = (float) serverPlayer.getAttributeValue(MANA_REGEN);

            //playerData.setGameplayDashCount(playerData.getDashCount()+1);
            // playerData.setGameplayDashCooldown(playerData.getDashCount()+1,10);
            playerData.setGameplayDashCountCooldown(playerData.getDashCount()+1,60);
            return true;
        } else {
            return false;
        }
    }


    public boolean doPlayerInternalDashCooldown(ServerPlayer serverPlayer, GameplayPlayerData playerData) {
        var dashCooldown = playerData.getDashInternalCoolDown();
        if (dashCooldown>0) {
            //float playerTenebraeRegenMultiplier = (float) serverPlayer.getAttributeValue(MANA_REGEN);

           playerData.setGameplayDashCountCooldown(playerData.getDashCount(),playerData.getDashInternalCoolDown()-1);


            return true;
        } else {
            return false;
        }
    }

    public void tick(Level level) {
        boolean doTenebraeRegen = level.getServer().getTickCount() % MANA_REGEN_TICKS == 0;
//        boolean doDashRegen = level.getServer().getTickCount() % 50 == 0;
//        boolean doDashCooldown = level.getServer().getTickCount() % 5 == 0;


        level.players().stream().toList().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                TenebraeData playerTenebraeData = TenebraeData.getPlayerTenebraeData(serverPlayer);
                GameplayClassData gameplayClassData = GameplayClassData.getGameplayClassData(serverPlayer);
                GameplayPlayerData gameplayPlayerData = GameplayPlayerData.getGameplayPlayerData(serverPlayer);

                int playerMaxDash = (int) serverPlayer.getAttributeValue(MAX_DASH_COUNT);
                var dashCount = gameplayPlayerData.getDashCount();
                var dashCooldown = gameplayPlayerData.getDashInternalCoolDown();


                //Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+dashCooldown));
                //Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+serverPlayer.getAttributeValue(MAX_MANA)));
                //Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal(""+playerTenebraeData.getTenebrae()));
                //Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal("" ));


//                if(doDashCooldown){
//                    if(doPlayerInternalDashCooldown(serverPlayer,gameplayPlayerData)){
//                        PacketDistributor.sendToPlayer(serverPlayer, new SyncGameplayPlayerDataPacket(gameplayPlayerData));
//                        Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal("Dash cd:"+ gameplayPlayerData.getDashInternalCoolDown()));
//                    }
//                }

                //if(doDashRegen){
                    if(regenPlayerDash(serverPlayer, gameplayPlayerData)){
                        PacketDistributor.sendToPlayer(serverPlayer, new SyncGameplayPlayerDataPacket(gameplayPlayerData));
                        Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal(""+ gameplayPlayerData.getDashCount()));
                    }
                //}

                if (doTenebraeRegen) {
                    if (regenPlayerTenebrae(serverPlayer, playerTenebraeData)) {
                        PacketDistributor.sendToPlayer(serverPlayer, new SyncTenebraePacket(playerTenebraeData));
                        Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal(""+player));
                    }
                }

                if(dashCount<playerMaxDash && dashCooldown>0){
                   gameplayPlayerData.setGameplayDashCountCooldown(gameplayPlayerData.getDashCount(), gameplayPlayerData.getDashInternalCoolDown()-1);
                }

            }
        });
    }

    @Override
    public void addCooldown(ServerPlayer serverPlayer, AbstractSpell spell, CastSource castSource) {

    }
}
