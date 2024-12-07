package net.Greek.Tenebris.event.TenebraeEvent;

import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.event.client.PlayerEvents;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ChangeTenebraeEvent extends PlayerEvent implements ICancellableEvent {
    private final TenebraeData magicData;
    private final float oldTenebrae;
    private float newTenebrae;

    public ChangeTenebraeEvent(Player player, TenebraeData magicData, float oldTenebrae, float newTenebrae) {
        super(player);
        this.magicData = magicData;
        this.oldTenebrae = oldTenebrae;
        this.newTenebrae = newTenebrae;
    }

    public TenebraeData getTenebraeData() {
        return magicData;
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
