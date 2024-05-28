package net.Greek.Tenebris.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.Greek.Tenebris.Tenebris;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.ArrayList;
import java.util.List;



//
//public class KeyBinding {
//    public static final String KEY_CATEGORY_TENEBRIS = "key.category.tenebris";
//
//    public static final String CLAYMORE_TRANSFORM = "key.tenebris.transform_claymore";
//
//    public static final KeyMapping TRANFORM_KEY = new KeyMapping(CLAYMORE_TRANSFORM, KeyConflictContext.IN_GAME,
//            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_0, KEY_CATEGORY_TENEBRIS);
//
// }
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public  class KeyBindings {

    //public static final String KEY_CATEGORY_TENEBRIS = "key.category.tenebris";
    private static final List<KeyMapping> keyMappings = new ArrayList<>();
    private static final KeyConflictTransform CONFLICT_TRANSFORM = new KeyConflictTransform();
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyBindings.class);


    public static KeyMapping TransformClaymore = createBinding("transform_claymore", GLFW.GLFW_KEY_O);//repeat to add new keybinds

    private static KeyMapping createBinding(String name, int key) {
        KeyMapping keyBinding = new KeyMapping(getKey(name), CONFLICT_TRANSFORM, InputConstants.Type.KEYSYM.getOrCreate(key), getKey("category"));
        keyMappings.add(keyBinding);
        return keyBinding;
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        LOGGER.debug("Registering {} keybinding for {}", keyMappings.size(), Tenebris.MOD_ID);
        keyMappings.forEach(event::register);
    }

    private static String getKey(String name) {
        return String.join(".", "key", Tenebris.MOD_ID, name);
    }


    public static class KeyConflictTransform implements IKeyConflictContext {
        @Override
        public boolean isActive() {
            Player player = Minecraft.getInstance().player;
            return !KeyConflictContext.GUI.isActive() && player != null;
        }

        @Override
        public boolean conflicts(IKeyConflictContext other) {
            return other == this || other == KeyConflictContext.IN_GAME;
        }
    }
}
