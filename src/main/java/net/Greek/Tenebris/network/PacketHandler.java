package net.Greek.Tenebris.network;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.common.lib.Version;
import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.network.packets.BasePacketHandler;
import net.Greek.Tenebris.network.packets.PacketClaymoreModeChange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;

import static net.Greek.Tenebris.Tenebris.rl;

//import static net.Greek.Tenebris.event.client.EventKeyInput.IsTransformed;

public class PacketHandler extends BasePacketHandler {

    public static boolean IsTransformed = false;




    //client to server
    private SimplePacketPayLoad AskModeChange;
    //server to client
    private SimplePacketPayLoad AnswerModeChange;

    public PacketHandler(IEventBus modEventBus, Version version) {
        super(modEventBus, version);
        modEventBus.addListener(RegisterConfigurationTasksEvent.class, event -> {
            ServerConfigurationPacketListener listener = event.getListener();
        });
    }

    public void AskModeChange() {
        PacketUtils.sendToServer(AskModeChange);
    }

    public void AnswerModeChange(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, AnswerModeChange);
    }

    @Override
    protected void registerClientToServer(PacketRegistrar registrar) {
        AskModeChange = registrar.playInstanced(rl("ask_mode_change"), (ignored, context) -> {
            if (context.player() instanceof ServerPlayer player) {

                Objects.requireNonNull(player).sendSystemMessage(Component.literal(" " + IsTransformed));

                IsTransformed = !IsTransformed;

                player.getCooldowns().addCooldown(Claymore.getClaymore(player).getItem(), 20);

            }
        });

        registrar.play(PacketClaymoreModeChange.TYPE, PacketClaymoreModeChange.STREAM_CODEC);
    }

    @Override
    protected void registerServerToClient(PacketRegistrar registrar) {

    }
}
