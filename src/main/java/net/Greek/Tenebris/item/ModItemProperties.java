package net.Greek.Tenebris.item;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.init.DataComponentRegistry;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import static net.Greek.Tenebris.Tenebris.rl;
import static net.Greek.Tenebris.network.PacketHandler.IsTransformed;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.CLAYMORE.get(),rl("claymore_mode"),
                (pStack, pLevel, pEntity, pSeed) ->

                    pStack.get(DataComponentRegistry.TRANSFORMED).booleanValue()? 0F : 1F

        );
    }
}
