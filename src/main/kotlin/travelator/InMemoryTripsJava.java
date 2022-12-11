package travelator;

import java.time.Clock;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class InMemoryTripsJava implements TripsJava {
    private final Clock clock;
    private final Map<String, Set<TripJava>> trips = new HashMap<>();

    public InMemoryTripsJava(Clock clock) {
        this.clock = clock;
    }

    public void addTrip(TripJava trip) {
        var existingTrips = trips.getOrDefault(
                trip.getCustomerId(),
                new HashSet<>());
        existingTrips.add(trip);
        trips.put(trip.getCustomerId(), existingTrips);
    }

    @Override
    public Set<TripJava> tripsFor(String customerId) {
        return trips.getOrDefault(customerId, emptySet());
    }

    @Override
    public Set<TripJava> currentTripsFor(String customerId, Instant at) {
        return tripsFor(customerId).stream()
                .filter(trip -> trip.isPlannedToBeActiveAt(at))
                .collect(toSet());
    }
}
