import java.time.LocalDateTime;

/**
 * Represents a parking slot in a car park.
 */
public class ParkingSlot {
    private String slotID;
    private Car parkedCar;

    /**
     * Constructor to create a ParkingSlot with a specified slot ID.
     * @param slotID The ID of the parking slot, must start with a capital letter followed by three digits.
     * @throws IllegalArgumentException If the slot ID format is invalid.
     */
    public ParkingSlot(String slotID) {
        if (isValidSlotID(slotID)) {
            this.slotID = slotID;
            this.parkedCar = null; // Initially no car parked
        } else {
            throw new IllegalArgumentException("Invalid slot ID format. It must start with a capital letter followed by a three-digit number, e.g., D001.");
        }
    }

    /**
     * Validates the slot ID format.
     * @param slotID The slot ID to validate.
     * @return True if the slot ID is valid, false otherwise.
     */
    private boolean isValidSlotID(String slotID) {
        return slotID.matches("[A-Z]\\d{3}");
    }

    /**
     * Get the slot ID of the parking slot.
     * @return The slot ID.
     */
    public String getSlotID() {
        return slotID;
    }

    /**
     * Get the car parked in the slot.
     * @return The Car object parked in the slot, or null if no car is parked.
     */
    public Car getParkedCar() {
        return parkedCar;
    }

    /**
     * Park a car in the slot.
     * @param car The Car object to park.
     */
    public void parkCar(Car car) {
        if (this.parkedCar == null) {
            this.parkedCar = car;
            car.setParkingTime(LocalDateTime.now()); // Record current time
            System.out.println("Car parked successfully at " + car.getFormattedParkingTime());
        } else {
            System.out.println("Slot " + slotID + " is already occupied.");
        }
    }

    /**
     * Remove a car from the slot.
     */
    public void removeCar() {
        if (this.parkedCar != null) {
            this.parkedCar = null;
            System.out.println("Car removed from slot " + slotID);
        } else {
            System.out.println("Slot " + slotID + " is already empty.");
        }
    }

    /**
     * String representation of the ParkingSlot object.
     * @return A string representation indicating whether the slot is parked or available.
     */
    @Override
    public String toString() {
        if (parkedCar != null) {
            return "Slot ID: " + slotID + ", Parked | Car Info: " + parkedCar.toString();
        } else {
            return "Slot ID: " + slotID + ", Available for parking.";
        }
    }
}


