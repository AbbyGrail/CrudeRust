package com.abbygrail.cruderust.data;

import com.abbygrail.cruderust.core.registry.CrudeRustBlocks;
import com.abbygrail.cruderust.cruderust;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, cruderust.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(cruderust.BRONZE_BLOCK.get())
                .add(cruderust.ARSENIC_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(cruderust.BRONZE_BLOCK.get())
                .add(cruderust.ARSENIC_ORE.get());

    }
}
