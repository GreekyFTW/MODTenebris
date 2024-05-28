//
//package net.Greek.Tenebris.client.event;
//
//import net.Greek.Tenebris.Tenebris;
//import net.Greek.Tenebris.util.KeyBinding;
//import net.minecraft.client.Minecraft;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.Mod;
//import net.neoforged.neoforge.client.event.InputEvent;
//import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
//
//import net.neoforged.fml.common.EventBusSubscriber;
//
//public class ClientEvents {
//
//    @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//    public static class ClientNeoForgeEvents {
//        @SubscribeEvent
//        public static void onKeyInput(InputEvent.Key event) {
//                if (KeyBinding.TRANFORM_KEY.consumeClick()) {
//                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("fuck you"));
//                }
//            }
//    }
//
//   // @EventBusSubscriber(modid = Tenebris.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
//    public static class ClientBusEvents {
//
//        @SubscribeEvent
//        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
//            event.register(KeyBinding.TRANFORM_KEY);
//        }
//
//
//    }
//}
//
//
