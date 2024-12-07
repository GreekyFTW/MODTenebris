package net.Greek.Tenebris.mixin;

import net.Greek.Tenebris.GUI.overlay.ManaBarOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "renderExperienceBar", at = @At(value = "HEAD"), cancellable = true)
    public void renderExperienceBar(GuiGraphics guiGraphics, int pXPos, CallbackInfo ci) {
        if (Minecraft.getInstance().player != null)

            ci.cancel();
    }
}
