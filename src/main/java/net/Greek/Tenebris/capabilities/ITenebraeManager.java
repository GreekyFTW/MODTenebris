package net.Greek.Tenebris.capabilities;

import net.Greek.Tenebris.api.Spells.AbstractSpell;
import net.Greek.Tenebris.api.Spells.CastSource;
import net.minecraft.server.level.ServerPlayer;

public interface ITenebraeManager {
    void addCooldown(ServerPlayer serverPlayer, AbstractSpell spell, CastSource castSource);
}
