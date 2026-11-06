class Vehicle {

    protected String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void display() {
        System.out.println("Registration: " + registrationNumber);
    }
}

class TwoWheeler extends Vehicle {

    TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }
}

class Car extends Vehicle {

    Car(String registrationNumber) {
        super(registrationNumber);
    }
}

class ElectricCar extends Car {

    ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }
}

public class VehicleDemo {

    public static void main(String[] args) {

        TwoWheeler bike = new TwoWheeler("TN01AB1111");
        Car car = new Car("TN02CD2222");
        ElectricCar ev = new ElectricCar("TN03EF3333");

        bike.display();
        car.display();
        ev.display();
    }
}
