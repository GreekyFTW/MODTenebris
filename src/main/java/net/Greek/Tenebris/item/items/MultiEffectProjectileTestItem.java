package net.Greek.Tenebris.item.items;

import net.Greek.Tenebris.util.projectiles.MultiEffectProjectile;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MultiEffectProjectileTestItem extends Item implements ProjectileItem{

    private static final int COOLDOWN = 10;

    public MultiEffectProjectileTestItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            MultiEffectProjectile mep = new MultiEffectProjectile(pPlayer, pLevel, pPlayer.position().x(), pPlayer.getEyePosition().y(), pPlayer.position().z());
            mep.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(mep);
        }

        pLevel.playSound(
                null,
                pPlayer.getX(),
                pPlayer.getY(),
                pPlayer.getZ(),
                SoundEvents.WIND_CHARGE_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.getCooldowns().addCooldown(this, 0);
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, pPlayer);
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        RandomSource randomsource = level.getRandom();
        double d0 = randomsource.triangle((double)direction.getStepX(), 0.11485000000000001);
        double d1 = randomsource.triangle((double)direction.getStepY(), 0.11485000000000001);
        double d2 = randomsource.triangle((double)direction.getStepZ(), 0.11485000000000001);
        Vec3 vec3 = new Vec3(d0, d1, d2);
        MultiEffectProjectile mep = new MultiEffectProjectile(level, position.x(), position.y(), position.z(), vec3);
        mep.setDeltaMovement(vec3);
        return mep;
    }

    @Override
    public void shoot(Projectile projectile, double v, double v1, double v2, float v3, float v4) {
    }

}
