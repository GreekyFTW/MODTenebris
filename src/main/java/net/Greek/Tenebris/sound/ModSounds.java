package net.Greek.Tenebris.sound;

import net.Greek.Tenebris.Tenebris;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public final class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Tenebris.MOD_ID);


//    public static final Supplier<SoundEvent> SQUEEK =
//            SOUND_EVENTS.register("squeek_1",
//                    () -> SoundEvent.createVariableRangeEvent(new
//                            ResourceLocation(
//                            Tenebris.MOD_ID,
//                            "squeek_1")));

    public static Supplier<SoundEvent>
        registerVariableRangeSound(String name){
        return  SOUND_EVENTS.register(
                name,
                ()-> SoundEvent.createVariableRangeEvent(new
                        ResourceLocation(Tenebris.MOD_ID, name))
        );
    }

    public static final Supplier<SoundEvent> SQUEEK =
            registerVariableRangeSound("squeek");


//    public  static  void register(IEventBus modBus){
//        SOUND_EVENTS.register(modBus);
//    }
}
