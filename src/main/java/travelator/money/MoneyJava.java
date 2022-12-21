package travelator.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MoneyJava {
    private final BigDecimal amount;
    private final Currency currency;

    private MoneyJava(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static MoneyJava of(BigDecimal amount, Currency currency) {
        return new MoneyJava(
            amount.setScale(currency.getDefaultFractionDigits()),
            currency
        );
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyJava money = (MoneyJava) o;

        if (!Objects.equals(amount, money.amount)) return false;
        return Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public MoneyJava add(MoneyJava that) {
        if (!this.currency.equals(that.currency)) {
            throw new IllegalArgumentException(
                    "cannot add Money values of different currencies"
            );
        }
        return new MoneyJava(this.amount.add(that.amount) , this.currency);
    }

}
