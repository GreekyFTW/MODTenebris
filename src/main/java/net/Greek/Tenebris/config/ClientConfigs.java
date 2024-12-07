package net.Greek.Tenebris.config;

import net.Greek.Tenebris.GUI.overlay.ManaBarOverlay;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<Integer> MANA_BAR_Y_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_BAR_X_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_TEXT_X_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_TEXT_Y_OFFSET;
    public static final ModConfigSpec.ConfigValue<ManaBarOverlay.Anchor> MANA_BAR_ANCHOR;
    public static final ModConfigSpec SPEC;

    static {
        MANA_BAR_X_OFFSET = BUILDER.define("manaBarXOffset", 0);
        MANA_BAR_Y_OFFSET = BUILDER.define("manaBarYOffset", 0);
        MANA_BAR_ANCHOR = BUILDER.defineEnum("manaBarAnchor", ManaBarOverlay.Anchor.Hunger);
        MANA_TEXT_X_OFFSET = BUILDER.define("manaTextXOffset", 0);
        MANA_TEXT_Y_OFFSET = BUILDER.define("manaTextYOffset", 0);

        SPEC = BUILDER.build();
    }
}
