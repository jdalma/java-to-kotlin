package chapter16.travelator.recommendations

import chapter16.travelator.destinations.FeaturedDestination
import chapter16.travelator.destinations.FeaturedDestinations
import chapter16.travelator.domain.DistanceCalculator
import chapter16.travelator.domain.Location

class Recommendations(
    private val distanceCalculator: DistanceCalculator,
    // 인터페이스에서 findCloseTo() 함수 추출
    private val destinationFinder: (Location) -> List<FeaturedDestination>
) {

    fun recommendationsFor(
        journey: Set<Location>
    ): List<FeaturedDestinationSuggestion> =
        journey
            .flatMap { location -> recommendationsFor(location) }
            .deduplicated()
            .sortedBy { it.distanceMeters }

    private fun recommendationsFor(
        location: Location
    ): List<FeaturedDestinationSuggestion> =
        destinationFinder(location)
            .map { featuredDestination ->
                FeaturedDestinationSuggestion(
                    location,
                    featuredDestination,
                    distanceCalculator.distanceInMetersBetween(
                        location,
                        featuredDestination.location
                    )
                )
            }
}

private fun List<FeaturedDestinationSuggestion>.deduplicated() =
    groupBy { it.suggestion }
        .values
        .map { suggestionsWithSameDestination ->
            suggestionsWithSameDestination.closestToJourneyLocation()
        }

private fun List<FeaturedDestinationSuggestion>.closestToJourneyLocation() =
    minByOrNull { it.distanceMeters } ?: error("Unexpected empty group")
