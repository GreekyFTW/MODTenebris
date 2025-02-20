package net.Greek.Tenebris.capabilities;

import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import javax.annotation.Nullable;

public class PlayerDataProvider implements IAttachmentSerializer<CompoundTag, GameplayPlayerData> {
    @Override
    public GameplayPlayerData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
        //Entities implement IIAttachmentHolder
        var playerData = holder instanceof ServerPlayer serverPlayer ? new GameplayPlayerData(serverPlayer) : new GameplayPlayerData(true);
        playerData.loadNBTData(tag, provider);
        return playerData;
    }

    @Override
    public @Nullable CompoundTag write(GameplayPlayerData attachment, HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        attachment.saveNBTData(tag, provider);
        return tag;
    }
}
