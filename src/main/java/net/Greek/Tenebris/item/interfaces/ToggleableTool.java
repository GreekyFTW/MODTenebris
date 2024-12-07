package net.Greek.Tenebris.item.interfaces;

import net.Greek.Tenebris.init.DataComponentRegistry;
import net.minecraft.world.item.ItemStack;

public interface ToggleableTool {

    default boolean getTransformationBooleanValue(ItemStack pStack){
        return Boolean.TRUE.equals(pStack.get(DataComponentRegistry.TRANSFORMED.get()));
    }


}
