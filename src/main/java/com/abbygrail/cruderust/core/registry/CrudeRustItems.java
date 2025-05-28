package com.abbygrail.cruderust.core.registry;

import com.abbygrail.cruderust.cruderust;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.abbygrail.cruderust.cruderust.ITEMS;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class CrudeRustItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(cruderust.MODID);

    public static final DeferredItem<Item> SLAG = ITEMS.register("slag",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.registerSimpleItem("bronze_ingot");
    public static final DeferredItem<Item> BRONZE_NUGGET = ITEMS.registerSimpleItem("bronze_nugget");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}