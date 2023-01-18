package travelator.itinerary

import travelator.itinerary.money.CurrencyConversion
import travelator.itinerary.money.Money
import java.util.Currency

class CostSummaryRefactoring(
    userCurrency :Currency,
    private val lines: List<CurrencyConversion>
) {
    val total = lines
        .map { it.toMoney }
        .fold(Money.of(0, userCurrency), Money::add)
}
