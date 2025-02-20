package net.Greek.Tenebris.GameplayClasses.Abilities.Malerin.Misc;

import net.Greek.Tenebris.GameplayClasses.Abstract.AbstractProjectile;
import net.Greek.Tenebris.registry.EntityRegistry;
import net.Greek.Tenebris.sound.ModSounds;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.client.event.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

public class PaintBall extends Projectile {


    protected PaintBall(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

    }


}
