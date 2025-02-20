package net.Greek.Tenebris.GameplayClasses;

import net.Greek.Tenebris.dataAttachments.DataAttachmentRegistry;
import net.Greek.Tenebris.event.GameplayClassEvent.ChangePlayerDataEvent;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;

public class GameplayPlayerData {
    private boolean isMob = false;

    public GameplayPlayerData(boolean isMob) {
        this.isMob = isMob;
    }

    public GameplayPlayerData() {
        this(false);
    }

    public GameplayPlayerData(ServerPlayer serverPlayer) {
        this(false);
        this.serverPlayer = serverPlayer;
        //this.playerRecasts = new PlayerRecasts(serverPlayer);
    }

    private ServerPlayer serverPlayer = null;
    public static final String DASH = "dash";
    public static final String DASHCD = "dash_cd";
    public static final String DASHTYPE = "dash_type";

    private int dashCount;

    public int getDashCount() {
        return dashCount;
    }

    public void setGameplayDashCount(int dashCount) {
        //Event will not get posted if the server player is null
        ChangePlayerDataEvent e = new ChangePlayerDataEvent(this.serverPlayer, this, this.dashCount,dashCount);
        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
            this.dashCount = e.getNewDashCount();
        }
        if (this.serverPlayer != null) {

            this.dashCount = dashCount;

        }
    }

    private int dashInternalCooldown;

    public int getDashInternalCoolDown() {
        return dashInternalCooldown;
    }

    public void setGameplayDashCountCooldown(int dashCount, int DashCooldown) {
        ChangePlayerDataEvent e = new ChangePlayerDataEvent(this.serverPlayer, this, this.dashCount,dashCount, this.dashInternalCooldown,DashCooldown);
        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
            this.dashInternalCooldown = e.getNewDashCooldown();
            this.dashCount = e.getNewDashCount();
        }
        if (this.serverPlayer != null) {

            this.dashInternalCooldown = DashCooldown;
            this.dashCount = dashCount;
        }
    }

    private String dashType = "dash";

    public String getDashType() {
        return dashType;
    }

    public void setGameplayDashType(String dashType) {
        ChangePlayerDataEvent e = new ChangePlayerDataEvent(this.serverPlayer, this, this.dashType,dashType);
        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
            this.dashType = e.getNewDashType();

        }
        if (this.serverPlayer != null) {

            this.dashType = dashType;
        }
    }

    public static final String SKILL_FIRST_LVL = "s_lvl_one";
    public static final String SKILL_SECOND_LVL = "s_lvl_two";
    public static final String SKILL_THIRD_LVL = "s_lvl_three";
    public static final String SKILL_FOURTH_LVL = "s_lvl_four";
    //public static final String DASHCD = "dash_cd";

    public static GameplayPlayerData getGameplayPlayerData(LivingEntity livingEntity) {
        return livingEntity.getData(DataAttachmentRegistry.GAMEPLAY_PLAYER_DATA);
    }

    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putInt(DASH, dashCount);
        compound.putInt(DASHCD, dashInternalCooldown);
        compound.putString(DASHTYPE, dashType);
    }


    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        dashCount = compound.getInt(DASH);
        dashInternalCooldown = compound.getInt(DASHCD);
        dashType = compound.getString(DASHTYPE);
    }



}
