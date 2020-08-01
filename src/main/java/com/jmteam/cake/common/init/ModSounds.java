package com.jmteam.cake.common.init;

import com.jmteam.cake.Cake;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.List;

public class ModSounds {

    public static List<SoundEvent> SOUNDS = new ArrayList<>();



    /* Register Sound with name that is defined in sounds.json */
    private static SoundEvent addSound(String name) {
        ResourceLocation location = new ResourceLocation(Cake.MODID, name);
        SoundEvent soundEvent = new SoundEvent(location).setRegistryName(location);
        SOUNDS.add(soundEvent);
        return soundEvent;
    }
}
