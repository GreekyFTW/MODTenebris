package net.Greek.Tenebris.api;

import net.Greek.Tenebris.dataAttachments.DataAttachmentRegistry;
import net.Greek.Tenebris.event.TenebraeEvent.ChangeTenebraeEvent;
import net.Greek.Tenebris.registry.AttributeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;

public class TenebraeData {
    private boolean isMob = false;

    public TenebraeData(boolean isMob) {
        this.isMob = isMob;
    }

    public TenebraeData() {
        this(false);
    }

    public TenebraeData(ServerPlayer serverPlayer) {
        this(false);
        this.serverPlayer = serverPlayer;
        //this.playerRecasts = new PlayerRecasts(serverPlayer);
    }

//    public void setServerPlayer(ServerPlayer serverPlayer) {
//        if (this.serverPlayer == null && serverPlayer != null) {
//            this.serverPlayer = serverPlayer;
//            this.playerRecasts = new PlayerRecasts(serverPlayer);
//        }
//    }

    private ServerPlayer serverPlayer = null;
    public static final String MANA = "mana";
    public static final String COOLDOWNS = "cooldowns";
    public static final String RECASTS = "recasts";

    /********* MANA *******************************************************/

    private float mana;

    public float getTenebrae() {
        return mana;
    }

    public void setTenebrae(float mana) {
        //Event will not get posted if the server player is null
        ChangeTenebraeEvent e = new ChangeTenebraeEvent(this.serverPlayer, this, this.mana, mana);
        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
            this.mana = e.getNewTenebrae();
        }
        if (this.serverPlayer != null) {
            float maxTenebrae = (float) serverPlayer.getAttributeValue(AttributeRegistry.MAX_MANA);
            if (this.mana > maxTenebrae) {
                this.mana = maxTenebrae;
            }
        }
    }

    public void addTenebrae(float mana) {
        setTenebrae(this.mana + mana);
    }
    /********* DASH DATA *******************************************************/


//    private float dash;
//
//    public float getDashCount() {
//        return dash;
//    }
//
//    public void setTenebrae(int dash) {
//        //Event will not get posted if the server player is null
//        ChangeTenebraeEvent e = new ChangeTenebraeEvent(this.serverPlayer, this, this.dash, dash);
//        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
//            this.dash = e.getNewTenebrae();
//        }
//        if (this.serverPlayer != null) {
//
//                this.dash = maxTenebrae;
//
//        }
//    }
//
//    public void addTenebrae(int dash) {
//        setTenebrae(this.dash + dash);
//    }
    /********* SYNC DATA *******************************************************/

//    private SyncedSpellData syncedSpellData;
//
//    public SyncedSpellData getSyncedData() {
//        if (syncedSpellData == null) {
//            syncedSpellData = new SyncedSpellData(serverPlayer);
//        }
//
//        return syncedSpellData;
//    }
//
//    public void setSyncedData(SyncedSpellData syncedSpellData) {
//        this.syncedSpellData = syncedSpellData;
//    }

    /********* CASTING *******************************************************/

//    private int castingSpellLevel = 0;
//    private int castDuration = 0;
//    private int castDurationRemaining = 0;
//    private CastSource castSource;
//    private CastType castType;
//    private @Nullable ICastData additionalCastData;
//    private int poisonedTimestamp; //Poison does not have a damage source, so we mark when we are poisoned to ignore if instead of cancelling our long cast
//
//    private ItemStack castingItemStack = ItemStack.EMPTY;
//
//
//    public void resetCastingState() {
//        //Ironsspellbooks.logger.debug("PlayerTenebraeData.resetCastingState: serverPlayer:{}", serverPlayer);
//        this.castingSpellLevel = 0;
//        this.castDuration = 0;
//        this.castDurationRemaining = 0;
//        this.castSource = CastSource.NONE;
//        this.castType = CastType.NONE;
//        this.getSyncedData().setIsCasting(false, "", 0, getCastingEquipmentSlot());
//        resetAdditionalCastData();
//
//        if (serverPlayer != null) {
//            serverPlayer.stopUsingItem();
//        }
//    }
//
//    public void initiateCast(AbstractSpell spell, int spellLevel, int castDuration, CastSource castSource, String castingEquipmentSlot) {
//        this.castingSpellLevel = spellLevel;
//        this.castDuration = castDuration;
//        this.castDurationRemaining = castDuration;
//        this.castSource = castSource;
//        this.castType = spell.getCastType();
//        this.syncedSpellData.setIsCasting(true, spell.getSpellId(), spellLevel, castingEquipmentSlot);
//    }
//
//    public ICastData getAdditionalCastData() {
//        return additionalCastData;
//    }
//
//    public void setAdditionalCastData(ICastData newCastData) {
//        additionalCastData = newCastData;
//    }
//
//    public void resetAdditionalCastData() {
//        if (additionalCastData != null) {
//            additionalCastData.reset();
//            additionalCastData = null;
//        }
//    }
//
//    public boolean isCasting() {
//        return getSyncedData().isCasting();
//    }
//
//    public String getCastingEquipmentSlot() {
//        return getSyncedData().getCastingEquipmentSlot();
//    }
//
//    public String getCastingSpellId() {
//        return getSyncedData().getCastingSpellId();
//    }
//
//    public SpellData getCastingSpell() {
//        return new SpellData(SpellRegistry.getSpell(getSyncedData().getCastingSpellId()), castingSpellLevel);
//    }
//
//    public int getCastingSpellLevel() {
//        return castingSpellLevel;
//    }
//
//    public CastSource getCastSource() {
//        if (castSource == null) {
//            return CastSource.NONE;
//        }
//
//        return castSource;
//    }
//
//    public CastType getCastType() {
//        return castType;
//    }

