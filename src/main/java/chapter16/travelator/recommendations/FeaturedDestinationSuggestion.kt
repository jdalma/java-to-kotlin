package chapter16.travelator.recommendations

import chapter16.travelator.destinations.FeaturedDestination
import chapter16.travelator.domain.Location

data class FeaturedDestinationSuggestion(
    val routeLocation: Location,
    val suggestion: FeaturedDestination,
    val distanceMeters: Int
)
