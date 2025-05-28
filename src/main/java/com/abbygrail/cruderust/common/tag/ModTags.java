package com.abbygrail.cruderust.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import com.abbygrail.cruderust.cruderust;

public class ModTags {

    public static final TagKey<Block> BRONZE_BLOCKS = modBlockTag("bronze_blocks");
    public static final TagKey<Item> BRONZE_BLOCK_ITEMS = modItemTag("bronze_blocks");

    private static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(cruderust.MODID, path));
    }

    private static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(cruderust.MODID, path));
    }
}
