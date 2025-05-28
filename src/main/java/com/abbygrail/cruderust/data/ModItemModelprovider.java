package com.abbygrail.cruderust.data;

import com.abbygrail.cruderust.core.registry.CrudeRustItems;
import com.abbygrail.cruderust.cruderust;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelprovider extends ItemModelProvider {
    public ModItemModelprovider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, cruderust.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(CrudeRustItems.BRONZE_INGOT.get());
        basicItem(CrudeRustItems.BRONZE_NUGGET.get());
        basicItem(CrudeRustItems.SLAG.get());
        basicItem(cruderust.FIREWOOD_ITEM.get());
    }
}
