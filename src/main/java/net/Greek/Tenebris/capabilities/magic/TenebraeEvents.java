package net.Greek.Tenebris.capabilities.magic;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.capabilities.TenebraeManager;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class TenebraeEvents {

    public static void onWorldTick(LevelTickEvent.Pre event) {
        // Don't do anything client side
        if (event.getLevel().isClientSide) {
            return;
        }

        Tenebris.TENEBRAE_MANAGER.tick(event.getLevel());
    }
}
