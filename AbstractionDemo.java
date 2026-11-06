
interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    private String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract double calculateFee(int hours);

    public void display(int hours) {
        System.out.println(
            registrationNumber + " -> Rs " + calculateFee(hours)
        );
    }
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }
}

class ElectricCar extends Vehicle implements Chargeable {
    ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("TN01AB1234");
        ElectricCar electricCar =
            new ElectricCar("TN02CD5678");

        car.display(3);
        electricCar.display(3);

        System.out.println(
            "Charging Fee: Rs " + electricCar.chargingFee(5)
        );
    }
}
