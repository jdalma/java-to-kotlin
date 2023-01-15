package travelator.itinerary

import travelator.itinerary.money.ExchangeRates
import travelator.itinerary.money.Money
import java.util.Comparator.comparing
import java.util.Currency

class CostSummaryCalculatorRefactoring(
    private val userCurrency: Currency,
    private val exchangeRates: ExchangeRates
) {
    private val currencyTotals = mutableMapOf<Currency,Money>()

    fun addCost(cost: Money) {
        currencyTotals.merge(cost.currency, cost, Money::add)
    }

    fun summaries() : CostSummaryRefactoring {
        val totals = currencyTotals.values.sortedBy {
            it.currency.currencyCode
        }
        return CostSummaryRefactoring(userCurrency).apply {
            for (total in totals) {
                addLine(exchangeRates.convert(total, userCurrency))
            }
        }
    }

    fun reset() {
        currencyTotals.clear()
    }
}
