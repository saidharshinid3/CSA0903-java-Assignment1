
import java.util.*;

abstract class Vehicle {
    private String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract double calculateFee(int minutes);
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String number) {
        super(number);
    }

    @Override
    public double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 10.0;
    }
}

class Car extends Vehicle {
    public Car(String number) {
        super(number);
    }

    @Override
    public double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 30.0;
    }
}

interface Chargeable {
    double chargingFee(int kWh);
}

class ElectricCar extends Car implements Chargeable {
    public ElectricCar(String number) {
        super(number);
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }
}

class ParkingTicket {
    private static int nextTicketId = 1;

    private int ticketId;
    private Vehicle vehicle;
    private int floor;
    private int slot;

    public ParkingTicket(Vehicle vehicle, int floor, int slot) {
        this.ticketId = nextTicketId++;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlot() {
        return slot;
    }

    public int getTicketId() {
        return ticketId;
    }
}

class ParkingFacility {
    private boolean[][] slots;
    private Map<Integer, ParkingTicket> tickets = new HashMap<>();

    public ParkingFacility(int floors, int slotsPerFloor) {
        slots = new boolean[floors][slotsPerFloor];
    }

    public ParkingTicket park(Vehicle vehicle) {
        for (int i = 0; i < slots.length; i++) {
            for (int j = 0; j < slots[i].length; j++) {
                if (!slots[i][j]) {
                    slots[i][j] = true;

                    ParkingTicket ticket =
                        new ParkingTicket(vehicle, i, j);

                    tickets.put(ticket.getTicketId(), ticket);
                    return ticket;
                }
            }
        }
        return null;
    }

    public double exit(int ticketId, int minutes, int kWh) {
        ParkingTicket ticket = tickets.remove(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket");
        }

        slots[ticket.getFloor()][ticket.getSlot()] = false;

        Vehicle vehicle = ticket.getVehicle();
        double fee = vehicle.calculateFee(minutes);

        if (vehicle instanceof Chargeable) {
            fee += ((Chargeable) vehicle).chargingFee(kWh);
        }

        return fee;
    }

    public void displaySlots() {
        for (int i = 0; i < slots.length; i++) {
            System.out.println(
                "Floor " + (i + 1) + ": " +
                Arrays.toString(slots[i])
            );
        }
    }
}

public class SmartParkingSystem {
    public static void main(String[] args) {
        ParkingFacility facility = new ParkingFacility(2, 3);

        Vehicle bike = new TwoWheeler("TN01A1");
        Vehicle car = new Car("TN02B2");
        Vehicle electricCar = new ElectricCar("TN03C3");

        ParkingTicket t1 = facility.park(bike);
        ParkingTicket t2 = facility.park(car);
        ParkingTicket t3 = facility.park(electricCar);

        facility.displaySlots();

        System.out.println(
            "Bike Fee: Rs " + facility.exit(t1.getTicketId(), 150, 0)
        );

        System.out.println(
            "Car Fee: Rs " + facility.exit(t2.getTicketId(), 150, 0)
        );

        System.out.println(
            "Electric Car Fee: Rs " +
            facility.exit(t3.getTicketId(), 150, 10)
        );

        facility.displaySlots();
    }
}
