
import parking.model.Vehicle;
import parking.service.ParkingService;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("TN01AB1234", "Car");

        ParkingService service = new ParkingService();
        service.parkVehicle(vehicle);

        vehicle.setVehicleType("Electric Car");

        System.out.println(
            "Updated Type: " + vehicle.getVehicleType()
        );
    }
}
