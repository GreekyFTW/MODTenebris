package net.Greek.Tenebris.network.packets;

import io.netty.buffer.ByteBuf;
import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.network.ITenebrisPacket;
import net.Greek.Tenebris.network.PacketUtils;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record PacketClaymoreModeChange(EquipmentSlot slot, int shift, boolean displayChangeMessage) implements ITenebrisPacket {

    public static final CustomPacketPayload.Type<PacketClaymoreModeChange> TYPE = new CustomPacketPayload.Type<>(Tenebris.rl("mode"));
    public static final StreamCodec<ByteBuf,  PacketClaymoreModeChange> STREAM_CODEC = StreamCodec.composite(
            PacketUtils.EQUIPMENT_SLOT_STREAM_CODEC, PacketClaymoreModeChange::slot,
            ByteBufCodecs.VAR_INT, PacketClaymoreModeChange::shift,
            ByteBufCodecs.BOOL, PacketClaymoreModeChange::displayChangeMessage,
            PacketClaymoreModeChange::new
    );

    public PacketClaymoreModeChange(EquipmentSlot slot, boolean holdingShift) {
        this(slot, holdingShift ? -1 : 1, true);
    }

    public PacketClaymoreModeChange(EquipmentSlot slot, int shift) {
        this(slot, shift, false);
    }

    @NotNull
    @Override
    public CustomPacketPayload.Type<PacketClaymoreModeChange> type() {
        return TYPE;
    }

    @Override
    public void handle(IPayloadContext context) {
        Player player = context.player();
        //ItemStack stack = player.getItemBySlot(slot);

    }


}

