import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Car Park that manages parking slots and cars.
 */
public class CarPark {
    private String title;
    private List<ParkingSlot> slots;

    /**
     * Constructor to initialize a CarPark with a title and an empty list of parking slots.
     * @param title The title of the car park.
     */
    public CarPark(String title) {
        this.title = title;
        this.slots = new ArrayList<>();
    }

    /**
     * Get the title of the car park.
     * @return The title of the car park.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Check if a slot ID is unique.
     * @param slotID The ID of the parking slot to check.
     * @return True if the slot ID is unique, false otherwise.
     */
    public boolean isSlotIDUnique(String slotID) {
        return findBySlotID(slotID) == null;
    }

    /**
     * Check if a parking slot is occupied.
     * @param slotID The ID of the parking slot to check.
     * @return True if the parking slot is occupied, false otherwise.
     */
    public boolean isSlotOccupied(String slotID) {
        ParkingSlot slot = findBySlotID(slotID);
        return slot != null && slot.getParkedCar() != null;
    }

    /**
     * Check if a car is already parked in the car park.
     * @param regNo The registration number of the car.
     * @return True if the car is parked, false otherwise.
     */
    public boolean isCarParked(String regNo) {
        return findByRego(regNo) != null;
    }

    /**
     * Add a parking slot to the car park.
     * @param slotID The ID of the parking slot to add.
     */
    public void addSlot(String slotID) {
        if (isValidSlotID(slotID)) {
            slots.add(new ParkingSlot(slotID));
        } else {
            throw new IllegalArgumentException("Invalid slot ID format.");
        }
    }

    /**
     * Validate the format of a parking slot ID.
     * @param slotID The ID of the parking slot.
     * @return True if the slot ID is valid, false otherwise.
     */
    private boolean isValidSlotID(String slotID) {
        return slotID.matches("[A-Z]\\d{3}");
    }

    /**
     * Remove a parking slot from the car park by its ID.
     * @param slotID The ID of the parking slot to remove.
     */
    public void removeSlot(String slotID) {
        ParkingSlot slot = findBySlotID(slotID);
        if (slot != null) {
            slots.remove(slot);
            System.out.println("Parking slot " + slotID + " is removed successfully.");
        } else {
            System.out.println("Slot ID not found.");
        }
    }

    /**
     * Display all the parking slots in the car park.
     */
    public void displayAll() {
        if (slots.isEmpty()) {
            System.out.println("The car park is empty! You can book any slot.");
        } else {
            for (ParkingSlot slot : slots) {
                System.out.println(slot);
                Car parkedCar = slot.getParkedCar();
                if (parkedCar != null) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime parkedTime = parkedCar.getParkingTime();
                    Duration duration = Duration.between(parkedTime, now);

                    long hours = duration.toHours();
                    long minutes = duration.toMinutesPart();
                    long seconds = duration.toSecondsPart();

                    System.out.println("Parked Time: " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
                }
            }
        }
    }

    /**
     * Park a car in a specific parking slot.
     * @param slotID The ID of the parking slot.
     * @param sc The Scanner object to read user input.
     * @param regNo The registration number of the car.
     */
    public void parkCar(String slotID, Scanner sc, String regNo) {
        ParkingSlot slot = findBySlotID(slotID);
        if (slot != null && slot.getParkedCar() == null) {
            System.out.println("Enter car make:");
            String make = sc.next();
            System.out.println("Enter car model:");
            String model = sc.next();
            System.out.println("Enter car year:");
            int year = sc.nextInt();
            Car car = new Car(regNo, make, model, year);
            slot.parkCar(car);
        } else {
            System.out.println("Slot ID not found or already occupied.");
        }
    }

    /**
     * Find a parking slot by its ID.
     * @param slotID The ID of the parking slot.
     * @return The ParkingSlot object if found, null otherwise.
     */
    public ParkingSlot findBySlotID(String slotID) {
        for (ParkingSlot slot : slots) {
            if (slot.getSlotID().equals(slotID)) {
                return slot;
            }
        }
        return null;
    }

    /**
     * Find a parking slot by the car's registration number.
     * @param regNo The registration number of the car.
     * @return The ParkingSlot object if found, null otherwise.
     */
    public ParkingSlot findByRego(String regNo) {
        for (ParkingSlot slot : slots) {
            if (slot.getParkedCar() != null && slot.getParkedCar().getRegNo().equals(regNo)) {
                return slot;
            }
        }
        return null;
    }

    /**
     * Remove a car from a parking slot by the car's registration number with confirmation.
     * @param regNo The registration number of the car to be removed.
     */
    public void removeByRegoWithConfirmation(String regNo) {
        ParkingSlot slot = findByRego(regNo);

        if (slot != null) {
            slot.removeCar();
            System.out.println("The car is removed! Slot " + slot.getSlotID() + " is now empty.");
        } else {
            System.out.println("Car with registration number " + regNo + " not found.");
        }
    }

    /**
     * Search for cars by their make and display their parking slots.
     * @param make The make of the cars to search for.
     */
    public void searchByMake(String make) {
        boolean found = false;
        for (ParkingSlot slot : slots) {
            Car parkedCar = slot.getParkedCar();
            if (parkedCar != null && parkedCar.getMake().equalsIgnoreCase(make)) {
                System.out.println(slot);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime parkedTime = parkedCar.getParkingTime();
                Duration duration = Duration.between(parkedTime, now);

                long hours = duration.toHours();
                long minutes = duration.toMinutesPart();
                long seconds = duration.toSecondsPart();

                System.out.println("Parked Time: " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars with make " + make + " found.");
        }
    }
}
