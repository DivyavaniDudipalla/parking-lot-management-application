# 🐚 Parking Lot Management - Shell Commands Documentation

## 📦 Component: ParkingCommands

This class provides CLI-based commands for interacting with the Parking Lot Management System using Spring Shell.

---

## 📌 Commands

### 1. 🏗️ create-parking-lot

- **Command:** `create-parking-lot`
- **Description:** Initializes a parking lot with given slot counts.

#### 🔸 Parameters
| Name         | Type | Description            |
|--------------|------|------------------------|
| smallSlots   | int  | Number of small slots  |
| largeSlots   | int  | Number of large slots  |
| oversizeSlots| int  | Number of oversize slots |

#### ✅ Example
```
create-parking-lot 10 8 5 --smallSlots 10 --largeSlots 8 --oversizeSlots 5
```

---

### 2. 🅿️ park

- **Command:** `park`
- **Description:** Parks a vehicle into an appropriate slot.

#### 🔸 Parameters
| Name           | Type   | Description         |
|----------------|--------|---------------------|
| licensePlate   | String | License plate       |
| vehicleType    | Enum   | SMALL_CAR, COMPACT_CAR, FULL_SIZE_CAR, SUV, TRUCK |

#### ✅ Example
```
park ABC123 SMALL_CAR
park XYZ789 SUV --licensePlate XYZ789 --vehicleType SUV
```

---

### 3. 🚗 leave

- **Command:** `leave`
- **Description:** Removes a vehicle based on the license plate.

#### 🔸 Parameters
| Name         | Type   | Description        |
|--------------|--------|--------------------|
| licensePlate | String | Vehicle license    |

#### ✅ Example
```
leave ABC123--licensePlate ABC123
```

---

### 4. 🔍 find

- **Command:** `find`
- **Description:** Finds the slot of a parked vehicle.

#### 🔸 Parameters
| Name         | Type   | Description        |
|--------------|--------|--------------------|
| licensePlate | String | Vehicle license    |

#### ✅ Example
```
find ABC123--licensePlate ABC123
```

---

### 5. 📊 available

- **Command:** `available`
- **Description:** Shows available parking slots.

#### 🔸 Optional Parameter
| Name         | Type   | Description            |
|--------------|--------|------------------------|
| slotSizeStr  | String | SMALL, LARGE, OVERSIZE |

#### ✅ Examples
```
available
available SMALL--slotSizeStr SMALL
```

---

### 6. 📋 status

- **Command:** `status`
- **Description:** Shows current status of parking lot.

#### ✅ Example
```
status
```

---

## 📘 Enum Reference

### VehicleType
- SMALL_CAR
- COMPACT_CAR
- FULL_SIZE_CAR
- SUV
- TRUCK

### SlotSize
- SMALL
- LARGE
- OVERSIZE

---

## 🧪 Usage

Use these commands inside the Spring Shell CLI environment after starting your Spring Boot application.
