package com.capgemini.enums;

public enum VehicleType {
    SMALL_CAR(SlotSize.SMALL),
    COMPACT_CAR(SlotSize.SMALL),
    FULL_SIZE_CAR(SlotSize.LARGE),
    SUV(SlotSize.OVERSIZE),
    TRUCK(SlotSize.OVERSIZE);

    private final SlotSize minimumSlotSize;

    VehicleType(SlotSize minimumSlotSize) {
        this.minimumSlotSize = minimumSlotSize;
    }

    public SlotSize getMinimumSlotSize() {
        return minimumSlotSize;
    }
}
