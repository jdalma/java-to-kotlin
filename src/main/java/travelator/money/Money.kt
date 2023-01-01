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

    @JvmName("add")
    operator fun plus(that: Money): Money {
        require(this.currency == that.currency) {
            "cannot add Money values of different currencies"
        }
        return Money(this.amount.add(that.amount), this.currency);
    }

    companion object {
        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency) = Money(
            amount.setScale(currency.defaultFractionDigits),
            currency
        )

        @JvmStatic
        fun of(amountStr: String, currency: Currency) =
            of(BigDecimal(amountStr), currency)

        @JvmStatic
        fun of(amount: Int, currency: Currency) =
            of(BigDecimal(amount), currency)

        @JvmStatic
        fun zero(userCurrency: Currency) =
            of(BigDecimal.ZERO, userCurrency)
    }
}
