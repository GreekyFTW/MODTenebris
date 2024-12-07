package net.Greek.Tenebris.registry;

import net.Greek.Tenebris.api.attribute.TenebraePercentAttribute;
import net.Greek.Tenebris.api.attribute.TenebraeRangedAttribute;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

@EventBusSubscriber(modid = MOD_ID  , bus = EventBusSubscriber.Bus.MOD)
public class AttributeRegistry {

    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MOD_ID);

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    public static final DeferredHolder<Attribute, Attribute> MAX_MANA = ATTRIBUTES.register("maximum_mana", () -> (new TenebraeRangedAttribute("attribute.tenebris.max_mana", 100.0D, 0.0D, 1000000.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> MANA_REGEN = ATTRIBUTES.register("mana_regen", () -> (new TenebraePercentAttribute("attribute.tenebris.mana_regen", 1.0D, 0.0D, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> COOLDOWN_REDUCTION = ATTRIBUTES.register("cooldown_reduction", () -> (new TenebraePercentAttribute("attribute.tenebris.cooldown_reduction", 1.0D, -100.0D, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> SPELL_POWER = ATTRIBUTES.register("spell_power", () -> (new TenebraePercentAttribute("attribute.tenebris.spell_power", 1.0D, -100, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> SPELL_RESIST = ATTRIBUTES.register("spell_resist", () -> (new TenebraePercentAttribute("attribute.tenebris.spell_resist", 1.0D, -100, 100.0D).setSyncable(true)));
    public static final DeferredHolder<Attribute, Attribute> CAST_TIME_REDUCTION = ATTRIBUTES.register("cast_time_reduction", () -> (new TenebraePercentAttribute("attribute.tenebris.cast_time_reduction", 1.0D, -100, 100.0D).setSyncable(true)));

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent e) {
        e.getTypes().forEach(entity -> ATTRIBUTES.getEntries().forEach(attribute -> e.add(entity, attribute)));
    }

    private static DeferredHolder<Attribute, Attribute> newResistanceAttribute(String id) {
        return (DeferredHolder<Attribute, Attribute>) ATTRIBUTES.register(id + "_Tenebrae_resist", () -> (new TenebraePercentAttribute("attribute.tenebris." + id + "_Tenebrae_resist", 1.0D, -100, 100).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> newPowerAttribute(String id) {
        return ATTRIBUTES.register(id + "_spell_power", () -> (new TenebraePercentAttribute("attribute.tenebris." + id + "_spell_power", 1.0D, -100, 100).setSyncable(true)));
    }

}
