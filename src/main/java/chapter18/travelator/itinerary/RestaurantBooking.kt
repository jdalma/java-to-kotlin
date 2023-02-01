package chapter18.travelator.itinerary

import chapter18.travelator.Id
import chapter18.travelator.Location
import chapter18.travelator.geo.PointOverlay
import chapter18.travelator.geo.StandardIcons
import chapter18.travelator.money.Money
import java.time.ZonedDateTime

data class RestaurantBooking(
    override val id: Id<RestaurantBooking>,
    val location: Location,
    val time: ZonedDateTime
) : ItineraryItem {
    override val description get() = location.userReadableName

    override val costs get() = emptyList<Money>()

    override val mapOverlay get() =
        PointOverlay(
            id = id,
            position = location.position,
            text = location.userReadableName,
            icon = StandardIcons.RESTAURANT
        )

}