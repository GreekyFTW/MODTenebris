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


}
