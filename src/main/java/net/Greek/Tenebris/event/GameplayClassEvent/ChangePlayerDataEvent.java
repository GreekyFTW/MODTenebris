package net.Greek.Tenebris.event.GameplayClassEvent;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ChangePlayerDataEvent extends PlayerEvent implements ICancellableEvent {
    private final GameplayPlayerData playerData;
    private String[] newGameplaySpellData;
    private int newDashCount;
    private int oldDashCount;

    private int newDashCooldown;
    private int oldDashCooldown;

    private String newDashType;
    private String oldDashType;

    public ChangePlayerDataEvent(Player player, GameplayPlayerData playerData, int oldDashCount , int newDashCount){
        super(player);
        this.playerData = playerData;
        this.oldDashCount = oldDashCount;
        this.newDashCount = newDashCount;
    }

    public ChangePlayerDataEvent(Player player, GameplayPlayerData playerData, int oldDashCount , int newDashCount, int oldDashCooldown, int newDashCooldown){
        super(player);
        this.playerData = playerData;
        this.oldDashCount = oldDashCount;
        this.newDashCount = newDashCount;
        this.oldDashCooldown = oldDashCooldown;
        this.newDashCooldown = newDashCooldown;

    }

    public ChangePlayerDataEvent(Player player, GameplayPlayerData playerData, String oldDashType , String newDashType){
        super(player);
        this.playerData = playerData;
        this.oldDashType = oldDashType;
        this.newDashType = newDashType;
    }

    public GameplayPlayerData getPlayerData() {
        return playerData;
    }

    public String[] getNewGameplaySpellData() {
        return newGameplaySpellData;
    }

    public int getNewDashCount() {
        return newDashCount;
    }

    public int getNewDashCooldown() {
        return newDashCooldown;
    }

    public String getNewDashType() {
        return newDashType;
    }

    public void setNewDashCount(int newDashCount) {
        this.newDashCount = newDashCount;
    }

    public void setNewGameplayClass(String[] newGameplaySpellData) {
        this.newGameplaySpellData = newGameplaySpellData;
    }
}
