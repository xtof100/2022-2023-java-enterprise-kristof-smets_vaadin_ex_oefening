package be.ucll.jpa.exercise3.onetomany;

public class TravelInfo {

    private Long id;

    private VehicleType vehicleType;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(final VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
