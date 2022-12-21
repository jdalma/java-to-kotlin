package travelator.money

import java.math.BigDecimal
import java.util.*

class Money
private constructor(
    val amount: BigDecimal,
    val currency: Currency
) {

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || this.javaClass != o.javaClass) {
            return false
        }
        val money = o as Money
        return this.amount == money.amount && this.currency == o.currency
    }

    override fun hashCode(): Int {
        return Objects.hash(amount, currency)
    }

    override fun toString(): String {
        return "$amount ${currency.currencyCode}"
    }

    fun add(that: Money): Money {
        require(this.currency == that.currency) {
            "cannot add Money values of different currencies"
        }
        return Money(this.amount.add(that.amount), this.currency);
    }

    companion object {
        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency): Money {
            return Money(
                amount.setScale(currency.defaultFractionDigits),
                currency
            )
        }
    }
}
