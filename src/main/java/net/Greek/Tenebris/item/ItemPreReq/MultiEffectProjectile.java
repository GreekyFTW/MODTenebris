package net.Greek.Tenebris.item.ItemPreReq;

import net.Greek.Tenebris.item.Abstract.AbstractMultiEffectProjectile;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public class MultiEffectProjectile extends AbstractMultiEffectProjectile {
    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
            true, false, Optional.of(1.22F), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
    );
    private static final float RADIUS = 1.2F;
    private int noDeflectTicks = 5;

    public MultiEffectProjectile(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }

    public MultiEffectProjectile(Player player, Level level, double v, double v1, double v2) {
        super(EntityType.WIND_CHARGE, level, player, v, v1, v2);
    }

//    public MultiEffectProjectile(Level p_326007_, double p_326331_, double p_326001_, double p_325990_, Vec3 p_347497_) {
//        super(EntityType.WIND_CHARGE, p_326331_, p_326001_, p_325990_, p_347497_, p_326007_);
//    }

    @Override
    public void tick() {
        super.tick();
        if (this.noDeflectTicks > 0) {
            this.noDeflectTicks--;
        }
    }

    @Override
    public boolean deflect(ProjectileDeflection projectileDeflection, @Nullable Entity entity, @Nullable Entity entity1, boolean b) {
        return this.noDeflectTicks > 0 ? false : super.deflect(projectileDeflection, entity, entity1, b);
    }

    @Override
    protected void explode(Vec3 vec3) {
        this.level()
                .explode(
                        this,
                        null,
                        EXPLOSION_DAMAGE_CALCULATOR,
                        vec3.x(),
                        vec3.y(),
                        vec3.z(),
                        1.2F,
                        false,
                        Level.ExplosionInteraction.TRIGGER,
                        ParticleTypes.GUST_EMITTER_SMALL,
                        ParticleTypes.GUST_EMITTER_LARGE,
                        SoundEvents.WIND_CHARGE_BURST
                );
    }
}
