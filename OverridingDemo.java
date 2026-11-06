
class Vehicle {
    String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    double calculateFee(int minutes) {
        return 0;
    }

    void displayDetails(int minutes) {
        System.out.println(registrationNumber + " -> Rs " +
                calculateFee(minutes));
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 10.0;
    }
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 30.0;
    }
}

class ElectricCar extends Car {
    ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int minutes) {
        return super.calculateFee(minutes) + 60;
    }
}

public class OverridingDemo {
    public static void main(String[] args) {
        Vehicle bike = new TwoWheeler("TN01A1");
        Vehicle car = new Car("TN02B2");
        Vehicle electricCar = new ElectricCar("TN03C3");

        bike.displayDetails(150);
        car.displayDetails(150);
        electricCar.displayDetails(150);
    }
}
