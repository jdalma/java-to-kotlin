package travelator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;

public class ShortlistsJava {

    public static <T> List<T> sorted(
            List<T> shortlist,
            Comparator<? super T> ordering) {
        return shortlist.stream()
                .sorted(ordering)
                .collect(Collectors.toUnmodifiableList());
    }

    public static <T> List<T> removeItemAt(List<T> shortlist, int index) {
        return Stream.concat(
                shortlist.stream().limit(index),
                shortlist.stream().skip(index + 1)
        ).collect(Collectors.toUnmodifiableList());
    }

    public static Comparator<HasRating> byRating() {
        return comparingDouble(HasRating::getRating).reversed();
    }

    public static Comparator<HasPrice> byPriceLowToHigh() {
        return comparing(HasPrice::getPrice);
    }

    public static <T extends HasPrice & HasRating> Comparator<T> byValue() {
        return comparingDouble((T t) -> t.getRating() / t.getPrice()).reversed();
    }

    public static Comparator<HasRelevance> byRelevance() {
        return comparingDouble(HasRelevance::getRelevance).reversed();
    }
}
