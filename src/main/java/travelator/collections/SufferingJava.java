package travelator.collections;


import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static travelator.collections.Collections.sorted;

public class SufferingJava {

    public static int sufferScoreFor(List<JourneyJava> route) {
        /**
         * longestJourneysIn로 반환받는 List와
         * 파라미터로 받은 route는 메소드 내에서 정렬이 진행되기 때문에
         * 원소들의 순서가 서로 다르다
         */
        return sufferScore(longestJourneysIn(route, 3), getDepartsFrom(route));
    }

    public static List<JourneyJava> longestJourneysIn(
            List<JourneyJava> journeys,
            int limit
    ) {
        var actualLimit = Math.min(journeys.size(), limit);
//        journeys.sort(comparing(JourneyJava::getDuration).reversed()); // <1>
//        return journeys.subList(0, actualLimit);
        return sorted(journeys, comparing(JourneyJava::getDuration).reversed())
                .subList(0, actualLimit);

    }

    public static List<List<JourneyJava>> routesToShowFor(String itineraryId) {
        return bearable(routesFor(itineraryId));
    }

    private static List<List<JourneyJava>> bearable(List<List<JourneyJava>> routes) {
//        routes.removeIf(route -> sufferScoreFor(route) > 10);
//        return routes;
        return routes.stream()
                .filter(route -> sufferScoreFor(route) <= 10)
                .collect(Collectors.toUnmodifiableList());
    }

    private static int sufferScore(
            List<JourneyJava> longestJourneys,
            LocationJava start
    ) {
        return SOME_COMPLICATED_RESULT();
    }

    public static int SOME_COMPLICATED_RESULT() {
        return 0;
    }

    public static List<List<JourneyJava>> routesFor(String itineraryId) {
        return null;
    }

    public static LocationJava getDepartsFrom(List<JourneyJava> route) {
        return route.get(0).getDepartsFrom();
    }

}
