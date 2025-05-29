package com.abbygrail.cruderust.block.state;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum FireWoodState implements StringRepresentable {

    OFF("off"),
    LIT("lit"),
    CHARRED("charred");

    private final String fireWoodState;

    FireWoodState(String name) {
        this.fireWoodState = name;
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.fireWoodState;
    }
}
