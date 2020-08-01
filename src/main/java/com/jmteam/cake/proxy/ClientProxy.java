package com.jmteam.cake.proxy;

import com.jmteam.cake.client.init.EntityRenderRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends ServerProxy {

    @Override
    public void doClientStuff(FMLClientSetupEvent event) {
        EntityRenderRegistry.registryEntityRenders();
    }
}
