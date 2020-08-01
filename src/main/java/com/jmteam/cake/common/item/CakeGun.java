package com.jmteam.cake.common.item;

import com.jmteam.cake.common.entity.EntityCake;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CakeGun extends ItemBase {
    public float damage;

    public CakeGun(float damage) {
        this.damage = damage;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setActiveHand(handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);

        EntityCake laser = new EntityCake(worldIn, entityLiving, entityLiving.getPosition().add(0, entityLiving.getEyeHeight(), 0), damage);
        Vec3d vec3d = entityLiving.getLookVec();
        laser.shoot(vec3d.x + 0.05, vec3d.y + 0.005f, vec3d.z + 0.05, 1.5F, 0F);
        worldIn.addEntity(laser);
    }
}
