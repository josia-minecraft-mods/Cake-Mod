package com.jmteam.cake.common.init;

import com.jmteam.cake.Cake;
import com.jmteam.cake.common.entity.EntityCake;
import com.jmteam.cake.util.registry.EntityBuilder;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModEntities {

    public static List<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

    public static EntityType<EntityCake> CAKE;

    public static void init() {

        EntityType<?>[] entityTypes = new EntityType[]{
                CAKE = create("gauntlet").<EntityCake>entity(EntityCake::new, EntityClassification.AMBIENT).size(1, 1).build()
        };

        ENTITY_TYPES.addAll(Arrays.asList(entityTypes));
    }

    public static EntityBuilder create(String name) {
        EntityBuilder builder = EntityBuilder.create();
        builder.setName(name);
        builder.setId(new ResourceLocation(Cake.MODID, name));
        return builder;
    }
}
