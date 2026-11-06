
package parking.service;

import parking.model.Vehicle;

public class ParkingService {
    public void parkVehicle(Vehicle vehicle) {
        System.out.println(
            "Vehicle Parked: " + vehicle.getRegistrationNumber()
        );
        System.out.println(
            "Vehicle Type: " + vehicle.getVehicleType()
        );
    }
}
