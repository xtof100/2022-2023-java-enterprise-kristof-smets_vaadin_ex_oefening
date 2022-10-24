package be.ucll.jpa.exercise3.onetoone;

public class Vehicle {

    private Long id;

    private VehicleDetails vehicleDetails;

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(final VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
        vehicleDetails.setVehicle(this);
    }
}
