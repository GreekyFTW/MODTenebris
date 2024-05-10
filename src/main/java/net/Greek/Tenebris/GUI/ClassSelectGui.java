package net.Greek.Tenebris.GUI;

import net.Greek.Tenebris.Tenebris;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClassSelectGui extends Screen {
    private static final Component TITLE = Component.translatable("gui."+ Tenebris.MOD_ID+ "Class Select Gui");


    private final int imageWidth, imageHeight;
    private int leftPos, topPos;


    protected ClassSelectGui(int imageWidth, int imageHeight) {
        super(TITLE);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }
}
