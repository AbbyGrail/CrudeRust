package com.abbygrail.cruderust.data;

import com.abbygrail.cruderust.cruderust;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;

import java.util.Set;

import static com.abbygrail.cruderust.cruderust.FIREWOOD;
import static com.abbygrail.cruderust.cruderust.FIREWOOD_ITEM;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(cruderust.BRONZE_BLOCK.get());
        dropSelf(cruderust.ARSENIC_ORE.get());
        this.add(cruderust.FIREWOOD.get(), createSlabItemTable(cruderust.FIREWOOD.get()));




    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return cruderust.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
