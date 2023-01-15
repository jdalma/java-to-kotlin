package travelator.itinerary.money

import java.util.*

// These all have 100 minor units per major unit
val GBP: Currency = Currency.getInstance("GBP")
val USD: Currency = Currency.getInstance("USD")
val EUR: Currency = Currency.getInstance("EUR")

// JOD has 1000 minor units per major unit
val JOD: Currency = Currency.getInstance("JOD")

// JPY has no minor units
val JPY: Currency = Currency.getInstance("JPY")
