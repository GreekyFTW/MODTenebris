/*
package net.Greek.Tenebris.client.handler;


import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientNeoForgeHandler {

    private static final Component TRANSFORM_KEY_PRESS =
            Component.translatable("message" + Tenebris.MOD_ID + ".transform.key.pressed");

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if(KeyBinding.TRANFORM_KEY.isDown() && minecraft.player != null){
            KeyBinding.TRANFORM_KEY.consumeClick();
            minecraft.player.displayClientMessage(TRANSFORM_KEY_PRESS, true);
        }
    }

}*/
