package net.Greek.Tenebris.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.network.packets.SyncGameplayClassPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Collection;


public class GameplayClassCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(Commands.literal("class_data")
                .requires((p) -> p.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("targets", EntityArgument.players())
                                .then(Commands.argument("ClassId", StringArgumentType.string())
                                        .executes((context -> changeGameplayClass(context.getSource(),EntityArgument.getPlayers(context,"targets"), StringArgumentType.getString(context,"ClassId"),true))))))
        );
    }

    private static int changeGameplayClass(CommandSourceStack source, Collection<ServerPlayer> targets, String ClassData, boolean set) {
        targets.forEach((serverPlayer -> {
            GameplayClassData pmg = GameplayClassData.getGameplayClassData(serverPlayer);
            var base = set ? 0 : pmg.getGameplayClass();
            pmg.setGameplayClass(ClassData);
            PacketDistributor.sendToPlayer(serverPlayer, new SyncGameplayClassPacket(pmg));
        }));
        String s = set ? "set" : "add";
        if (targets.size() == 1) {
            source.sendSuccess(() -> Component.translatable("commands.tenebrae." + s + ".success.single", ClassData, targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendSuccess(() -> Component.translatable("commands.tenebrae." + s + ".success.multiple", ClassData, targets.size()), true);
        }

        return targets.size();
    }


}
