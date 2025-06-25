package com.capgemini;

import com.capgemini.enums.SlotSize;
import com.capgemini.enums.VehicleType;
import com.capgemini.model.ParkingSlot;
import com.capgemini.model.Vehicle;
import com.capgemini.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ParkingCommands {
    private final ParkingService parkingService;

    @Autowired
    public ParkingCommands(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @ShellMethod(value = "Create parking lot", key = "create-parking-lot")
    public String createParkingLot(
            @ShellOption(help = "Number of small slots") int smallSlots,
            @ShellOption(help = "Number of large slots") int largeSlots,
            @ShellOption(help = "Number of oversize slots") int oversizeSlots) {

        try {
            parkingService.createParkingLot(smallSlots, largeSlots, oversizeSlots);
            int totalSlots = smallSlots + largeSlots + oversizeSlots;
            return "Created parking lot with " + totalSlots + " slots (" +
                    smallSlots + " small, " + largeSlots + " large, " + oversizeSlots + " oversize)";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(value = "Park a vehicle", key = "park")
    public String parkVehicle(
            @ShellOption(help = "Vehicle license plate") String licensePlate,
            @ShellOption(help = "Vehicle type (SMALL_CAR, COMPACT_CAR, FULL_SIZE_CAR, SUV, TRUCK)")
            VehicleType vehicleType) {

        try {
            Vehicle vehicle = new Vehicle(licensePlate, vehicleType);
            ParkingSlot slot = parkingService.parkVehicle(vehicle);
            return "Parked " + vehicle + " in slot #" + slot.getId() + " (" + slot.getSize() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(value = "Remove a vehicle", key = "leave")
    public String removeVehicle(@ShellOption(help = "Vehicle license plate") String licensePlate) {
        try {
            Vehicle vehicle = parkingService.removeVehicle(licensePlate);
            return vehicle + " has left the parking lot";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(value = "Find vehicle location", key = "find")
    public String findVehicle(@ShellOption(help = "Vehicle license plate") String licensePlate) {
        try {
            ParkingSlot slot = parkingService.findVehicleSlot(licensePlate);
            if (slot == null) {
                return "Vehicle with license plate " + licensePlate + " not found in the parking lot";
            }
            return "Vehicle with license plate " + licensePlate + " is parked in slot #" + slot.getId() +
                    " (" + slot.getSize() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(value = "Show available slots", key = "available")
    public String showAvailableSlots(
            @ShellOption(help = "Slot size (SMALL, LARGE, OVERSIZE)", defaultValue = "")
            String slotSizeStr) {

        try {
            StringBuilder result = new StringBuilder();

            if (slotSizeStr.isEmpty()) {
                // Show all available slots
                result.append("Small slots available: ")
                        .append(parkingService.getAvailableSlots(SlotSize.SMALL).size()).append("\n");
                result.append("Large slots available: ")
                        .append(parkingService.getAvailableSlots(SlotSize.LARGE).size()).append("\n");
                result.append("Oversize slots available: ")
                        .append(parkingService.getAvailableSlots(SlotSize.OVERSIZE).size());
            } else {
                // Show specific slot size availability
                SlotSize slotSize = SlotSize.valueOf(slotSizeStr);
                result.append(parkingService.getAvailableSlots(slotSize).size())
                        .append(" ").append(slotSize).append(" slots available");
            }

            return result.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(value = "Show parking lot status", key = "status")
    public String showStatus() {
        try {
            return parkingService.getParkingLotStatus();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
