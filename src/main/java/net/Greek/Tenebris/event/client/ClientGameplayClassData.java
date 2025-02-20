package net.Greek.Tenebris.event.client;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.api.TenebraeData;

public class ClientGameplayClassData {
    private static final GameplayClassData playerGameplayClassData = new GameplayClassData();

    public static String getPlayerGameplayClassData() {
        return playerGameplayClassData.getGameplayClass();
    }

    public static void setPlayerGameplayClassData(String gameplayClassData) {
        ClientGameplayClassData.playerGameplayClassData.setGameplayClass(gameplayClassData);
    }
}
