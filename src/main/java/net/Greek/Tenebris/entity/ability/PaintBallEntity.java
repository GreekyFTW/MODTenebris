package net.Greek.Tenebris.entity.ability;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class PaintBallEntity extends Projectile {
    public PaintBallEntity(EntityType<? extends PaintBallEntity> pEntityType, Level pLevel) {
        super( pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
    }

    @Override
    public <T> boolean hasData(Supplier<AttachmentType<T>> type) {
        return super.hasData(type);
    }

    @Override
    public <T> T getData(Supplier<AttachmentType<T>> type) {
        return super.getData(type);
    }

    @Override
    public <T> Optional<T> getExistingData(Supplier<AttachmentType<T>> type) {
        return super.getExistingData(type);
    }

    @Override
    public <T> @Nullable T setData(Supplier<AttachmentType<T>> type, T data) {
        return super.setData(type, data);
    }

    @Override
    public <T> @Nullable T removeData(Supplier<AttachmentType<T>> type) {
        return super.removeData(type);
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (pResult.getEntity() instanceof PaintBallEntity entity) {
            if (entity.getOwner() != this.getOwner()) {

            }
        }
    }


}
