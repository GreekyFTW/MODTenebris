package net.Greek.Tenebris.block.entity;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.entity.ability.PaintBallEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.Greek.Tenebris.Tenebris.rl;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Tenebris.MOD_ID);



    public static final Supplier<EntityType<PaintBallEntity>> PAINTBALL_ENTITY =
            ENTITIES.register("paintball_entity",
                    ()->EntityType.Builder.<PaintBallEntity>of(PaintBallEntity::new, MobCategory.MISC).sized(4f,4f).build(rl("paintball_entity").toString()));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
