package travelator;

import org.junit.jupiter.api.Test;
import travelator.testing.StoppedClock;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static travelator.TripJava.BookingStatus.BOOKED;
import static travelator.TripJava.BookingStatus.NOT_BOOKED;

public class TrackingTests {

    final StoppedClock clock = new StoppedClock();

    final InMemoryTripsJava trips = new InMemoryTripsJava(clock);
    final TrackingJava tracking = new TrackingJava(trips);

    @Test
    public void returns_empty_when_no_trip_planned_to_happen_now() {
        clock.now = anInstant();
        assertEquals(
                Optional.empty(),
                tracking.currentTripFor("aCustomer", null)
        );
    }

    @Test
    public void returns_single_active_booked_trip() {
        var diwaliTrip = givenATrip("cust1", "Diwali",
                "2020-11-13", "2020-11-15", BOOKED);
        givenATrip("cust1", "Christmas",
                "2020-12-24", "2020-11-26", BOOKED);

        clock.now = diwaliTrip.getPlannedStartTime().toInstant();
        assertEquals(
                Optional.of(diwaliTrip),
                tracking.currentTripFor("cust1", null)
        );
    }

    @Test
    public void returns_only_customers_trip() {
        var diwaliTrip = givenATrip("cust1", "Diwali",
                "2020-11-13", "2020-11-15", BOOKED);
        givenATrip("aDifferentCustomer", "Diwali",
                "2020-11-13", "2020-11-15", BOOKED);

        clock.now = diwaliTrip.getPlannedStartTime().toInstant();
        assertEquals(Optional.of(diwaliTrip),
                tracking.currentTripFor("cust1", null)
        );
    }

    @Test
    public void returns_single_booked_trip() {
        var diwaliTrip = givenATrip("cust1", "Diwali",
                "2020-11-13", "2020-11-15", BOOKED);
        givenATrip("cust1", "Diwali",
                "2020-11-13", "2020-11-15", NOT_BOOKED);

        clock.now = diwaliTrip.getPlannedStartTime().toInstant();
        assertEquals(Optional.of(diwaliTrip),
                tracking.currentTripFor("cust1", null)
        );
    }

    @Test
    public void throws_when_more_than_one_simultaneous_booked_trip() {
        var diwaliTrip = givenATrip("cust1", "Diwali", "" +
                "2020-11-13", "2020-11-15", BOOKED);
        givenATrip("cust1", "Diwali",
                "2020-11-13", "2020-11-15", BOOKED);

        clock.now = diwaliTrip.getPlannedStartTime().toInstant();
        assertThrows(IllegalStateException.class,
                () -> tracking.currentTripFor("cust1", null)
        );
    }

    private TripJava givenATrip(
            String customerId,
            String name,
            String startDate,
            String endDate,
            TripJava.BookingStatus bookingStatus
    ) {
        var trip = new TripJava(
                "ignoredId",
                customerId,
                name,
                zonedDateTime(startDate + "T12:00:00+00:00[Europe/London]"),
                zonedDateTime(endDate + "T12:00:00+00:00[Europe/London]"),
                bookingStatus
        );
        trips.addTrip(trip);
        return trip;
    }

    private ZonedDateTime zonedDateTime(String s) {
        return ZonedDateTime.parse(s);
    }

    private Instant anInstant() {
        return Instant.now();
    }
}
