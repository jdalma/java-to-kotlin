package travelator.itinerary

import chapter18.travelator.itinerary.Accommodation
import travelator.itinerary.money.Money

data class Accommodation(
    val totalPrice: Money
    // and other fields...
)

fun Accommodation.costs() = listOf(totalPrice)
