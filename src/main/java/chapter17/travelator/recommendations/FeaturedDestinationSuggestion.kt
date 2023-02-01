package chapter17.travelator.recommendations

import chapter17.travelator.destinations.FeaturedDestination
import chapter17.travelator.domain.Location

data class FeaturedDestinationSuggestion(
    val routeLocation: Location,
    val suggestion: FeaturedDestination,
    val distanceMeters: Int
)
