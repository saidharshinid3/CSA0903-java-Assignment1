
import java.util.Objects;

interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    protected String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    abstract double calculateFee(int minutes);

    protected int billedHours(int minutes) {
        return (minutes + 59) / 60;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;

        Vehicle other = (Vehicle) obj;
        return Objects.equals(
            registrationNumber, other.registrationNumber
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(registrationNumber);
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int minutes) {
        return billedHours(minutes) * 10.0;
    }
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int minutes) {
        return billedHours(minutes) * 30.0;
    }
}

class ElectricCar extends Car implements Chargeable {
    ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }
}

public class FeeDemo {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };

        int minutes = 150;

        for (Vehicle vehicle : vehicles) {
            double fee = vehicle.calculateFee(minutes);

            if (vehicle instanceof Chargeable) {
                fee += ((Chargeable) vehicle).chargingFee(10);
            }

            System.out.println(
                vehicle.getClass().getSimpleName() + " " +
                vehicle.registrationNumber + " -> Rs " + fee
            );
        }

        System.out.println(
            new Car("TN02B2").equals(
                new ElectricCar("TN02B2")
            )
        );
    }
}
