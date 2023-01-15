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

    fun summaries() : CostSummary {
        val totals = ArrayList(currencyTotals.values)
        totals.sortWith(comparing {m : Money -> m.currency.currencyCode})
        val summary = CostSummary(userCurrency)
        for (total in totals) {
            summary.addLine(exchangeRates.convert(total, userCurrency))
        }
        return summary
    }

    fun reset() {
        currencyTotals.clear()
    }
}
