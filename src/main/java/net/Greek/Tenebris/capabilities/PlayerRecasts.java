package net.Greek.Tenebris.capabilities;

import com.google.common.collect.Maps;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.registry.SpellRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.Greek.Tenebris.api.Spells.AbstractSpell;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;
import java.util.Map;

public class PlayerRecasts {
    private static final RecastInstance EMPTY = null;
    private final Map<String, RecastInstance> recastLookup;

    //This will only be null on the client side
    private final ServerPlayer serverPlayer;

    public PlayerRecasts() {
        this.recastLookup = Maps.newHashMap();
        this.serverPlayer = null;
    }

    public PlayerRecasts(ServerPlayer serverPlayer) {
        this.recastLookup = Maps.newHashMap();
        this.serverPlayer = serverPlayer;
    }

    @OnlyIn(Dist.CLIENT)
    public PlayerRecasts(Map<String, RecastInstance> recastLookup) {
        this.recastLookup = recastLookup;
        this.serverPlayer = null;
    }

    public boolean addRecast(RecastInstance recastInstance, TenebraeData magicData) {
        var existingRecastInstance = recastLookup.get(recastInstance.spellId);

        if (!isRecastActive(existingRecastInstance)) {
            //magicData.getPlayerCooldowns().removeCooldown(recastInstance.spellId);
            recastLookup.put(recastInstance.spellId, recastInstance);
            syncToPlayer(recastInstance);
            return true;
        }

        return false;
    }

    public boolean isRecastActive(RecastInstance recastInstance) {
        return recastInstance != null && recastInstance.remainingRecasts > 0 && recastInstance.remainingTicks > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void removeRecast(String spellId) {
        recastLookup.remove(spellId);
    }

    @OnlyIn(Dist.CLIENT)
    public void forceAddRecast(RecastInstance recastInstance) {
        recastLookup.put(recastInstance.spellId, recastInstance);
    }

    @OnlyIn(Dist.CLIENT)
    public void tickRecasts() {
        if (!recastLookup.isEmpty()) {
            recastLookup.values().stream().toList().forEach(x -> x.remainingTicks--);
        }
    }

    public boolean hasRecastsActive() {
        return !recastLookup.isEmpty();
    }

    public boolean hasRecastForSpell(String spellId) {
        return isRecastActive(recastLookup.get(spellId));
    }

    public int getRemainingRecastsForSpell(String spellId) {
        var recastInstance = recastLookup.getOrDefault(spellId, EMPTY);

        if (isRecastActive(recastInstance)) {
            return recastInstance.remainingRecasts;
        }

        return 0;
    }

    public RecastInstance getRecastInstance(String spellId) {
        return recastLookup.get(spellId);
    }

    public List<RecastInstance> getAllRecasts() {
        return recastLookup.values().stream().toList();
    }

    public List<RecastInstance> getActiveRecasts() {
        return recastLookup.values().stream().filter(this::isRecastActive).toList();
    }

    public void syncAllToPlayer() {
        if (serverPlayer != null) {
            //PacketDistributor.sendToPlayer(serverPlayer, new SyncRecastsPacket(recastLookup));
        }
    }

    public void syncToPlayer(RecastInstance recastInstance) {
        if (serverPlayer != null) {
            //PacketDistributor.sendToPlayer(serverPlayer, new SyncRecastPacket(recastInstance));
        }
    }

    public void syncRemoveToPlayer(String spellId) {
        if (serverPlayer != null) {
            //PacketDistributor.sendToPlayer(serverPlayer, new RemoveRecastPacket(spellId));
        }
    }



    public ListTag saveNBTData(HolderLookup.Provider provider) {
        var listTag = new ListTag();
        recastLookup.values().stream().filter(this::isRecastActive).forEach(recastInstance -> {
            if (recastInstance.remainingRecasts > 0 && recastInstance.remainingTicks > 0) {
                listTag.add(recastInstance.serializeNBT(provider));
            }
        });
        return listTag;
    }

    public void loadNBTData(ListTag listTag, HolderLookup.Provider provider) {
        if (listTag != null) {
            listTag.forEach(tag -> {
                var recastInstance = new RecastInstance();
                recastInstance.deserializeNBT(provider, (CompoundTag) tag);
                if (recastInstance.remainingRecasts > 0 && recastInstance.remainingTicks > 0) {
                    recastLookup.put(recastInstance.spellId, recastInstance);
                } else {
                    //cull anything leftover not removed. shouldn't get here
                }
            });
        }
    }

    public void tick(int actualTicks) {
        if (serverPlayer != null && serverPlayer.level().getGameTime() % actualTicks == 0) {
            recastLookup.values()
                    .stream()
                    .filter(r -> {
                        r.remainingTicks -= actualTicks;
                        return r.remainingTicks <= 0;
                    })
                    .toList();
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        recastLookup.values().forEach(recastInstance -> {
            sb.append(recastInstance.toString());
            sb.append("\n");
        });

        return sb.toString();
    }
}
