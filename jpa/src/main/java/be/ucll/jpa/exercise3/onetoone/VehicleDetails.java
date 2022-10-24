package be.ucll.jpa.exercise3.onetoone;

public class VehicleDetails {

    private Long id;

    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
