package chapter15.travelator.itinerary

import chapter15.travelator.Location
import java.time.Duration
import java.util.*

typealias Route = List<Journey>

/**
 * 기존 코드의 Route 생성자를 위함
 */
fun Route(journeys: List<Journey>) = journeys

/**
 * 기존 Route 클래스의 journeys의 프로퍼티도 수신 객체 자신을 반환하는 확장 함수로 대신할 수 있다.
 */
val Route.journeys get() = this

val Route.departsFrom: Location
    get() = first().departsFrom

val Route.arrivesAt: Location
    get() = last().arrivesAt

val Route.duration: Duration
    get() = Duration.between(
        first().departureTime,
        last().arrivalTime
    )

fun <T> Iterable<T>.withItemAt(index: Int, replacedBy: T): List<T> =
    this.toMutableList().apply {
        this[index] = replacedBy
    }
