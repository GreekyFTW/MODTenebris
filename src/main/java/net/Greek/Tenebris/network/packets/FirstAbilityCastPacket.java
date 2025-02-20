package net.Greek.Tenebris.network.packets;

import net.Greek.Tenebris.GameplayClasses.Abilities.Malerin.Misc.PaintBall;
import net.Greek.Tenebris.GameplayClasses.Abilities.Malerin.PaintBallAbility;
import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.event.client.ClientGameplayClassData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static net.Greek.Tenebris.Tenebris.rl;

public class FirstAbilityCastPacket implements CustomPacketPayload {
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return null;
    }
//    public static final CustomPacketPayload.Type<FirstAbilityCastPacket> TYPE = new CustomPacketPayload.Type<>(rl("cast"));
//    public static final StreamCodec<RegistryFriendlyByteBuf, FirstAbilityCastPacket> STREAM_CODEC = CustomPacketPayload.codec(FirstAbilityCastPacket::write, FirstAbilityCastPacket::new);
//
//    public FirstAbilityCastPacket() {
//    }
//
//    public FirstAbilityCastPacket(FriendlyByteBuf buf) {
//    }
//
//    public void write(FriendlyByteBuf buf) {
//    }
//
//    public static void handle(FirstAbilityCastPacket packet, IPayloadContext context) {
//        context.enqueueWork(() -> {
//            if (context.player() instanceof ServerPlayer serverPlayer) {
//                switch(GameplayClassData.getGameplayClassData(serverPlayer).getGameplayClass()){
//                    case "Malerin":
//
//                        break;
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public Type<? extends CustomPacketPayload> type() {
//        return TYPE;
//    }
}
