package net.Greek.Tenebris.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.network.packets.SyncTenebraePacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.network.chat.Component;

import java.util.Collection;

public class TenebraeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(Commands.literal("tenebrae")
                .requires((p) -> p.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("targets", EntityArgument.players())
                                .then(Commands.argument("amount", IntegerArgumentType.integer())
                                        .executes((context) -> changeTenebrae(context.getSource(), EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "amount"), true)))))
                .then(Commands.literal("add")
                        .then(Commands.argument("targets", EntityArgument.players())
                                .then(Commands.argument("amount", IntegerArgumentType.integer())
                                        .executes((context) -> changeTenebrae(context.getSource(), EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "amount"), false)))))
                .then(Commands.literal("get")
                        .then(Commands.argument("targets", EntityArgument.player())
                                .executes((context) -> getTenebrae(context.getSource(), EntityArgument.getPlayer(context, "targets")))))
        );
    }

    private static int changeTenebrae(CommandSourceStack source, Collection<ServerPlayer> targets, int amount, boolean set) {
        targets.forEach((serverPlayer -> {
            TenebraeData pmg = TenebraeData.getPlayerTenebraeData(serverPlayer);
            var base = set ? 0 : pmg.getTenebrae();
            pmg.setTenebrae(amount + base);
            PacketDistributor.sendToPlayer(serverPlayer, new SyncTenebraePacket(pmg));
        }));
        String s = set ? "set" : "add";
        if (targets.size() == 1) {
            source.sendSuccess(() -> Component.translatable("commands.tenebrae." + s + ".success.single", amount, targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendSuccess(() -> Component.translatable("commands.tenebrae." + s + ".success.multiple", amount, targets.size()), true);
        }

        return targets.size();
    }

    private static int getTenebrae(CommandSourceStack source, ServerPlayer serverPlayer) {
        TenebraeData pmg = TenebraeData.getPlayerTenebraeData(serverPlayer);
        var tenebrae = (int) pmg.getTenebrae();
        source.sendSuccess(() -> Component.translatable("commands.tenebrae.get.success", serverPlayer.getDisplayName(), tenebrae), true);

        return tenebrae;
    }
}
