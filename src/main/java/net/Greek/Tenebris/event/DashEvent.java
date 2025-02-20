package net.Greek.Tenebris.event;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.util.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;

public class DashEvent {
//
//    byte cooldown = 0;
//
//    @SubscribeEvent
//    public void onKeyPressed(ClientTickEvent.Pre event)
//    {
//        Minecraft mc = Minecraft.getInstance();
//
//        if (mc.player == null)
//            return;
//
//        Player player = mc.player;
//
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal("done"+ cooldown));
//
//        if (KeyBindings.DASH.isDown() && cooldown <= 0 && !player.isInWaterOrBubble() && player.getFallFlyingTicks() <= 10)
//        {
//            //PacketHandler.sendToServer(new DashVisualsPacket());
//
//            Vec3 playerLook = player.getViewVector(1);
//            Vec3 dashVec = new Vec3(playerLook.x(), player.getDeltaMovement().y(), playerLook.z());
//            player.setDeltaMovement(dashVec);
//
//            Objects.requireNonNull(player).sendSystemMessage(Component.literal("done"+ cooldown));
//
//            cooldown = 50;
//        }
//    }
//
//    @SubscribeEvent
//    public void onTick(PlayerTickEvent.Pre event)
//    {
//
//        Player player =event.getEntity();
//        boolean doAllowDash = player.level().getGameTime() % 2 == 0;
//
//            if (cooldown > 0 )
//                --cooldown;
//
//    }


}
