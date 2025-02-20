package net.Greek.Tenebris.event.TenebraeEvent;

import net.Greek.Tenebris.api.TenebraeData;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ChangeTenebraeEvent extends PlayerEvent implements ICancellableEvent {
    private final TenebraeData tenebraeData;
    private final float oldTenebrae;
    private float newTenebrae;

    public ChangeTenebraeEvent(Player player, TenebraeData tenebraeData, float oldTenebrae, float newTenebrae) {
        super(player);
        this.tenebraeData = tenebraeData;
        this.oldTenebrae = oldTenebrae;
        this.newTenebrae = newTenebrae;
    }

    public TenebraeData getTenebraeData() {
        return tenebraeData;
    }

    public float getOldTenebrae() {
        return oldTenebrae;
    }

    public float getNewTenebrae() {
        return newTenebrae;
    }

    public void setNewTenebrae(float newTenebrae) {
        this.newTenebrae = newTenebrae;
    }
}
