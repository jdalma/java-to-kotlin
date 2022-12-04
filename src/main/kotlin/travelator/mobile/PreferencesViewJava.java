package travelator.mobile;

import java.util.Currency;
import java.util.Locale;

public class PreferencesViewJava extends View {

    private final UserPreferences preferences;
    private final GreetingPickerJava greetingPicker = new GreetingPickerJava();
    private final LocalePickerJava localePicker = new LocalePickerJava();
    private final CurrencyPickerJava currencyPicker = new CurrencyPickerJava();

    public PreferencesViewJava(UserPreferences preferences) {
        this.preferences = preferences;
    }

    public void show() {
        greetingPicker.setGreeting(preferences.getGreeting());
        localePicker.setLocale(preferences.getLocale());
        currencyPicker.setCurrency(preferences.getCurrency());
        super.show();
    }

    protected void onGreetingChange() {
        preferences.setGreeting(greetingPicker.getGreeting());
    }

    protected void onLocaleChange() {
        preferences.setLocale(localePicker.getLocale());
    }

    protected void onCurrencyChange() {
        preferences.setCurrency(currencyPicker.getCurrency());
    }
}

class GreetingPickerJava {
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}

class LocalePickerJava {
    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}

class CurrencyPickerJava {
    private Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
