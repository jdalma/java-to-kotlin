package travelator

import java.time.Instant

class InMemoryTrips : TripsJava {
    private val trips: MutableMap<String, MutableSet<TripJava>> = mutableMapOf()

    fun addTrip(trip: TripJava) {
        val existingTrips = trips.getOrDefault(trip.customerId, mutableSetOf())
        existingTrips.add(trip)
        trips[trip.customerId] = existingTrips
    }

    override fun tripsFor(customerId: String?) =
        trips.getOrDefault(customerId, emptySet<TripJava>())
//    trips.getOrDefault(customerId, mutableSetOf())

    override fun currentTripsFor(customerId: String?, at: Instant?) =
        tripsFor(customerId)
            .filter { it.isPlannedToBeActiveAt(at) }
            .toSet()


}
