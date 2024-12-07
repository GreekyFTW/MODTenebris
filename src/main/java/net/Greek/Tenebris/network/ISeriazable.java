package net.Greek.Tenebris.network;

import net.minecraft.network.FriendlyByteBuf;

public interface ISeriazable {
    void writeToBuffer(FriendlyByteBuf buffer);

    void readFromBuffer(FriendlyByteBuf buffer);
}
