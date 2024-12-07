package net.Greek.Tenebris.event.client;

import net.Greek.Tenebris.api.TenebraeData;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.UUID;

public class ClientTenebraeData {
    /**
     * Current Player's Synced Data
     */
    private static final TenebraeData playerTenebraeData = new TenebraeData();

    /**
     * Other Player's Synced Data
     */
//    private static final HashMap<Integer, SyncedSpellData> playerSyncedDataLookup = new HashMap<>();
//    private static final SyncedSpellData emptySyncedData = new SyncedSpellData(-999);

    /**
     * Spell Selections
     */
//    static SpellSelectionTenebraeger spellSelectionTenebraeger;
//
//    public static SpellSelectionTenebraeger getSpellSelectionTenebraeger() {
//        if (spellSelectionTenebraeger == null) {
//            var player = MinecraftInstanceHelper.getPlayer();
//            if (player != null) {
//                spellSelectionTenebraeger = new SpellSelectionTenebraeger(player);
//            }
//
//        }
//
//        return spellSelectionTenebraeger;
//    }
//
//    public static void updateSpellSelectionTenebraeger(@NotNull ServerPlayer player) {
//        spellSelectionTenebraeger = new SpellSelectionTenebraeger(player);
//    }
//
//    public static void updateSpellSelectionTenebraeger() {
//        var player = Minecraft.getInstance().player;
//        if (player != null) {
//            spellSelectionTenebraeger = new SpellSelectionTenebraeger(Minecraft.getInstance().player);
//        }
//    }

    /**
     * Local Targeting data
     */
//    private static ClientSpellTargetingData spellTargetingData;
//
//    public static void setTargetingData(ClientSpellTargetingData spellTargetingData) {
//        ClientTenebraeData.spellTargetingData = spellTargetingData;
//    }
//
//    public static ClientSpellTargetingData getTargetingData() {
//        if (spellTargetingData == null)
//            setTargetingData(new ClientSpellTargetingData());
//        return spellTargetingData;
//    }
//
//    public static void resetTargetingData() {
//        spellTargetingData = null;
//    }

    /**
     * Animation Data
     */
//    public static HashMap<UUID, KeyframeAnimationPlayer> castingAnimationPlayerLookup = new HashMap<>();
//
//    public static PlayerCooldowns getCooldowns() {
//        return playerTenebraeData.getPlayerCooldowns();
//    }
//
//    public static PlayerRecasts getRecasts() {
//        return playerTenebraeData.getPlayerRecasts();
//    }
//
//    public static void setRecasts(PlayerRecasts playerRecasts) {
//        playerTenebraeData.setPlayerRecasts(playerRecasts);
//    }
//
//    public static float getCooldownPercent(AbstractSpell spell) {
//        return playerTenebraeData.getPlayerCooldowns().getCooldownPercent(spell);
//    }

    public static int getPlayerTenebrae() {
        return (int) playerTenebraeData.getTenebrae();
    }

    public static void setTenebrae(int playerTenebrae) {
        ClientTenebraeData.playerTenebraeData.setTenebrae(playerTenebrae);
    }

//    public static CastType getCastType() {
//        return ClientTenebraeData.playerTenebraeData.getCastType();
//    }
//
//    public static String getCastingSpellId() {
//        return playerTenebraeData.getCastingSpellId();
//    }
//
//    public static int getCastingSpellLevel() {
//        return playerTenebraeData.getCastingSpellLevel();
//    }
//
//    public static int getCastDurationRemaining() {
//        return playerTenebraeData.getCastDurationRemaining();
//    }
//
//    public static int getCastDuration() {
//        return playerTenebraeData.getCastDuration();
//    }
//
//    public static boolean isCasting() {
//        return playerTenebraeData.isCasting();
//    }
//
//    public static void handleCastDuration() {
//        playerTenebraeData.handleCastDuration();
//    }
//
//    public static float getCastCompletionPercent() {
//        return playerTenebraeData.getCastCompletionPercent();
//    }
//
//    public static void setClientCastState(String spellId, int spellLevel, int castDuration, CastSource castSource, String castingEquipmentSlot) {
//        playerTenebraeData.initiateCast(SpellRegistry.getSpell(spellId), spellLevel, castDuration, castSource, castingEquipmentSlot);
//    }

//    public static void resetClientCastState(UUID playerUUID) {
//        //Ironsspellbooks.logger.debug("resetClientCastState.1: instanceUUID:{}, playerUUID:{}", Minecraft.getInstance().player.getUUID(), playerUUID);
//
//        if (Minecraft.getInstance().player.getUUID().equals(playerUUID)) {
//            //Ironsspellbooks.logger.debug("resetClientCastState.1.1");
//            playerTenebraeData.resetCastingState();
//            resetTargetingData();
//        }
//
//        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isUsingItem() && Minecraft.getInstance().player.getUUID().equals(playerUUID)) {
//            //Ironsspellbooks.logger.debug("resetClientCastState.2: instanceUUID:{}, playerUUID:{}", Minecraft.getInstance().player.getUUID(), playerUUID);
//            Minecraft.getInstance().player.stopUsingItem();
//        }
//    }
//
//    public static SyncedSpellData getSyncedSpellData(LivingEntity livingEntity) {
//        if (livingEntity instanceof Player) {
//            return playerSyncedDataLookup.getOrDefault(livingEntity.getId(), emptySyncedData);
//        }
//        if (livingEntity instanceof ITenebraeEntity abstractSpellCastingMob) {
//            return abstractSpellCastingMob.getTenebraeData().getSyncedData();
//        }
//        return new SyncedSpellData(null);
//
//    }
//
//    public static void handlePlayerSyncedData(SyncedSpellData playerSyncedData) {
//        if (Log.SPELL_SELECTION) {
//            Tenebris.LOGGER.debug("ClientTenebraeData.handlePlayerSyncedData {}", playerSyncedData.getSpellSelection());
//        }
//        playerSyncedDataLookup.put(playerSyncedData.getServerPlayerId(), playerSyncedData);
//    }
//
//    public static void handleAbstractCastingMobSyncedData(int entityId, SyncedSpellData syncedSpellData) {
//        var level = Minecraft.getInstance().level;
//
//        if (Log.SPELL_DEBUG) {
//            Tenebris.LOGGER.debug("handleAbstractCastingMobSyncedData {}, {}, {}", level, entityId, syncedSpellData);
//        }
//
//        if (level == null) {
//            return;
//        }
//
//        var entity = level.getEntity(entityId);
//        if (entity instanceof ITenebraeEntity abstractSpellCastingMob) {
//            abstractSpellCastingMob.setSyncedSpellData(syncedSpellData);
//        }
//    }
}
