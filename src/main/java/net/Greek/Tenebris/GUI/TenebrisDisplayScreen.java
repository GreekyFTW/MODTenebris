package net.Greek.Tenebris.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Greek.Tenebris.GUI.Widgets.CustomButton;
import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.event.client.ClientGameplayClassData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.SoundOptionsScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.widget.ExtendedButton;

import java.awt.*;
import java.util.Objects;

import static net.Greek.Tenebris.Tenebris.rl;


public class TenebrisDisplayScreen extends Screen {


    private static final WidgetSprites CUSTOM_SPRITES = new WidgetSprites(
            rl("widget/img_1"),
            rl("widget/chromatic_compound")
    );

    GameplayClassData ClassData;

    {
        assert Minecraft.getInstance().player != null;
        ClassData = GameplayClassData.getGameplayClassData(Minecraft.getInstance().player);
    }

    protected String ClassPreSelect="";

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

        //this.addRenderableWidget(new EditBox(font,100,100,Component.literal("EY")));



        this.addRenderableWidget(new ImageButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 190,
                190,
                19,
                CUSTOM_SPRITES,
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 170,
                190,
                19,
                Component.literal("EY"),
                pButton -> {
                    //Minecraft.getInstance().setScreen(null);
                    ClassPreSelect="Ey";
                }
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 150,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 130,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 110,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 90,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 70,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 50,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));
        this.addRenderableWidget(new CustomButton(
                this.guiLeft + windowWidth - 500,
                this.guiTop + windowHeight - 30,
                190,
                19,
                Component.literal("EY"),
                pButton -> Minecraft.getInstance().setScreen(null)
        ));


            this.addRenderableWidget(new CustomButton(
                    this.guiLeft + windowWidth - 200,
                    this.guiTop + windowHeight - 30,
                    190,
                    19,
                    Component.literal("Confirm"),
                    pButton -> {
                        if (Objects.equals(ClassPreSelect, "Ey")) {
                            Minecraft.getInstance().setScreen(null);
                        }
                    }
            ));



    }


    @Override
    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,TEXTURE);



        assert Minecraft.getInstance().player != null;
        pGuiGraphics.drawString(font, "youre holding it buddy"+Minecraft.getInstance().player + ClientGameplayClassData.getPlayerGameplayClassData()+ ClassPreSelect, 80, 80, 0xFFFFFF);

         if(Objects.equals(ClassPreSelect, "Ey")) {
             pGuiGraphics.blit(TEXTURE, this.guiLeft - 40, this.guiTop - 37, 0, 0, 256, 256);
         }
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
