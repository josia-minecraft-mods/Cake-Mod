package com.jmteam.cake.util.registry;

import com.jmteam.cake.common.item.InfinityItemBlock;
import com.jmteam.cake.util.helpers.ReflectionHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class RegistryHelper {

    /* Use reflection to set Creative Tab after initialize */
    public static Item setCreativeTab(Item i, ItemGroup group) {
        if (i != null) {

            if(i instanceof InfinityItemBlock) {
                ((InfinityItemBlock) i).setGroup(group);
                return i;
            }

            if(i instanceof BlockItem) {
                BlockItem item = (BlockItem) i;
                ReflectionHelper.setValuePrivateDeclaredField("group", ReflectionHelper.getClassFromSuperClasses(item, Item.class), item, group);
            }
        }

        return i;
    }

}
