package chapter15.travelator.itinerary

fun Iterable<Itinerary>.shortest() =
    minByOrNull {
        // 이 확장 프로퍼티는 Route.duration, 즉 List<Journey>.duration 이다
        it.duration // <1>
    }

fun Itinerary.withoutJourneysBy(travelMethod: TravelMethod) =
    withTransformedRoute {
        filterNot { it.method == travelMethod }
    }

fun Itinerary.withoutLastJourney(travelMethod: TravelMethod) =
    withTransformedRoute { dropLast(1) }
