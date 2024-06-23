package net.Greek.Tenebris.item.Abstract;

import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.*;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public class AbstractMultiEffectProjectile extends AbstractHurtingProjectile implements ItemSupplier {

    public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
            true, false, Optional.empty(), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
    );
    public static final double JUMP_SCALE = 0.25;

    public AbstractMultiEffectProjectile(EntityType<? extends AbstractWindCharge> p_325927_, Level p_326350_) {
        super(p_325927_, p_326350_);
        this.accelerationPower = 0.0;
    }

    public AbstractMultiEffectProjectile(
            EntityType<? extends AbstractWindCharge> entityType, Level level, Entity entity, double v, double v1, double v2
    ) {
        super(entityType, v, v1, v2, level);
        this.setOwner(entity);
        this.accelerationPower = 0.0;
    }

    protected AbstractMultiEffectProjectile(
            EntityType<? extends AbstractWindCharge> entityType, double v, double v1, double v2, Vec3 vec3, Level level
    ) {
        super(entityType, v, v1, v2, vec3, level);
        this.accelerationPower = 0.0;
    }

    @Override
    protected AABB makeBoundingBox() {
        float f = this.getType().getDimensions().width() / 2.0F;
        float f1 = this.getType().getDimensions().height();
        float f2 = 0.15F;
        return new AABB(
                this.position().x - (double)f,
                this.position().y - 0.15F,
                this.position().z - (double)f,
                this.position().x + (double)f,
                this.position().y - 0.15F + (double)f1,
                this.position().z + (double)f
        );
    }

    @Override
    public boolean canCollideWith(Entity pEntity) {
        return pEntity instanceof AbstractWindCharge ? false : super.canCollideWith(pEntity);
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        if (entity instanceof AbstractWindCharge) {
            return false;
        } else {
            return entity.getType() == EntityType.END_CRYSTAL ? false : super.canHitEntity(entity);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (!this.level().isClientSide) {
            LivingEntity livingentity = this.getOwner() instanceof LivingEntity livingentity1 ? livingentity1 : null;
            Entity entity = entityHitResult.getEntity();
            if (livingentity != null) {
                livingentity.setLastHurtMob(entity);
            }

            DamageSource damagesource = this.damageSources().windCharge(this, livingentity);
            if (entity.hurt(damagesource, 1.0F) && entity instanceof LivingEntity livingentity2) {
                EnchantmentHelper.doPostAttackEffects((ServerLevel)this.level(), livingentity2, damagesource);
            }

            this.explode(this.position());
        }
    }

    @Override
    public void push(double pX, double pY, double pZ) {
    }

    protected void explode(Vec3 vec3) {
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide) {
            Vec3i vec3i = blockHitResult.getDirection().getNormal();
            Vec3 vec3 = Vec3.atLowerCornerOf(vec3i).multiply(0.25, 0.25, 0.25);
            Vec3 vec31 = blockHitResult.getLocation().add(vec3);
            this.explode(vec31);
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected float getInertia() {
        return 1.0F;
    }

    @Override
    protected float getLiquidInertia() {
        return this.getInertia();
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return null;
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide && this.getBlockY() > this.level().getMaxBuildHeight() + 30) {
            this.explode(this.position());
            this.discard();
        } else {
            super.tick();
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float pAmount) {
        return false;
    }

}
