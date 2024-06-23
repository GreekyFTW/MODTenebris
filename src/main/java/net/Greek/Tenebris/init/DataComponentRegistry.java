package net.Greek.Tenebris.init;

import com.mojang.serialization.Codec;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegistry {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Tenebris.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TRANSFORM;



    static {
        TRANSFORM = COMPONENTS.registerComponentType("transform", (builder) -> builder.persistent(Codec.INT));
    }



    public static void register(IEventBus eventBus) {
        COMPONENTS.register(eventBus);
    }
}
