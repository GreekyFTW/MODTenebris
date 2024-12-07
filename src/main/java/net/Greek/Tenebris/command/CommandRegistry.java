package net.Greek.Tenebris.command;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber()
public class CommandRegistry {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        var commandDispatcher = event.getDispatcher();
        //var commandBuildContext = event.getBuildContext();

        TenebraeCommand.register(commandDispatcher);
    }
}
