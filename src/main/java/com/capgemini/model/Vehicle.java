package com.capgemini.model;
import com.capgemini.enums.VehicleType;
import lombok.Data;

@Data
public class Vehicle {
        private final String licensePlate;
        private final VehicleType vehicleType;

        @Override
        public String toString() {
                return vehicleType + " [" + licensePlate + "]";
        }
}

