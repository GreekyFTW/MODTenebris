package net.Greek.Tenebris.GUI.overlay;

import net.Greek.Tenebris.config.ClientConfigs;
import net.Greek.Tenebris.event.client.ClientGameplayClassData;
import net.Greek.Tenebris.event.client.ClientTenebraeData;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

import static net.Greek.Tenebris.Tenebris.rl;
import static net.Greek.Tenebris.registry.AttributeRegistry.MAX_MANA;

public class ManaBarOverlay implements LayeredDraw.Layer {
    public static ManaBarOverlay instance = new ManaBarOverlay();

    public enum Anchor {
        Hunger,
        XP,
        Center,
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
    }

    static final int DEFAULT_IMAGE_WIDTH = 98;
    static final int XP_IMAGE_WIDTH = 188;
    static final int IMAGE_HEIGHT = 25;
    static final int HOTBAR_HEIGHT = 25;
    static final int ICON_ROW_HEIGHT = 11;
    static final int CHAR_WIDTH = 6;
    static final int HUNGER_BAR_OFFSET = 50;
    static final int SCREEN_BORDER_MARGIN = 20;
    float ANIMATION_OFFSET=0;
    int TICK_OFFSET=0;

    static final int IMAGE_WIDTH = 54;
    static final int COMPLETION_BAR_WIDTH = 44;
     int storedMana = 0;

    private static final ResourceLocation MANA_BAR_EMPTY =
            rl("textures/gui/mana_bar/256x256_maptexture_animated.png");
    private static final ResourceLocation MANA_BAR_FULL =
            rl("textures/gui/mana_bar/mana_bar_full.png");
    Minecraft mc = Minecraft.getInstance();


    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker DeltaTracker) {
        if (Minecraft.getInstance().options.hideGui || Minecraft.getInstance().player.isSpectator()) {}

        assert mc.player != null;

        int mana = ClientTenebraeData.getPlayerTenebrae();
        int maxmana = (int) mc.player.getAttributeValue(MAX_MANA);
        int previousmana = mana;
        int BonusManaPercent = (77/maxmana) * mana;
        String manafraction = (mana) + "/" + maxmana;

        int barX , barY;
        int configOffsetY = ClientConfigs.MANA_BAR_Y_OFFSET.get();
        int configOffsetX = ClientConfigs.MANA_BAR_X_OFFSET.get();
        Anchor anchor = ClientConfigs.MANA_BAR_ANCHOR.get();

        var font = Minecraft.getInstance().font;
        var screenWidth = guiGraphics.guiWidth();
        var screenHeight = guiGraphics.guiHeight();
        barX = getBarX(anchor, screenWidth) + configOffsetX;
        barY = getBarY(anchor, screenHeight, Minecraft.getInstance().gui) - configOffsetY;

        int imageWidth = anchor == Anchor.XP ? XP_IMAGE_WIDTH : DEFAULT_IMAGE_WIDTH;
        int spriteX = anchor == Anchor.XP ? 68 : 0;
        int spriteY = anchor == Anchor.XP ? 40 : 0;

            guiGraphics.blit(MANA_BAR_EMPTY, barX, barY, spriteX, spriteY+33, imageWidth+6 , IMAGE_HEIGHT,256,7680);
            guiGraphics.blit(MANA_BAR_EMPTY, barX, barY, spriteX, spriteY + TICK_OFFSET*256,20+(int) (77 * Math.min((mana / (double) maxmana),0.985)) , IMAGE_HEIGHT,256,7680);
            guiGraphics.drawString(font,manafraction + ClientGameplayClassData.getPlayerGameplayClassData() , barX+27, barY, ChatFormatting.AQUA.getColor());
            //guiGraphics.drawString(font,ANIMATION_OFFSET+""+TICK_OFFSET+""+ DeltaTracker.getRealtimeDeltaTicks() , barX+27, barY-50, ChatFormatting.AQUA.getColor());


    }
  

    private static int getBarX(Anchor anchor, int screenWidth) {
        if (anchor == Anchor.XP)
            return screenWidth / 2 - 91 - 3; //Vanilla's Pos - 3
        if (anchor == Anchor.Hunger || anchor == Anchor.Center)
            return screenWidth / 2 - DEFAULT_IMAGE_WIDTH / 2 + (anchor == Anchor.Center ? 0 : HUNGER_BAR_OFFSET)-300;
        else if (anchor == Anchor.TopLeft || anchor == Anchor.BottomLeft)
            return SCREEN_BORDER_MARGIN;
        else return screenWidth - SCREEN_BORDER_MARGIN - DEFAULT_IMAGE_WIDTH;

    }

    private static int getBarY(Anchor anchor, int screenHeight, Gui gui) {
        if (anchor == Anchor.XP)
            return screenHeight - 32 + 3 - 8; //Vanilla's Pos - 8
        if (anchor == Anchor.Hunger)
            return screenHeight - (getAndIncrementRightHeight(gui) - 2) - IMAGE_HEIGHT / 2 + 20;
        if (anchor == Anchor.Center)
            return screenHeight - HOTBAR_HEIGHT - (int) (ICON_ROW_HEIGHT * 2.5f) - IMAGE_HEIGHT / 2;
        if (anchor == Anchor.TopLeft || anchor == Anchor.TopRight)
            return SCREEN_BORDER_MARGIN;
        return screenHeight - SCREEN_BORDER_MARGIN - IMAGE_HEIGHT;

    }

    private static int getAndIncrementRightHeight(Gui gui) {
        int x = gui.rightHeight;
        gui.rightHeight += 10;
        return x;
    }
}


