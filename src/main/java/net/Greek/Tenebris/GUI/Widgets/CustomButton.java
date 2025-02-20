package net.Greek.Tenebris.GUI.Widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.widget.ExtendedButton;

import static net.Greek.Tenebris.Tenebris.rl;

public class CustomButton extends ExtendedButton {

    protected static final WidgetSprites CUSTOM_SPRITES = new WidgetSprites(
            rl("widget/img.png"),
            rl("widget/img.png"),
            rl("widget/img.png")
    );

    public CustomButton(Builder builder) {
        super(builder);
    }

    public CustomButton(int xPos, int yPos, int width, int height, Component displayString, OnPress handler, CreateNarration createNarration) {
        super(xPos, yPos, width, height, displayString, handler, createNarration);
    }

    public CustomButton(int xPos, int yPos, int width, int height, Component displayString, OnPress handler) {
        super(xPos, yPos, width, height, displayString, handler);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphics.blitSprite(CUSTOM_SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        final FormattedText buttonText = mc.font.ellipsize(this.getMessage(), this.width - 6); // Remove 6 pixels so that the text is always contained within the button's borders
        guiGraphics.drawCenteredString(mc.font, Language.getInstance().getVisualOrder(buttonText), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, getFGColor());
    }



}
