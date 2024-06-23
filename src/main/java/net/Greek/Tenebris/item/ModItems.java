package net.Greek.Tenebris.item;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.init.DataComponentRegistry;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.item.items.MultiEffectProjectileTestItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;


import static net.minecraft.world.item.Items.registerItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Tenebris.MOD_ID);

    public static final DeferredItem<Item> SHAFT = ITEMS.registerSimpleItem("shaft",
            new Item.Properties().stacksTo(16   ).durability(256));

    public static final DeferredItem<Item> CHROMATIC_COMPOUND = ITEMS.registerSimpleItem("chromatic_compound",
            new Item.Properties().stacksTo(64));

    public static final DeferredItem<Item> MEPI =
            ITEMS.register("mepi",
                ()-> new MultiEffectProjectileTestItem(
                        new Item.Properties().stacksTo(64)));




    public static final DeferredItem<Item> CLAYMORE =
            ITEMS.register("claymore",
                    ()-> new Claymore(Tiers.NETHERITE,
                            new Item.Properties()
                                    .fireResistant()
                                    .durability(999999999)
                                    .component(DataComponentRegistry.TRANSFORM.get(),1)
                    ));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
