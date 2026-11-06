
import java.time.LocalTime;
import java.util.*;

abstract class Vehicle {
    private String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract double calculateFee(
        int minutes, LocalTime entryTime
    );

    protected int hours(int minutes) {
        return (minutes + 59) / 60;
    }
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String number) {
        super(number);
    }

    @Override
    public double calculateFee(int minutes, LocalTime entryTime) {
        return hours(minutes) * 10.0;
    }
}

class Car extends Vehicle {
    public Car(String number) {
        super(number);
    }

    @Override
    public double calculateFee(int minutes, LocalTime entryTime) {
        return hours(minutes) * 30.0;
    }
}

class EBike extends Vehicle {
    public EBike(String number) {
        super(number);
    }

    @Override
    public double calculateFee(int minutes, LocalTime entryTime) {
        return 20.0;
    }
}

class DeliveryVan extends Car {
    public DeliveryVan(String number) {
        super(number);
    }

    @Override
    public double calculateFee(int minutes, LocalTime entryTime) {
        double fee = super.calculateFee(minutes, entryTime);

        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);

        if (!entryTime.isBefore(start) && entryTime.isBefore(end)) {
            return fee * 2;
        }

        return fee;
    }
}

class VisitorPass {
    private String visitorName;
    private int validDay;

    public VisitorPass(String visitorName, int validDay) {
        this.visitorName = visitorName;
        this.validDay = validDay;
    }

    public boolean isValid(int currentDay) {
        return currentDay == validDay;
    }

    public String getVisitorName() {
        return visitorName;
    }
}

class ParkingFacility {
    private boolean[][] slots;

    public ParkingFacility(int[] floorCapacities) {
        slots = new boolean[floorCapacities.length][];

        for (int i = 0; i < floorCapacities.length; i++) {
            slots[i] = new boolean[floorCapacities[i]];
        }
    }

    public int[] park() {
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (!slots[floor][slot]) {
                    slots[floor][slot] = true;
                    return new int[]{floor, slot};
                }
            }
        }
        return null;
    }

    public void release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length ||
            slot < 0 || slot >= slots[floor].length) {
            throw new IllegalArgumentException("Invalid parking slot");
        }

        slots[floor][slot] = false;
    }

    public void display() {
        for (int i = 0; i < slots.length; i++) {
            System.out.println(
                "Floor " + (i + 1) + ": " +
                Arrays.toString(slots[i])
            );
        }
    }
}

public class ExtendedParkingSystem {
    public static void main(String[] args) {
        int[] capacities = {2, 3, 4, 2, 5};

        ParkingFacility facility =
            new ParkingFacility(capacities);

        Vehicle eBike = new EBike("TN01EB100");
        Vehicle van = new DeliveryVan("TN02DV200");
        Vehicle car = new Car("TN03CA300");

        int[] slot1 = facility.park();
        int[] slot2 = facility.park();
        int[] slot3 = facility.park();

        System.out.println("E-Bike Slot: " +
            Arrays.toString(slot1));
        System.out.println("Van Slot: " +
            Arrays.toString(slot2));
        System.out.println("Car Slot: " +
            Arrays.toString(slot3));

        System.out.println("E-Bike Fee: Rs " +
            eBike.calculateFee(120, LocalTime.of(10, 0)));

        System.out.println("Delivery Van Fee: Rs " +
            van.calculateFee(120, LocalTime.of(11, 0)));

        System.out.println("Car Fee: Rs " +
            car.calculateFee(120, LocalTime.of(18, 0)));

        VisitorPass pass = new VisitorPass("Student Visitor", 15);

        System.out.println(
            "Visitor Pass Valid: " + pass.isValid(15)
        );

        facility.display();

        facility.release(slot1[0], slot1[1]);

        System.out.println("After E-Bike Exit:");
        facility.display();
    }
}
