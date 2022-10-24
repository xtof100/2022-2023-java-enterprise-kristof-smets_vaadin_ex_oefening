package be.ucll.jpa.exercise3.onetomany;

public class Leg {

    private Long id;

    private String from;
    private String to;

    private TravelInfo travelInfo;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public TravelInfo getTravelInfo() {
        return travelInfo;
    }

    public void setTravelInfo(final TravelInfo travelInfo) {
        this.travelInfo = travelInfo;
    }
}
