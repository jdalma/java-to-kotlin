package travelator.itinerary

import travelator.itinerary.money.ExchangeRates
import travelator.itinerary.money.Money
import java.util.Currency

class PricingContext(
    private val userCurrency: Currency,
    private val exchangeRates: ExchangeRates
) {
    fun toUserCurrency(money: Money) {
        exchangeRates.convert(money, userCurrency)
    }

    fun summarise(costs : Iterable<Money>) : CostSummaryRefactoring {
        val currencyTotals : List<Money> = costs
            .groupBy { it.currency }
            .values
            .map {
                it.sumOrNull()?: error("Unexpected empty list")
            }

        val lines = currencyTotals
            .sortedBy { it.currency.currencyCode }
            .map { exchangeRates.convert(it, userCurrency) }

        val total = lines
            .map { it.toMoney }
            .fold(Money.of(0, userCurrency) , Money::add)

        return CostSummaryRefactoring(lines, total)
    }
}

fun Iterable<Money>.sumOrNull() = reduceOrNull { money, that -> money.add(that) }

fun Iterable<Money>.sum(zeroCurrency: Currency): Money =
    fold(Money.zero(zeroCurrency), Money::add)
