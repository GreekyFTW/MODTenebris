package net.Greek.Tenebris.init;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegistry {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Tenebris.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> TRANSFORMED;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> ATTACK_DMG_MODIFIER;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> ATTACK_SPEED_MODIFIER;


    static {
        TRANSFORMED = COMPONENTS.registerComponentType("transform",
                (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

        ATTACK_DMG_MODIFIER = COMPONENTS.registerComponentType("attackdamage",
                (builder) -> builder.persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));

        ATTACK_SPEED_MODIFIER = COMPONENTS.registerComponentType("attackspeed",
                (builder) -> builder.persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));
    }

    public static void register(IEventBus eventBus) {
        COMPONENTS.register(eventBus);
    }
}
