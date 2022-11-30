package travelator

import java.time.Duration
import java.util.*

object Legs {
    @JvmStatic
    fun findLongestLegOver(
        legs: List<Leg>,
        duration: Duration
    ): Optional<Leg> {
        return Optional.ofNullable(legs.longestLegOver(duration))
    }
}

//  [1]
//fun longestLegOver(
//  legs: List<Leg>,
//  duration: Duration
//): Leg? {
//  var longestLeg: Leg? = legs.maxByOrNull(Leg::plannedDuration) ?: return null
//  return if (longestLeg != null && longestLeg.plannedDuration > duration)
//      longestLeg
//  else
//      null
//}

//    [2]
//fun longestLegOver(
//    legs: List<Leg>,
//    duration: Duration
//): Leg? {
//    val longestLeg = legs.maxByOrNull(Leg::plannedDuration)?.let { longestLeg ->
//        if (longestLeg.plannedDuration > duration)
//            longestLeg
//        else
//            null
//    }
//}

//    [3]
//fun longestLegOver(
//    legs: List<Leg>,
//    duration: Duration
//): Leg? {
//    val longestLeg = legs.maxByOrNull(Leg::plannedDuration)
//    return when {
//        longestLeg == null -> null
//        longestLeg.plannedDuration > duration -> longestLeg
//        else -> null
//    }
//}

//    [4]
//fun longestLegOver(
//    legs: List<Leg>,
//    duration: Duration
//): Leg? =
//    legs.maxByOrNull(Leg::plannedDuration)?.takeIf {
//        longestLeg -> longestLeg.plannedDuration > duration
//    }

//  [5]
fun List<Leg>.longestLegOver(duration: Duration): Leg? {
    val longestLeg = maxByOrNull(Leg::plannedDuration)
    return when {
        longestLeg == null -> null
        longestLeg.plannedDuration > duration -> longestLeg
        else -> null
    }
}

// 구간을 찾고, 그 가장 긴 구간이 주어진 기간보다 길다면 그 값을 반환하고 그렇지 않다면 "null"을 반환한다.
private fun Leg.isLongerThan(duration: Duration) = plannedDuration.compareTo(duration) > 0
