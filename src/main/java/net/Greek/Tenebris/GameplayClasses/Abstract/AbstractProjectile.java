package net.Greek.Tenebris.GameplayClasses.Abstract;

import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.sound.ModSounds;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.sound.SoundEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class AbstractProjectile extends Projectile {

    protected ServerLevel level;

    protected AbstractProjectile(EntityType<? extends Projectile> pEntityType, Level level) {
        super(pEntityType, level);
    }

    protected static final int EXPIRE_TIME = 15 * 20;

    protected float damage;
    protected float explosionRadius;

    public void trailParticles() {

    }

    public void impactParticles(double x, double y, double z) {

    }

    public float getSpeed() {
        return 0;
    }

    public Optional<Holder<SoundEvent>> getImpactSound() {
        return null;
    }

    public void shoot(Vec3 rotation) {
        setDeltaMovement(rotation.scale(getSpeed()));
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public float getExplosionRadius() {
        return explosionRadius;
    }

    public void setExplosionRadius(float explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return super.canHitEntity(pTarget) && pTarget != getOwner();
    }

    @Override
    public void checkDespawn() {
        if (this.level instanceof ServerLevel serverLevel && !serverLevel.getChunkSource().chunkMap.getDistanceManager().inEntityTickingRange(this.chunkPosition().toLong())) {
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (tickCount > EXPIRE_TIME) {
            discard();
            return;
        }
        if (level().isClientSide) {
            trailParticles();
        }
        handleHitDetection();
        travel();
    }

    public void handleHitDetection() {
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !NeoForge.EVENT_BUS.post(new ProjectileImpactEvent(this, hitresult)).isCanceled()) {
            onHit(hitresult);
        }
    }

    public void travel() {
        this.xOld = getX();
        this.yOld = getY();
        this.zOld = getZ();
        setPos(position().add(getDeltaMovement()));
        ProjectileUtil.rotateTowardsMovement(this, 1);
        if (!this.isNoGravity()) {
            Vec3 vec34 = this.getDeltaMovement();
            this.setDeltaMovement(vec34.x, vec34.y - (double) 0.05F, vec34.z);
        }
    }

    @Override
    protected void onHit(HitResult hitresult) {
        super.onHit(hitresult);

        if (!level().isClientSide) {
            //impactParticles(getX(), getY(), getZ());
            //getImpactSound().ifPresent(this::doImpactSound);
        }
    }

    @Override
    public boolean shouldBeSaved() {
        return super.shouldBeSaved() && !Objects.equals(getRemovalReason(), RemovalReason.UNLOADED_TO_CHUNK);
    }

//    protected void doImpactSound(Holder<SoundEvent> sound) {
//        level().playSound(player,getX(), getY(), getZ(),  sound, ModSounds.SQUEEK.get(), 2, .9f + 3 * .2f);
//    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

    }

//    @Override
//    public void onAntiMagic(TenebraeData playerTenebraeData) {
//        this.impactParticles(getX(), getY(), getZ());
//        this.discard();
//    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Damage", this.getDamage());
        if (explosionRadius != 0) {
            tag.putFloat("ExplosionRadius", explosionRadius);
        }
        tag.putInt("Age", tickCount);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.damage = tag.getFloat("Damage");
        if (tag.contains("ExplosionRadius")) {
            this.explosionRadius = tag.getFloat("ExplosionRadius");
        }
        this.tickCount = tag.getInt("Age");
    }

//    @Override
//    protected void onHitEntity(EntityHitResult pResult) {
//        super.onHitEntity(pResult);
//        if (!shouldPierceShields() && (pResult.getEntity() instanceof ShieldPart || pResult.getEntity() instanceof AbstractShieldEntity)) {
//            this.onHitBlock(new BlockHitResult(pResult.getEntity().position(), Direction.fromYRot(this.getYRot()), pResult.getEntity().blockPosition(), false));
//        }
//    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    protected boolean shouldPierceShields() {
        return false;
    }
}
