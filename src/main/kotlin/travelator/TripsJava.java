package travelator;

import java.time.Instant;
import java.util.Set;

public interface TripsJava {
    Set<TripJava> tripsFor(String customerId);
    Set<TripJava> currentTripsFor(String customerId, Instant at);
}
