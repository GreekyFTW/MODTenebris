package net.Greek.Tenebris.client.event;


import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.item.Tools.Claymore;
import net.Greek.Tenebris.util.KeyBindings;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.settings.KeyModifier;
//Minecraft.getInstance().player.sendSystemMessage(Component.literal("fuck you"));
@EventBusSubscriber(modid = Tenebris.MOD_ID, value = Dist.CLIENT)
public class EventKeyInput {

    public static boolean IsTransformed = false;

    @SubscribeEvent
    public static float handleEventInput(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        KeyMapping mode = KeyBindings.TransformClaymore;


        if (mc.player == null)
            return 0;

        ItemStack tool = Claymore.getClaymore(mc.player);

        if (KeyBindings.TransformClaymore.consumeClick() && ((KeyBindings.TransformClaymore.getKeyModifier() == KeyModifier.NONE && KeyModifier.getActiveModifier() == KeyModifier.NONE) || KeyBindings.TransformClaymore.getKeyModifier() != KeyModifier.NONE))
        {
            if (tool.isEmpty()) {
                return 0;
            }else {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("fuck you" + IsTransformed));
                if (tool.getItem() instanceof Claymore) {
                    IsTransformed = !IsTransformed;
                }
            }
        }
        return 0;
    }
}

