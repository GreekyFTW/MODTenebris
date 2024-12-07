package net.Greek.Tenebris.capabilities;

import net.Greek.Tenebris.api.TenebraeData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import javax.annotation.Nullable;

public class PlayerTenebraeProvider implements IAttachmentSerializer<CompoundTag,TenebraeData> {

    @Override
    public TenebraeData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
        //Entities implement IIAttachmentHolder
        var magicData = holder instanceof ServerPlayer serverPlayer ? new TenebraeData(serverPlayer) : new TenebraeData(true);
        magicData.loadNBTData(tag, provider);
        return magicData;
    }

    @Override
    public @Nullable CompoundTag write(TenebraeData attachment, HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        attachment.saveNBTData(tag, provider);
        return tag;
    }
}

