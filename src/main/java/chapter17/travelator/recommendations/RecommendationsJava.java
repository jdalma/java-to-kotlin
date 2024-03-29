package chapter17.travelator.recommendations;

import chapter17.travelator.destinations.FeaturedDestinations;
import chapter17.travelator.domain.DistanceCalculator;
import chapter17.travelator.domain.Location;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class RecommendationsJava {
    private final FeaturedDestinations featuredDestinations;
    private final DistanceCalculator distanceCalculator;

    public RecommendationsJava(
        FeaturedDestinations featuredDestinations,
        DistanceCalculator distanceCalculator
    ) {
        this.featuredDestinations = featuredDestinations;
        this.distanceCalculator = distanceCalculator;
    }
    private final Comparator<FeaturedDestinationSuggestion> distanceComparator =
        comparing(FeaturedDestinationSuggestion::getDistanceMeters);


    public List<FeaturedDestinationSuggestion> recommendationsFor(
        Set<Location> journey
    ) {
        var results = removeDuplicates(
            journey.stream()
                .flatMap(location ->
                    recommendationsFor(location).stream()
                )
        );
        results.sort(distanceComparator);
        return results;
    }

    public List<FeaturedDestinationSuggestion> recommendationsFor(
        Location location
    ) {
        return featuredDestinations
            .findCloseTo(location) // <1>
            .stream()
            .map(featuredDestination ->
                new FeaturedDestinationSuggestion(
                    location,
                    featuredDestination,
                    distanceCalculator.distanceInMetersBetween( // <2>
                        location,
                        featuredDestination.getLocation()
                    )
                )
            ).collect(toList());
    }


    private List<FeaturedDestinationSuggestion> removeDuplicates(
        Stream<FeaturedDestinationSuggestion> suggestions
    ) {
        return suggestions
            .collect(
                groupingBy(FeaturedDestinationSuggestion::getSuggestion)
            ).values().stream()
            .map(this::closestToJourneyLocation)
            .collect(toList());
    }

    private FeaturedDestinationSuggestion closestToJourneyLocation(
        List<FeaturedDestinationSuggestion> suggestionsWithSameDestination
    ) {
        return suggestionsWithSameDestination.stream().min(distanceComparator).orElseThrow();
    }
}
