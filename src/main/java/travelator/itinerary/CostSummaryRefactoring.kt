package travelator.itinerary

import travelator.itinerary.money.CurrencyConversion
import travelator.itinerary.money.Money
import java.util.Currency

class CostSummaryRefactoring(userCurrency :Currency) {
    private val _lines = mutableListOf<CurrencyConversion>()

    var total: Money = Money.of(0, userCurrency)
        private set

    val lines: List<CurrencyConversion> get() = _lines.toList()

    fun addLine(line: CurrencyConversion) {
        _lines.add(line)
        total += line.toMoney
    }

}
