package chapter18.travelator.itinerary

import chapter18.travelator.Id
import chapter18.travelator.Location
import chapter18.travelator.geo.*
import chapter18.travelator.money.Money
import java.time.ZonedDateTime

data class Journey(
    override val id: Id<Journey>,
    val travelMethod: TravelMethod,
    val departsFrom: Location,
    val departureTime: ZonedDateTime,
    val arrivesAt: Location,
    val arrivalTime: ZonedDateTime,
    val price: Money,
    val path: List<Position> = listOf(departsFrom.position, arrivesAt.position),
) : ItineraryItem {
    override val description
        get() = "${departsFrom.userReadableName} " +
            "to ${arrivesAt.userReadableName} " +
            "by ${travelMethod.userReadableName}"

    override val costs
        get() = listOf(price)

    override val mapOverlay
        get() = OverlayGroup(
            id = id,
            elements = listOf(
                PathOverlay(path, travelMethod.userReadableName),
                PointOverlay(departsFrom.position, departsFrom.userReadableName, StandardIcons.START),
                PointOverlay(arrivesAt.position, arrivesAt.userReadableName, StandardIcons.END)
            )
        )

}