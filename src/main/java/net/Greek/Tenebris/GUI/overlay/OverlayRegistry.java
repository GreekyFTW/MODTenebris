package net.Greek.Tenebris.GUI.overlay;

import net.Greek.Tenebris.Tenebris;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import static net.Greek.Tenebris.Tenebris.rl;

@EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayRegistry {

    @SubscribeEvent
    public static void OnRegisterOverlays(final RegisterGuiLayersEvent event){
        event.registerBelow(VanillaGuiLayers.CROSSHAIR,rl("spell_slot_overlay"), SpellSlotOverlay.instance);
        event.registerBelow(VanillaGuiLayers.CROSSHAIR,rl("mana_bar_overlay"), ManaBarOverlay.instance);

    }


}
