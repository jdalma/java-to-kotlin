package travelator;

import java.time.Instant;
import java.util.Optional;

public interface ITrackTrips {

    Optional<TripJava> currentTripFor(String customerId, Instant at);
}
