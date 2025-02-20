package net.Greek.Tenebris.event.GameplayClassEvent;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.api.TenebraeData;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ChangeClassEvent extends PlayerEvent implements ICancellableEvent {
    private final GameplayClassData ClassData;
    private final String oldGameplayClass;
    private String newGameplayClass;

    public ChangeClassEvent(Player player, GameplayClassData ClassData,String oldGameplayClass, String newGameplayClass) {
        super(player);
        this.ClassData = ClassData;
        this.oldGameplayClass = oldGameplayClass;
        this.newGameplayClass = newGameplayClass;
    }

    public GameplayClassData getClassData() {
        return ClassData;
    }

    public String getOldGameplayClass() {
        return oldGameplayClass;
    }

    public String getNewGameplayClass() {
        return newGameplayClass;
    }

    public void setNewGameplayClass(String newGameplayClass) {
        this.newGameplayClass = newGameplayClass;
    }
}
