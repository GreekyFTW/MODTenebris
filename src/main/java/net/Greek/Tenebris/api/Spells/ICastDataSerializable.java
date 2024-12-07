package net.Greek.Tenebris.api.Spells;

import net.Greek.Tenebris.network.ISeriazable;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface ICastDataSerializable extends ICastData, ISeriazable, INBTSerializable<CompoundTag> {
}
