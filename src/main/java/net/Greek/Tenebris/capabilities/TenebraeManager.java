package net.Greek.Tenebris.capabilities;

import net.Greek.Tenebris.api.Spells.AbstractSpell;
import net.Greek.Tenebris.api.Spells.CastSource;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.network.packets.SyncTenebraePacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;

import static net.Greek.Tenebris.registry.AttributeRegistry.MANA_REGEN;
import static net.Greek.Tenebris.registry.AttributeRegistry.MAX_MANA;

public class TenebraeManager implements ITenebraeManager{
    public static final int MANA_REGEN_TICKS = 10;
    public static final int CONTINUOUS_CAST_TICK_INTERVAL = 10;

    public boolean regenPlayerTenebrae(ServerPlayer serverPlayer, TenebraeData playerTenebraeData) {
        int playerMaxTenebrae = (int) serverPlayer.getAttributeValue(MAX_MANA);
        var mana = playerTenebraeData.getTenebrae();
        if (mana != playerMaxTenebrae) {
            float playerTenebraeRegenMultiplier = (float) serverPlayer.getAttributeValue(MANA_REGEN);
            var increment = playerMaxTenebrae * playerTenebraeRegenMultiplier * .01f;
            playerTenebraeData.setTenebrae(Mth.clamp(playerTenebraeData.getTenebrae() + increment, 0, playerMaxTenebrae));
            return true;
        } else {
            return false;
        }
    }


    public void tick(Level level) {
        boolean doTenebraeRegen = level.getServer().getTickCount() % MANA_REGEN_TICKS == 0;

        level.players().stream().toList().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                TenebraeData playerTenebraeData = TenebraeData.getPlayerTenebraeData(serverPlayer);
//                playerTenebraeData.getPlayerCooldowns().tick(1);
//                playerTenebraeData.getPlayerRecasts().tick(2);

//                if (playerTenebraeData.isCasting()) {
//                    playerTenebraeData.handleCastDuration();
//                    var spell = SpellRegistry.getSpell(playerTenebraeData.getCastingSpellId());
//                    if ((spell.getCastType() == CastType.LONG && !serverPlayer.isUsingItem()) || spell.getCastType() == CastType.INSTANT) {
//                        if (playerTenebraeData.getCastDurationRemaining() <= 0) {
//                            spell.castSpell(serverPlayer.level, playerTenebraeData.getCastingSpellLevel(), serverPlayer, playerTenebraeData.getCastSource(), true);
//                            if (playerTenebraeData.getCastSource() == CastSource.SCROLL) {
//                                Scroll.attemptRemoveScrollAfterCast(serverPlayer);
//                            }
//                            spell.onServerCastComplete(serverPlayer.level, playerTenebraeData.getCastingSpellLevel(), serverPlayer, playerTenebraeData, false);
//                        }
//                    } else if (spell.getCastType() == CastType.CONTINUOUS) {
//                        if ((playerTenebraeData.getCastDurationRemaining() + 1) % CONTINUOUS_CAST_TICK_INTERVAL == 0) {
//                            if (playerTenebraeData.getCastDurationRemaining() < CONTINUOUS_CAST_TICK_INTERVAL || (playerTenebraeData.getCastSource().consumesTenebrae() && playerTenebraeData.getTenebrae() - spell.getTenebraeCost(playerTenebraeData.getCastingSpellLevel()) * 2 < 0)) {
//                                spell.castSpell(serverPlayer.level, playerTenebraeData.getCastingSpellLevel(), serverPlayer, playerTenebraeData.getCastSource(), true);
//
//                                if (playerTenebraeData.getCastSource() == CastSource.SCROLL) {
//                                    Scroll.attemptRemoveScrollAfterCast(serverPlayer);
//                                }
//
//                                spell.onServerCastComplete(serverPlayer.level, playerTenebraeData.getCastingSpellLevel(), serverPlayer, playerTenebraeData, false);
//
//                            } else {
//                                spell.castSpell(serverPlayer.level, playerTenebraeData.getCastingSpellLevel(), serverPlayer, playerTenebraeData.getCastSource(), false);
//                            }
//                        }
//                    }
//
//                    if (playerTenebraeData.isCasting()) {
//                        spell.onServerCastTick(serverPlayer.level, playerTenebraeData.getCastingSpellLevel(), serverPlayer, playerTenebraeData);
//                    }
//                }
//
                //Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+serverPlayer.getAttributeValue(MAX_MANA)));
                //Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal(""+playerTenebraeData.getTenebrae()));
                //Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal(""+player));

                if (doTenebraeRegen) {
                    if (regenPlayerTenebrae(serverPlayer, playerTenebraeData)) {
                        PacketDistributor.sendToPlayer(serverPlayer, new SyncTenebraePacket(playerTenebraeData));
                        Objects.requireNonNull(serverPlayer).sendSystemMessage(Component.literal(""+player));
                    }
                }
            }
        });
    }

    @Override
    public void addCooldown(ServerPlayer serverPlayer, AbstractSpell spell, CastSource castSource) {

    }
}
