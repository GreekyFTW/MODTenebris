package net.Greek.Tenebris.api.Spells;

public enum CastSource {
    SPELLBOOK,
    SCROLL,
    SWORD,
    MOB,
    COMMAND,
    NONE;

    public boolean consumesMana() {
        return this == SPELLBOOK || (this == SWORD);
    }

    public boolean respectsCooldown() {
        return this == SPELLBOOK || this == SWORD;
    }

}
