package travelator.mobile;

import javax.xml.registry.infomodel.User;
import java.util.Currency;
import java.util.Locale;

public class WelcomeViewJava extends View{
    private final UserPreferences preferences;

    public WelcomeViewJava(UserPreferences preferences) {
        this.preferences = preferences;
    }
}
