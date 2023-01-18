package travelator.itinerary

import travelator.itinerary.money.CurrencyConversion
import travelator.itinerary.money.Money
import java.util.Currency

data class CostSummaryRefactoring(
    val lines: List<CurrencyConversion>,
    val total: Money
)
