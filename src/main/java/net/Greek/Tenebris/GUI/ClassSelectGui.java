package net.Greek.Tenebris.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import net.minecraft.world.entity.player.Player;

import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.WINDOW_INSIDE_HEIGHT;
import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.WINDOW_INSIDE_WIDTH;

public class ClassSelectGui extends TenebrisDisplayScreen{

    public ClassSelectGui(Component pTitle) {
        super(pTitle);
    }



}
