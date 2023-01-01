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

    fun add(that: Money) = this + that

    operator fun plus(that: Money): Money {
        require(currency == that.currency) {
            "cannot add Money values of different currencies"
        }
        return Money(amount.add(that.amount), currency)
    }

    companion object {
        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency) =
            this(amount, currency)

        operator fun invoke(amount: BigDecimal, currency: Currency) =
            Money(
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
