package net.Greek.Tenebris.GUI.overlay;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.event.client.ClientTenebraeData;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.util.Utils;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import java.util.Objects;

import static net.Greek.Tenebris.Tenebris.rl;


public class SpellSlotOverlay implements  LayeredDraw.Layer{
    public static SpellSlotOverlay instance = new SpellSlotOverlay();

    static final int IMAGE_WIDTH = 54;
    static final int COMPLETION_BAR_WIDTH = 44;
    static final int IMAGE_HEIGHT = 21;
    public int storedMana = 0;

    private static final ResourceLocation TEXTURE_1 =
            rl("textures/gui/chromatic_compound.png");
    private static final ResourceLocation TEXTURE_2 =
            rl("textures/gui/dust_lead.png");
    Minecraft mc = Minecraft.getInstance();

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker pDeltaTracker) {
        if (Minecraft.getInstance().options.hideGui || Minecraft.getInstance().player.isSpectator()) {}

        assert mc.player != null;

        var screenWidth = guiGraphics.guiWidth();
        var screenHeight = guiGraphics.guiHeight();

        int barX, barY;
        barX = screenWidth / 2 - IMAGE_WIDTH / 2;
        barY = screenHeight / 2 + screenHeight / 8;

        int textX, textY;
        var font = Minecraft.getInstance().font;

        textX = barX + (IMAGE_WIDTH - font.width("2")) / 2;
        textY = barY + IMAGE_HEIGHT / 2 - font.lineHeight / 2 + 1;



    }



}
