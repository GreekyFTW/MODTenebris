package net.Greek.Tenebris.api.magic;

import net.Greek.Tenebris.api.Spells.AbstractSpell;
import net.Greek.Tenebris.api.Spells.CastSource;
import net.minecraft.server.level.ServerPlayer;

public interface ITenebraeHelper {
    void addCooldown(ServerPlayer serverPlayer, AbstractSpell spell, CastSource castSource);
}
