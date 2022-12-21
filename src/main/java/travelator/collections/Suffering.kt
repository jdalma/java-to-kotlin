package travelator.collections

import travelator.collections.Collections.sorted
import travelator.collections.SufferingJava.SOME_COMPLICATED_RESULT
import travelator.collections.SufferingJava.routesFor
import java.util.stream.Collectors

object Suffering {
    
    @JvmStatic
    fun sufferScoreFor(route: List<JourneyJava>): Int {
        return sufferScore(
            route.longestJourneys(limit = 3),
            SufferingJava.getDepartsFrom(route)
        )   
    }

    @JvmStatic
    fun longestJourneysIn(
        journeys: List<JourneyJava>,
        limit: Int
    ): List<JourneyJava> {
//        [1]
//        val actualLimit = journeys.size.coerceAtMost(limit)
//        return sorted(
//            journeys,
//            Comparator.comparing {
//                obj: JourneyJava -> obj.duration
//            }.reversed()
//        ).subList(0, actualLimit)
//        [2]
        return journeys.sortedByDescending { it.duration }.take(limit)
    }

    @JvmStatic
    fun List<JourneyJava>.longestJourneys(limit: Int): List<JourneyJava> =
        sortedByDescending { it.duration }.take(limit)

    private fun sufferScore(
        longestJourneysIn: List<JourneyJava>,
        start: LocationJava
    ): Int {
        return SOME_COMPLICATED_RESULT()
    }

    fun routesShowFor(itineraryId: String?): List<List<JourneyJava>> {
        return bearable(routesFor(itineraryId))
    }

    private fun bearable(routes: List<List<JourneyJava>>): List<List<JourneyJava>>
//    {
//        [1]
//        return routes.stream()
//            .filter { route -> sufferScoreFor(route) <= 10 }
//            .collect(Collectors.toUnmodifiableList())
//    }
    = routes.filter { sufferScoreFor(it) <= 10 }
}
