package net.Greek.Tenebris.event.client;


import net.Greek.Tenebris.GUI.ClassSelectGui;
import net.Greek.Tenebris.GUI.TenebrisDisplayScreen;
import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.network.PacketUtils;
import net.Greek.Tenebris.network.packets.PacketClaymoreModeChange;
import net.Greek.Tenebris.sound.ModSounds;
import net.Greek.Tenebris.util.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Tiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.checkerframework.checker.units.qual.K;

import java.util.Objects;

import static net.Greek.Tenebris.init.DataComponentRegistry.TRANSFORMED;


//Minecraft.getInstance().player.sendSystemMessage(Component.literal("fuck you"));
@EventBusSubscriber(modid = Tenebris.MOD_ID, value = Dist.CLIENT)
public class EventKeyInput {

    private static boolean CastSpellKeyWasDown = false;


    @SubscribeEvent
    public static void handleEventInput(ClientTickEvent.Pre event) {

            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null)
                return;

            if (KeyBindings.TransformClaymore.consumeClick() && !Claymore.getClaymore(mc.player).isEmpty() && !mc.player.getCooldowns().isOnCooldown(Claymore.getClaymore(mc.player).getItem())) {
                if (Claymore.getClaymore(mc.player).getItem() instanceof Claymore) {
                    Tenebris.packetHandler().AskModeChange();
                }
            }

        boolean CastSpellKeyIsDown = KeyBindings.CastSpell.isDown();


            if (CastSpellKeyIsDown && !CastSpellKeyWasDown ){
                if(mc.screen == null){
                    Minecraft.getInstance().setScreen(new ClassSelectGui(Component.empty()));
                }

            }
    }
}

