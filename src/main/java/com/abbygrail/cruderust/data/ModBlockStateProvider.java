package com.abbygrail.cruderust.data;

import com.abbygrail.cruderust.cruderust;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;



public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, cruderust.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(cruderust.BRONZE_BLOCK);
        blockWithItem(cruderust.ARSENIC_ORE);
    }
    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
//    private void rotatedPillarBlockWithItem(DeferredBlock<RotatedPillarBlock> rotatedBlock) {
//        simpleBlockItem(rotatedBlock.get(), axisBlock(rotatedBlock.get(), name(rotatedBlock)));
//    }


}
