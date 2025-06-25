package com.capgemini.util;

import com.capgemini.enums.SlotSize;
import com.capgemini.model.ParkingSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private final Map<String, ParkingSlot> occupiedSlots; // License plate to slot mapping

    public ParkingLot(int smallSlots, int largeSlots, int oversizeSlots) {
        slots = new ArrayList<>(smallSlots + largeSlots + oversizeSlots);
        occupiedSlots = new HashMap<>();

        int slotId = 1;

        // Create small slots
        for (int i = 0; i < smallSlots; i++) {
            slots.add(new ParkingSlot(slotId++, SlotSize.SMALL));
        }

        // Create large slots
        for (int i = 0; i < largeSlots; i++) {
            slots.add(new ParkingSlot(slotId++, SlotSize.LARGE));
        }

        // Create oversize slots
        for (int i = 0; i < oversizeSlots; i++) {
            slots.add(new ParkingSlot(slotId++, SlotSize.OVERSIZE));
        }
    }

    public List<ParkingSlot> getAllSlots() {
        return new ArrayList<>(slots);
    }

    public List<ParkingSlot> getAvailableSlots(SlotSize slotSize) {
        List<ParkingSlot> availableSlots = new ArrayList<>();

        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && slot.getSize() == slotSize) {
                availableSlots.add(slot);
            }
        }

        return availableSlots;
    }

    public ParkingSlot findVehicleSlot(String licensePlate) {
        return occupiedSlots.get(licensePlate);
    }

    public void registerOccupiedSlot(String licensePlate, ParkingSlot slot) {
        occupiedSlots.put(licensePlate, slot);
    }

    public void unregisterOccupiedSlot(String licensePlate) {
        occupiedSlots.remove(licensePlate);
    }

    public int getTotalSlots() {
        return slots.size();
    }

    public int getOccupiedSlots() {
        return occupiedSlots.size();
    }

    public int getAvailableSlots() {
        return getTotalSlots() - getOccupiedSlots();
    }
}
