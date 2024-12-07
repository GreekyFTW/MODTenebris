package net.Greek.Tenebris.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.SoundOptionsScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.widget.ExtendedButton;

import java.awt.*;

import static net.Greek.Tenebris.Tenebris.rl;


public class TenebrisDisplayScreen extends Screen {

    protected static final int windowWidth = 176;
    protected static final int windowHeight = 182;
    protected int guiTop;
    protected int guiLeft;

    private static final ResourceLocation TEXTURE =
            rl("textures/gui/chromatic_compound.png");


    @Override
    protected void init(){
        super.init();
        this.guiLeft = (this.width - windowWidth) / 2;
        this.guiTop = (this.height - windowHeight) / 2;

        GridLayout gridlayout = new GridLayout();

        this.addRenderableWidget(new EditBox(font,100,100,Component.literal("EY")));



        this.addRenderableWidget(new ExtendedButton(
                this.guiLeft + windowWidth -6,
                this.guiTop + windowHeight - 110,
                18,
                18,
                Component.literal("EY"),
                pButton -> Tenebris.packetHandler().ManaAddition()

        ));

        this.addRenderableWidget(new ExtendedButton(
                this.guiLeft + windowWidth -6,
                this.guiTop + windowHeight - 70,
                18,
                18,
                Component.literal("RE"),
                pButton -> Tenebris.packetHandler().ManaReset()

        ));

        this.addRenderableWidget(new ExtendedButton(
                this.guiLeft + windowWidth - 187,
                this.guiTop + windowHeight - 110,
                19,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));

    }


    @Override
    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,TEXTURE);

        assert Minecraft.getInstance().player != null;
        pGuiGraphics.drawString(font, "youre holding it buddy"+Minecraft.getInstance().player, 80, 80, 0xFFFFFF);
        pGuiGraphics.blit(TEXTURE,this.guiLeft-40,this.guiTop-37,0,0,256,256);
    }

    @Override
    public void tick() {
        super.tick();

        

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public TenebrisDisplayScreen(Component pTitle) {
        super(pTitle);
    }
}
