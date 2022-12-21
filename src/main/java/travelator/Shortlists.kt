@file:JvmName("Shortlists") // 패키지나 임포트문보다도 더 앞에 써야한다.
package travelator

import java.util.Comparator.comparing
import java.util.Comparator.comparingDouble
import java.util.stream.Collectors.toUnmodifiableList
import java.util.stream.Stream

fun <T> sorted(
    shortlist: List<T>,
    ordering: Comparator<in T>
): List<T> {
    return shortlist.stream()
        .sorted(ordering)
        .collect(toUnmodifiableList())
}

fun <T> removeItemAt(shortlist: List<T>, index: Int): List<T> {
    return Stream.concat(
        shortlist.stream().limit(index.toLong()),
        shortlist.stream().skip((index + 1).toLong())
    ).collect(toUnmodifiableList())
}

fun byRating(): Comparator<HasRating> {
    return comparingDouble(HasRating::rating).reversed()
}

fun byPriceLowToHigh(): Comparator<HasPrice> {
    return comparing(HasPrice::price)
}

fun <T> byValue(): Comparator<T> where T : HasPrice, T : HasRating {
    return comparingDouble { t: T -> t.rating / t.price }.reversed()
}

fun byRelevance(): Comparator<HasRelevance> {
    return comparingDouble(HasRelevance::relevance).reversed()
}