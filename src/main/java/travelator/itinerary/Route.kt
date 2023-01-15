package travelator.itinerary

typealias Route = List<Journey>

fun Route.addCostsTo(calculator: CostSummaryCalculatorRefactoring) {
    forEach { journey -> calculator.addCost(journey.price) }
}
