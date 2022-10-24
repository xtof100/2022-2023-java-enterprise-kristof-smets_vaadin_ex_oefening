package be.ucll.jpa.exercise3.onetomany;

import java.util.List;

public class Itinerary {

    private Long id;

    private String cityOfDeparture;
    private String cityOfDestination;

    private List<Leg> legs;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setCityOfDeparture(final String cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public String getCityOfDestination() {
        return cityOfDestination;
    }

    public void setCityOfDestination(final String cityOfDestination) {
        this.cityOfDestination = cityOfDestination;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(final List<Leg> legs) {
        this.legs = legs;
    }
}
