package travelator;

import java.time.Instant;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static travelator.TripJava.BookingStatus.BOOKED;

public class TrackingJava implements ITrackTrips {
    private final TripsJava trips;

    public TrackingJava(TripsJava trips) {
        this.trips = trips;
    }

    @Override
    public Optional<TripJava> currentTripFor(String customerId, Instant at) {
        var candidates = trips.currentTripsFor(customerId, null).stream()
                .filter((trip) -> trip.getBookingStatus() == BOOKED)
                .collect(toList());
        if (candidates.size() == 1)
            return Optional.of(candidates.get(0));
        else if (candidates.size() == 0)
            return Optional.empty();
        else
            throw new IllegalStateException(
                    "Unexpectedly more than one current trip for " + customerId
            );
    }
}
