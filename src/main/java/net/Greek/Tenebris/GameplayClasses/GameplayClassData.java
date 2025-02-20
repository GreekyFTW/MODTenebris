package net.Greek.Tenebris.GameplayClasses;

import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.dataAttachments.DataAttachmentRegistry;
import net.Greek.Tenebris.event.GameplayClassEvent.ChangeClassEvent;
import net.Greek.Tenebris.event.TenebraeEvent.ChangeTenebraeEvent;
import net.Greek.Tenebris.registry.AttributeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;

public class GameplayClassData {
    private boolean isMob = false;
    private String GameplayClass = "No_Class";
    public static final String CLASS_ID = "class_id";

    private ServerPlayer serverPlayer = null;

    public GameplayClassData(boolean isMob) {
        this.isMob = isMob;
    }

    public GameplayClassData() {
        this(false);
    }

    public GameplayClassData(ServerPlayer serverPlayer) {
        this(false);
        this.serverPlayer = serverPlayer;
    }

    public String getGameplayClass() {
        return GameplayClass;
    }

    public void setGameplayClass(String GameplayClass) {
        //Event will not get posted if the server player is null
        ChangeClassEvent e = new ChangeClassEvent(this.serverPlayer, this, this.GameplayClass, GameplayClass);
        if (this.serverPlayer == null || !NeoForge.EVENT_BUS.post(e).isCanceled()) {
            this.GameplayClass = e.getNewGameplayClass();
        }
        if (this.serverPlayer != null) {

                this.GameplayClass = GameplayClass;

        }
    }

    public static GameplayClassData getGameplayClassData(LivingEntity livingEntity) {
        return livingEntity.getData(DataAttachmentRegistry.GAMEPLAY_CLASS_DATA);
    }

    public void saveNBTData(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putString(CLASS_ID, (String) GameplayClass);
    }

    public void loadNBTData(CompoundTag compound, HolderLookup.Provider provider) {

        GameplayClass = compound.getString(CLASS_ID);

        //getSyncedData().loadNBTData(compound, provider);
    }
}
