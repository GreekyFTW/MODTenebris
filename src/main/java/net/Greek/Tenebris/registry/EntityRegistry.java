package net.Greek.Tenebris.registry;

import net.Greek.Tenebris.GameplayClasses.Abilities.Malerin.Misc.PaintBall;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityRegistry {

    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Tenebris.MOD_ID);

//    public static final DeferredHolder<EntityType<?>, EntityType<PaintBall>> PAINT_BALL =
//            ENTITIES.register("paint_ball", () -> EntityType.Builder.<PaintBall>of(PaintBall::new, MobCategory.MISC)
//                    .sized(1.1f, 1.1f)
//                    .clientTrackingRange(64)
//                    .build("paint_ball"));



    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
