package net.Greek.Tenebris.GUI.overlay;

import net.Greek.Tenebris.event.client.ClientGameplayClassData;
import net.Greek.Tenebris.event.client.ClientTenebraeData;
import net.Greek.Tenebris.event.client.ClientGameplayPlayerData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;

public class DashOverlay implements LayeredDraw.Layer {
    public static DashOverlay instance = new DashOverlay();

    static final int IMAGE_WIDTH = 54;
    static final int COMPLETION_BAR_WIDTH = 44;
    static final int IMAGE_HEIGHT = 21;
    public int storedMana = 0;

    Minecraft mc = Minecraft.getInstance();

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker pDeltaTracker) {
        if (Minecraft.getInstance().options.hideGui || Minecraft.getInstance().player.isSpectator()) {}

        assert mc.player != null;

        int dashCount = ClientGameplayPlayerData.getDashCount();
        int dashCooldown  = ClientGameplayPlayerData.getDashCooldown();

        var screenWidth = guiGraphics.guiWidth();
        var screenHeight = guiGraphics.guiHeight();

        int barX, barY;
        barX = screenWidth / 2 - IMAGE_WIDTH / 2;
        barY = screenHeight / 2 + screenHeight / 8;

        int textX, textY;
        var font = Minecraft.getInstance().font;

        textX = barX + (IMAGE_WIDTH - font.width("2")) / 2;
        textY = barY + IMAGE_HEIGHT / 2 - font.lineHeight / 2 + 1;

        guiGraphics.drawString(font, dashCooldown+ "d" + dashCount + ClientTenebraeData.getPlayerTenebrae() + ClientGameplayClassData.getPlayerGameplayClassData() + ClientGameplayPlayerData.getDashType(), barX+27, barY-90, ChatFormatting.AQUA.getColor());


    }


}
