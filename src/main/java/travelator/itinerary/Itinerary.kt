import travelator.Id
import travelator.itinerary.Accommodation
import travelator.itinerary.CostSummaryCalculator
import travelator.itinerary.Route
import travelator.itinerary.addCostsTo

// 1. 여정을 한 개
// 2. 여정에 포함하는 List<Journey> 여러 개의 여행, 각 Journey마다 일반 비용
// 3. 여정에 포함하는 추가 비용 accommodations

// 한 여정에 한 계산기가 있다
// 그 계산기는 모든 Journey의 비용과 Accommodations의 비용에 대한 합계와 환율을 관리한다
// Calculator의 summary를 하면 초기 환율에 맞게 모든 금액을 합산하여 CostSummary로 반환한다

data class Itinerary(
    val id: Id<Itinerary>,
    val route: Route,
    val accommodations: List<Accommodation> = emptyList()
) {

    fun addCostsTo(calculator: CostSummaryCalculator) {
        route.addCostsTo(calculator)
        accommodations.addCostsTo(calculator)
    }

}

fun Iterable<Accommodation>.addCostsTo(calculator: CostSummaryCalculator) {
    forEach { a ->
        a.addCostsTo(calculator)
    }
}
