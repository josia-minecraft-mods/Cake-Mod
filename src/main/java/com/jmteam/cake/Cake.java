package com.jmteam.cake;

import com.jmteam.cake.common.init.ModEntities;
import com.jmteam.cake.common.init.ModItems;
import com.jmteam.cake.common.init.ModSounds;
import com.jmteam.cake.proxy.ClientProxy;
import com.jmteam.cake.proxy.ServerProxy;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Cake.MODID)
public class Cake {

    public static Cake INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ServerProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final String MODID = "cakemod";

    public Cake() {
        INSTANCE = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::doClientStuff);
        bothSideSetup(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void bothSideSetup(IEventBus modEventBus) {
        ModEntities.init();
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PROXY.doServerStuff(event);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        PROXY.doClientStuff(event);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            LOGGER.info("Registering Items");
            event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[ModItems.ITEMS.size()]));
        }

        @SubscribeEvent
        public static void onEntityRegistryEvent(RegistryEvent.Register<EntityType<?>> event) {
            LOGGER.info("Registering Entities");
            event.getRegistry().registerAll(ModEntities.ENTITY_TYPES.toArray(new EntityType[ModEntities.ENTITY_TYPES.size()]));
        }

        @SubscribeEvent
        public static void onSoundRegistryEvent(RegistryEvent.Register<SoundEvent> event) {
            LOGGER.info("Registering Sounds");
            event.getRegistry().registerAll(ModSounds.SOUNDS.toArray(new SoundEvent[ModSounds.SOUNDS.size()]));
        }
    }
}
