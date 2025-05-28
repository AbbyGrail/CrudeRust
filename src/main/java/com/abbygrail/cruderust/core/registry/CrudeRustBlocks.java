package com.abbygrail.cruderust.core.registry;

import com.abbygrail.cruderust.cruderust;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes.HELPER;

public class CrudeRustBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(cruderust.MODID);


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
