package travelator.itinerary

import travelator.itinerary.money.Money

data class Accommodation(
    val totalPrice: Money
    // and other fields...
)

fun Accommodation.addCostsTo(calculator: CostSummaryCalculatorRefactoring) {
    calculator.addCost(totalPrice)
}
