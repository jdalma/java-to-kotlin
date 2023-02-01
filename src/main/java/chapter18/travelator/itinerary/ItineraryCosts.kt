package chapter18.travelator.itinerary

import chapter18.travelator.money.Money


val ItineraryItem.costs: List<Money>
    get() = when (this) {
        is Accommodation -> listOf(totalPrice)
        is Attraction -> emptyList()
        is Journey -> listOf(price)
        is RestaurantBooking -> emptyList()
    }
