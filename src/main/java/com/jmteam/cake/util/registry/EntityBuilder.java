package com.jmteam.cake.util.registry;

import com.jmteam.cake.Cake;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;

/**
 * Does Entity Creation but simplified
 * Made by Josia50
 */

public class EntityBuilder {

    private String name = "";
    private ResourceLocation id;
    private EntityType.Builder<? extends Entity> builder;
    private EntityClassification classification = EntityClassification.AMBIENT;
    private EntityType.IFactory<?> factoryIn;
    private Vec2f size;

    public static EntityBuilder create() {
        return new EntityBuilder();
    }

    public EntityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public <T extends Entity> EntityBuilder entity(EntityType.IFactory<T> factoryIn, EntityClassification classification) {
        this.factoryIn = factoryIn;
        this.classification = classification;
        create(factoryIn, classification);
        return this;
    }


    public <T extends Entity> EntityBuilder create(EntityType.IFactory<T> factoryIn, EntityClassification classificationIn) {
        this.builder = EntityType.Builder.create(factoryIn, classificationIn);
        return this;
    }

    public EntityBuilder setId(ResourceLocation location) {
        this.id = location;
        return this;
    }

    public EntityBuilder setClassification(EntityClassification classification) {
        this.classification = classification;
        return this;
    }

    public EntityBuilder size(float width, float height) {
        this.size = new Vec2f(width, height);
        builder.size(width, height);
        return this;
    }

    public <T extends Entity> EntityType<T> build() {
        if (factoryIn == null || classification == null) {
            Cake.LOGGER.warn("[Incorrect Builder]" + "Couldn't register entity :" + name);
            return null;
        }

        EntityType<? extends Entity> EntityType = builder.build(id.toString());
        EntityType.setRegistryName(id);

        return (EntityType<T>) EntityType;
    }
}