//    public float getCastCompletionPercent() {
//        if (castDuration == 0) {
//            return 1;
//        }
//
//        return 1 - (castDurationRemaining / (float) castDuration);
//    }
//
//    public int getCastDurationRemaining() {
//        return castDurationRemaining;
//    }
//
//    public int getCastDuration() {
//        return castDuration;
//    }
//
//    public void handleCastDuration() {
//        castDurationRemaining--;
//
//        if (castDurationRemaining <= 0) {
//            castDurationRemaining = 0;
//        }
//    }
//
//    public void setPlayerCastingItem(ItemStack itemStack) {
//        this.castingItemStack = itemStack;
//    }
//
//    public ItemStack getPlayerCastingItem() {
//        return this.castingItemStack;
//    }
//
//    public void markPoisoned() {
//        if (this.serverPlayer != null) {
//            this.poisonedTimestamp = serverPlayer.tickCount;
//        }
//    }
//
//    public boolean popMarkedPoison() {
//        if (this.serverPlayer != null) {
//            boolean poisoned = this.serverPlayer.tickCount - poisonedTimestamp <= 1;
//            //reset so magic damage on the same tick does not get marked as poison
//            poisonedTimestamp = 0;
//            return poisoned;
//        }
//        return false;
//    }
//
//    /********* COOLDOWNS *******************************************************/
//
//    private final PlayerCooldowns playerCooldowns = new PlayerCooldowns();
//
//    public PlayerCooldowns getPlayerCooldowns() {
//        return this.playerCooldowns;
//    }
//
//    /********* RECASTS *******************************************************/

//    private PlayerRecasts playerRecasts = new PlayerRecasts();
//
//    public PlayerRecasts getPlayerRecasts() {
//        return this.playerRecasts;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public void setPlayerRecasts(PlayerRecasts playerRecasts) {
//        this.playerRecasts = playerRecasts;
//    }

    /********* SYSTEM *******************************************************/

    public static TenebraeData getPlayerTenebraeData(LivingEntity livingEntity) {
        return livingEntity.getData(DataAttachmentRegistry.TENEBRAE_DATA);
    }

    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putInt(MANA, (int) mana);

//        if (playerCooldowns.hasCooldownsActive()) {
//            compound.put(COOLDOWNS, playerCooldowns.saveNBTData());
//        }
//
//        if (playerRecasts.hasRecastsActive()) {
//            compound.put(RECASTS, playerRecasts.saveNBTData(provider));
//        }

        //getSyncedData().saveNBTData(compound, provider);
    }

    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        mana = compound.getInt(MANA);

        var listTag = (ListTag) compound.get(COOLDOWNS);
        if (listTag != null && !listTag.isEmpty()) {
            //playerCooldowns.loadNBTData(listTag);
        }

        listTag = (ListTag) compound.get(RECASTS);
        if (listTag != null && !listTag.isEmpty()) {
            //playerRecasts.loadNBTData(listTag, provider);
        }

        //getSyncedData().loadNBTData(compound, provider);
    }

//    @Override
//    public String toString() {
//        return String.format("isCasting:%s, spellID:%s], spellLevel:%s, duration:%s, durationRemaining:%s, source:%s, type:%s",
//                getSyncedData().isCasting(),
//                getSyncedData().getCastingSpellId(),
//                castingSpellLevel,
//                castDuration,
//                castDurationRemaining,
//                castSource,
//                castType);
//    }
}
