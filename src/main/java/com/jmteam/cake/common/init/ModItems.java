package com.jmteam.cake.common.init;

import com.jmteam.cake.common.item.CakeGun;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static List<Item> ITEMS = new ArrayList<>();

    public static Item cake_gun = addItem(new CakeGun(1.5f), "cake_gun").setGroup(ItemGroup.COMBAT);

    public static <T extends Item> T addItem(T item, String name) {
        item.setRegistryName(name);
        ITEMS.add(item);

        return item;
    }
}
