package net.Greek.Tenebris.item;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.init.DataComponentRegistry;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.item.items.MultiEffectProjectileTestItem;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


import static net.minecraft.world.item.Items.registerItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tenebris.MOD_ID);

    public static final DeferredItem<Item> SHAFT = ITEMS.registerSimpleItem("shaft",
            new Item.Properties().stacksTo(16   ).durability(256));

    public static final DeferredItem<Item> STUFFING = ITEMS.registerSimpleItem("stuffing",
            new Item.Properties().stacksTo(16   ).rarity(Rarity.EPIC));

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
                                    .durability(3000)
                                    .rarity(Rarity.EPIC)
                                    .component(DataComponentRegistry.TRANSFORMED.get(),false)
                                    .component(DataComponentRegistry.ATTACK_DMG_MODIFIER.get(),90f)
                                    .component(DataComponentRegistry.ATTACK_SPEED_MODIFIER.get(),0f)

                                    , -6f ,-2.5F

                    ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
