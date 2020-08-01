package com.jmteam.cake.common.damage;

import com.jmteam.cake.Cake;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ISource extends DamageSource {

    private String message;
    private boolean blockable;

    public ISource(String name, boolean blockable) {
        super(name);
        this.message = "damagesource." + Cake.MODID + "." + name;
        this.blockable = blockable;
    }

    public ISource(String name) {
        this(name, false);
    }

    @Override
    public ITextComponent getDeathMessage(LivingEntity entity) {
        return new TranslationTextComponent(message, entity.getName());
    }

    @Override
    public boolean isUnblockable() {
        return !blockable;
    }
}
