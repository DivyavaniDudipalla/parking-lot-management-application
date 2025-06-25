package com.capgemini.service;

import com.capgemini.enums.SlotSize;
import com.capgemini.model.ParkingSlot;
import com.capgemini.model.Vehicle;
import com.capgemini.util.ParkingLot;

import java.util.List;

public interface ParkingService {
    void createParkingLot(int smallSlots, int largeSlots, int oversizeSlots);
    ParkingSlot parkVehicle(Vehicle vehicle);
    Vehicle removeVehicle(String licensePlate);
    ParkingLot getParkingLot();
    List<ParkingSlot> getAllSlots();
    List<ParkingSlot> getAvailableSlots(SlotSize slotSize);
    ParkingSlot findVehicleSlot(String licensePlate);
    String getParkingLotStatus();
}
