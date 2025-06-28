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
create-parking-lot --smallSlots 3 --largeSlots 2 --oversizeSlots 1
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
park --licensePlate MH12AB1234 --vehicleType TRUCK
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
leave --licensePlate MH12AB1234
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
find --licensePlate MH12AB1234
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
available --slotSizeStr SMALL
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