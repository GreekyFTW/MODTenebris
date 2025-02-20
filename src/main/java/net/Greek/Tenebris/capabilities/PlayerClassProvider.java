package net.Greek.Tenebris.capabilities;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import javax.annotation.Nullable;

public class PlayerClassProvider implements IAttachmentSerializer<CompoundTag, GameplayClassData> {

    @Override
    public GameplayClassData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
        //Entities implement IIAttachmentHolder
        var classData = holder instanceof ServerPlayer serverPlayer ? new GameplayClassData(serverPlayer) : new GameplayClassData(true);
        classData.loadNBTData(tag, provider);
        return classData;
    }

    @Override
    public @Nullable CompoundTag write(GameplayClassData attachment, HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        attachment.saveNBTData(tag, provider);
        return tag;
    }
}
