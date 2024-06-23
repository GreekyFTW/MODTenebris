package net.Greek.Tenebris.event.client;


import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.network.PacketUtils;
import net.Greek.Tenebris.network.packets.PacketClaymoreModeChange;
import net.Greek.Tenebris.util.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import java.util.Objects;



//Minecraft.getInstance().player.sendSystemMessage(Component.literal("fuck you"));
@EventBusSubscriber(modid = Tenebris.MOD_ID, value = Dist.CLIENT)
public class EventKeyInput {

    @SubscribeEvent
    public static void handleEventInput(ClientTickEvent.Post event) {


        //if (event == ClientTickEvent.Pre) {


            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null)
                return;
            //ItemStack tool = Claymore.getClaymore(mc.player);

            if (KeyBindings.TransformClaymore.consumeClick() && !Claymore.getClaymore(mc.player).isEmpty() && !mc.player.getCooldowns().isOnCooldown(Claymore.getClaymore(mc.player).getItem())) {
//                Objects.requireNonNull(Minecraft.getInstance().player).sendSystemMessage(Component.literal(" " + IsTransformed));
                if (Claymore.getClaymore(mc.player).getItem() instanceof Claymore) {

                    Tenebris.packetHandler().AskModeChange();
                    //PacketUtils.sendToServer(new PacketClaymoreModeChange());
                }
            }

        //}
    }
}

