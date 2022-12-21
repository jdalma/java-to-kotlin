package travelator.collections;

import travelator.collections.LocationJava;

import java.time.Duration;
import java.time.ZonedDateTime;

public class JourneyJava {

    private final LocationJava departsFrom;
    private final LocationJava arrivesAt;
    private final ZonedDateTime departureTime;
    private final ZonedDateTime arrivalTime;

    public JourneyJava(LocationJava departsFrom, LocationJava arrivesAt, ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
        this.departsFrom = departsFrom;
        this.arrivesAt = arrivesAt;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public LocationJava getDepartsFrom() {
        return departsFrom;
    }

    public LocationJava getArrivesAt() {
        return arrivesAt;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Duration getDuration() {
        return Duration.between(departureTime, arrivalTime);
    }
}
