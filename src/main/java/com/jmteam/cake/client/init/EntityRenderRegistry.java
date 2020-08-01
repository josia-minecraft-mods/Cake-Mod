package com.jmteam.cake.client.init;

import com.jmteam.cake.Cake;
import com.jmteam.cake.client.render.entity.RenderCake;
import com.jmteam.cake.common.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRenderRegistry {

    @SuppressWarnings("unchecked")
    public static void registryEntityRenders() {
        Cake.LOGGER.info("Registering Entity Renders");
        registerRender(ModEntities.CAKE, RenderCake::new);
    }

    public static <T extends Entity> void registerRender(EntityType<T> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}
