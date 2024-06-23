package net.Greek.Tenebris.item;

import net.Greek.Tenebris.Tenebris;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;

import static net.Greek.Tenebris.Tenebris.rl;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.CLAYMORE.value(),rl("claymore_mode"),
                (pStack, pLevel, pEntity, pSeed) -> pStack.has(DataComponents.CUSTOM_DATA) ? 0f : 1f);
    }
}
