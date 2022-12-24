package travelator

import java.util.*
import java.util.Comparator.comparing
import java.util.Comparator.comparingDouble

fun <T> Iterable<T>.sorted(ordering: Comparator<in T>): List<T> =
    sortedWith(ordering)

fun <T> Iterable<T>.removeItemAt(index: Int): List<T> =
    take(index) + drop(index + 1)

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
