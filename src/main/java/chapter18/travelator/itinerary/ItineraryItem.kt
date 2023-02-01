package chapter18.travelator.itinerary

import chapter18.travelator.Id
import chapter18.travelator.geo.MapOverlay
import chapter18.travelator.money.Money

interface ItineraryItem {
    val id: Id<ItineraryItem>
    val description: String
    val costs: List<Money>
    val mapOverlay: MapOverlay
}