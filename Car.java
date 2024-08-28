import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Car {
    private String regNo;
    private String make;
    private String model;
    private int year;
    private LocalDateTime parkingTime;

    public Car(String regNo, String make, String model, int year) {
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public static boolean isValidRego(String regNo) {
        return regNo.matches("[A-Z]{1,3}\\d{1,4}");
    }

    public String getRegNo() {
        return regNo;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(LocalDateTime parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getFormattedParkingTime() {
        if (parkingTime == null) {
            return "Unknown";
        }
        return parkingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "Car [Reg No: " + regNo + ", Make: " + make + ", Model: " + model + ", Year: " + year + "]";
    }
}
