package travelator.mobile

class Application(
    private var preferences: UserPreferences
) {
    fun showWelcome() {
        WelcomeViewJava(preferences).show()
    }

    fun editPreferences() {
        preferences = PreferencesView().showModal(preferences)
    }
}
