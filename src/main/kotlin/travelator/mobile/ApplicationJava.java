package travelator.mobile;

public class ApplicationJava {

    private final UserPreferences preferences;

    public ApplicationJava(UserPreferences preferences) {
        this.preferences = preferences;
    }

    public void showWelcome() {
        new WelcomeViewJava(preferences).show();
    }

    public void editPreferences() {
        new PreferencesView().showModal(preferences);
    }
}
