package com.jmteam.cake.common.entity;

import com.jmteam.cake.common.init.ModEntities;
import com.jmteam.cake.util.helpers.WorldUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityCake extends ThrowableEntity implements IProjectile {

    public LivingEntity shooter;
    public float damage;
    private DamageSource damageSource = DamageSource.GENERIC;

    public EntityCake(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityCake(World worldIn, LivingEntity shooter, BlockPos pos, float damage) {
       super(ModEntities.CAKE, worldIn);
       setShooter(shooter);
       setPosition(pos.getX(), pos.getY(), pos.getZ());
       this.damage = damage;
    }

    public void setShooter(LivingEntity entity) {
        this.shooter = entity;
    }

    public void setDamageSource(DamageSource damageSource) {
        this.damageSource = damageSource;
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksExisted % 80 == 0) {
            onKillCommand();
        }
    }

    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
        float f1 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
        float f2 = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, inaccuracy);
        this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                EntityRayTraceResult result1 = (EntityRayTraceResult) result;
                Entity entity = result1.getEntity();

                if (entity != null && entity != shooter) {
                    entity.attackEntityFrom(damageSource, damage);
                }
            } else if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockRayTraceResult rayTraceResult = (BlockRayTraceResult) result;
                BlockState state = world.getBlockState(rayTraceResult.getPos());

                if (state.getBlock().getMaterial(state).isSolid()) {
                    BlockPos posup = rayTraceResult.getPos().up();
                    if(world.getBlockState(posup).getBlock() == Blocks.AIR) {
                        WorldUtil.setBlockState(world, Blocks.CAKE.getDefaultState(), posup);
                    }
                    onKillCommand();
                }
            }
        }
    }

    @Override
    protected void registerData() {

    }

    /**
     * This is required to make entity spawn
     * */
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
