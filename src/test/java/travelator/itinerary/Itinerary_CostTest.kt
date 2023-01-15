package travelator.itinerary

import Itinerary
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import travelator.Id
import travelator.itinerary.money.*
import travelator.itinerary.money.Money.Companion.zero
import java.math.BigDecimal
import java.util.*

class Itinerary_CostTest {
    private val fx: ExchangeRates =
        ExchangeRatesViaBaseCurrency(
            EUR,
            mapOf(
                GBP to BigDecimal("0.8"),
                USD to BigDecimal("1.6"),
                JOD to BigDecimal("100")
            )
        )
    private val userCurrency: Currency = GBP
    private val calculator = CostSummaryCalculatorRefactoring(userCurrency, fx) // <1>

    private fun costSummary(i: Itinerary): CostSummaryRefactoring {
        i.addCostsTo(calculator) // <2>
        return calculator.summaries() // <3>
    }

    @Test
    fun an_empty_route_costs_nothing() {
        val i = itinerary(route = emptyList())

        val costs = costSummary(i)

        assertEquals(zero(userCurrency), costs.total)
    }

    @Test
    fun route_in_local_currency() {
        val i = itinerary(route = listOf(
            journeyCosting(Money("80", userCurrency)),
            journeyCosting(Money("35", userCurrency))))

        val costs = costSummary(i)

        assertEquals(Money("115", userCurrency), costs.total)
    }

    @Test
    fun route_in_one_foreign_currency() {
        val i = itinerary(route = listOf(
            journeyCosting(Money("100", EUR)),
            journeyCosting(Money("50", EUR))))

        val costs = costSummary(i)

        assertEquals(Money("120", userCurrency), costs.total)
    }

    @Test
    fun route_in_foreign_and_local_currency() {
        val i = itinerary(route = listOf(
            journeyCosting(Money("100", EUR)),
            journeyCosting(Money("50", userCurrency))))

        val costs = costSummary(i)

        assertEquals(Money("130", userCurrency), costs.total)
    }

    @Test
    fun route_in_multiple_foreign_and_local_currencies() {
        val i = itinerary(route = listOf(
            journeyCosting(Money("250", EUR)),
            journeyCosting(Money("10000", JOD)),
            journeyCosting(Money("750", userCurrency))))

        val costs = costSummary(i)

        assertEquals(Money("1030", userCurrency), costs.total)
    }

    private fun journeyCosting(cost: Money): Journey {
        return Journey(cost)
    }

    private fun itinerary(route: Route) =
        Itinerary(Id.mint(), route)
}
