package com.capgemini.service;

import com.capgemini.enums.SlotSize;
import com.capgemini.model.ParkingSlot;
import com.capgemini.model.Vehicle;
import com.capgemini.util.ParkingLot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private ParkingLot parkingLot;

    @Override
    public void createParkingLot(int smallSlots, int largeSlots, int oversizeSlots) {
        parkingLot = new ParkingLot(smallSlots, largeSlots, oversizeSlots);
    }

    @Override
    public ParkingSlot parkVehicle(Vehicle vehicle) {
        if (parkingLot == null) {
            throw new IllegalStateException("Parking lot has not been created");
        }

        // Check if vehicle already parked
        ParkingSlot existingSlot = parkingLot.findVehicleSlot(vehicle.getLicensePlate());
        if (existingSlot != null) {
            throw new IllegalStateException("Vehicle with license plate " +
                    vehicle.getLicensePlate() + " already parked in slot #" +
                    existingSlot.getId());
        }

        // Get minimum size needed for vehicle
        SlotSize requiredSize = vehicle.getVehicleType().getMinimumSlotSize();

        // Try to find suitable slot based on vehicle size
        ParkingSlot selectedSlot = null;

        // First try exact size
        List<ParkingSlot> exactSizeSlots = parkingLot.getAvailableSlots(requiredSize);
        if (!exactSizeSlots.isEmpty()) {
            selectedSlot = exactSizeSlots.get(0);
        }
        // If no exact size available, try larger slots in order
        else {
            switch (requiredSize) {
                case SMALL:
                    List<ParkingSlot> largeSlots = parkingLot.getAvailableSlots(SlotSize.LARGE);
                    if (!largeSlots.isEmpty()) {
                        selectedSlot = largeSlots.get(0);
                    } else {
                        List<ParkingSlot> oversizeSlots = parkingLot.getAvailableSlots(SlotSize.OVERSIZE);
                        if (!oversizeSlots.isEmpty()) {
                            selectedSlot = oversizeSlots.get(0);
                        }
                    }
                    break;
                case LARGE:
                    List<ParkingSlot> oversizeSlots = parkingLot.getAvailableSlots(SlotSize.OVERSIZE);
                    if (!oversizeSlots.isEmpty()) {
                        selectedSlot = oversizeSlots.get(0);
                    }
                    break;
                default:
                    // No fallback for OVERSIZE
                    break;
            }
        }

        if (selectedSlot == null) {
            throw new IllegalStateException("No available slot for " + vehicle.getVehicleType());
        }

        // Park vehicle in selected slot
        selectedSlot.parkVehicle(vehicle);
        parkingLot.registerOccupiedSlot(vehicle.getLicensePlate(), selectedSlot);

        return selectedSlot;
    }

    @Override
    public Vehicle removeVehicle(String licensePlate) {
        if (parkingLot == null) {
            throw new IllegalStateException("Parking lot has not been created");
        }

        ParkingSlot slot = parkingLot.findVehicleSlot(licensePlate);
        if (slot == null) {
            throw new IllegalArgumentException("No vehicle found with license plate: " + licensePlate);
        }

        Vehicle vehicle = slot.removeVehicle();
        parkingLot.unregisterOccupiedSlot(licensePlate);

        return vehicle;
    }

    @Override
    public ParkingLot getParkingLot() {
        if (parkingLot == null) {
            throw new IllegalStateException("Parking lot has not been created");
        }
        return parkingLot;
    }

    @Override
    public List<ParkingSlot> getAllSlots() {
        if (parkingLot == null) {
            throw new IllegalStateException("Parking lot has not been created");
        }
        return parkingLot.getAllSlots();
    }

    @Override
    public List<ParkingSlot> getAvailableSlots(SlotSize slotSize) {
        if (parkingLot == null) {
            throw new IllegalStateException("Parking lot has not been created");
        }
        return parkingLot.getAvailableSlots(slotSize);
    }

    @Override
    public ParkingSlot findVehicleSlot(String licensePlate) {
        if (parkingLot == null) {
            throw new IllegalStateException("Parking lot has not been created");
        }
        return parkingLot.findVehicleSlot(licensePlate);
    }

    @Override
    public String getParkingLotStatus() {
        if (parkingLot == null) {
            return "Parking lot has not been created";
        }

        StringBuilder status = new StringBuilder();
        status.append("Parking Lot Status\n");
        status.append("-----------------\n");
        status.append("Total slots: ").append(parkingLot.getTotalSlots()).append("\n");
        status.append("Occupied slots: ").append(parkingLot.getOccupiedSlots()).append("\n");
        status.append("Available slots: ").append(parkingLot.getAvailableSlots()).append("\n\n");

        status.append("Small slots available: ")
                .append(parkingLot.getAvailableSlots(SlotSize.SMALL).size()).append("\n");
        status.append("Large slots available: ")
                .append(parkingLot.getAvailableSlots(SlotSize.LARGE).size()).append("\n");
        status.append("Oversize slots available: ")
                .append(parkingLot.getAvailableSlots(SlotSize.OVERSIZE).size()).append("\n\n");

        status.append("Detailed Slot Information:\n");
        for (ParkingSlot slot : parkingLot.getAllSlots()) {
            status.append(slot.toString()).append("\n");
        }

        return status.toString();
    }
}
