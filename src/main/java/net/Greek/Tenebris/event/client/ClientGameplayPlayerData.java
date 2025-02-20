package net.Greek.Tenebris.event.client;

import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;

public class ClientGameplayPlayerData {
    private static final GameplayPlayerData playerGameplayData = new GameplayPlayerData();



    public static int getDashCount(){
        return playerGameplayData.getDashCount();
    }

    public static int getDashCooldown(){
        return playerGameplayData.getDashInternalCoolDown();
    }

    public static String getDashType(){
        return playerGameplayData.getDashType();
    }

    public static void setPlayerGameplayDashData(int DashCount) {
        ClientGameplayPlayerData.playerGameplayData.setGameplayDashCount(DashCount);
    }

    public static void setPlayerGameplayDashCooldown(int DashCount,int DashCooldown) {
        ClientGameplayPlayerData.playerGameplayData.setGameplayDashCountCooldown(DashCount,DashCooldown);
    }

    public static void setPlayerGameplayDashType(String DashType) {
        ClientGameplayPlayerData.playerGameplayData.setGameplayDashType(DashType);
    }
}
