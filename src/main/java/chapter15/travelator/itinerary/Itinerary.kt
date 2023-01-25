package chapter15.travelator.itinerary

import travelator.Id
import java.time.Duration

data class Itinerary(
    val id: Id<Itinerary>,
    val route: Route
) : Route by route {

    // filterNot { it.method == travelMethod }
    // dropLast(1)
    // (Route).() -> Route
    fun withTransformedRoute(transform: (Route).() -> Route) =
        copy(route = transform(route))

}

fun Route.hasJourneyLongerThan(duration: Duration) =
    any { it.duration > duration }
