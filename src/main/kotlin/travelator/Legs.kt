package travelator

import java.time.Duration
import java.util.*

object Legs {

    @JvmStatic
    fun findLongestLegOver(
        legs: List<Leg>,
        duration: Duration
    ): Optional<Leg> {
        return Optional.ofNullable(longestOver(legs, duration))
    }

    fun isLongerThan(leg: Leg, duration: Duration): Boolean {
        return leg.plannedDuration.compareTo(duration) > 0
//        Duration은 비교 연산이 바로 되나?
//        return leg.plannedDuration > duration
    }
}

fun longestOver(
    legs: List<Leg>,
    duration: Duration
): Leg? {
    var result: Leg? = null
    for (leg in legs) {
        if (Legs.isLongerThan(leg, duration)) {
            if (result == null || Legs.isLongerThan(leg, result.plannedDuration)) {
                result = leg;
            }
        }
    }
    return result;
}