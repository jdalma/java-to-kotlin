package chapter18.travelator.itinerary

import chapter18.travelator.Id
import chapter18.travelator.Location
import chapter18.travelator.geo.PointOverlay
import chapter18.travelator.geo.StandardIcons
import chapter18.travelator.money.Money

data class Attraction(
    override val id: Id<Attraction>,
    val location: Location,
    val notes: String
) : ItineraryItem {
    override val description get() =
        location.userReadableName

    override val costs get() =
        emptyList<Money>()

    override val mapOverlay get() =
        PointOverlay(
            position = location.position,
            text = description,
            icon = StandardIcons.ATTRACTION,
            id = id
        )

}