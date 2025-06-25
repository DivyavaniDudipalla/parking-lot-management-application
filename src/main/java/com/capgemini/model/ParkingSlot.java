package com.capgemini.model;

import com.capgemini.enums.SlotSize;
import lombok.Data;

@Data
public class ParkingSlot {
        private final int id;
        private final SlotSize size;
        private Vehicle vehicle;
        public boolean isOccupied() {
                return vehicle != null;
        }
        public void parkVehicle(Vehicle vehicle) {
                this.vehicle = vehicle;
        }

        public Vehicle removeVehicle() {
                Vehicle removedVehicle = this.vehicle;
                this.vehicle = null;
                return removedVehicle;
        }

        @Override
        public String toString() {
                return "ParkingSlot{" +
                        "id=" + id +
                        ", size=" + size +
                        ", vehicle=" + vehicle +
                        '}';
        }
}
