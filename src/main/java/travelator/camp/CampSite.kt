package travelator.camp

data class CampSite(
    val id: String,
    val name: String,
    val address: AddressJava,
) {
    val countryCode: String
        get() = address.countryCode

    val region: String get() = address.region
}
