package travelator.itinerary

import chapter18.travelator.itinerary.Journey

typealias Route = List<Journey>

fun Route.costs() = map { it.price }
