import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static travelator.Shortlists.*;

public class ShortlistsTest {
    ExampleItem a = new ExampleItem("A", 4.0f, 500, 0.25);
    ExampleItem b = new ExampleItem("B", 2.5f, 250, 0.75);
    ExampleItem c = new ExampleItem("C", 4.8f, 300, 0.5);
    ExampleItem d = new ExampleItem("D", 3.5f, 480, 0.8);

    List<ExampleItem> items = shortlistOf(a, b, c, d);

    @Test
    public void cheapest() {
        var reordered = travelator.ShortlistsKt.sorted(items, byPriceLowToHigh());

        assertEquals(shortlistOf(b, c, d, a), reordered);
    }

    @Test
    public void topRated() {
        assertEquals(shortlistOf(c, a, d, b), travelator.ShortlistsKt.sorted(items, byRating()));
    }

    @Test
    public void bestValue() {
        var reordered = travelator.ShortlistsKt.sorted(items, byValue());

        assertEquals(shortlistOf(c, b, a, d), reordered);
    }

    @Test
    public void mostRelevant() {
        assertEquals(shortlistOf(d, b, c, a), travelator.ShortlistsKt.sorted(items, byRelevance()));
    }

    private List<ExampleItem> shortlistOf(ExampleItem... items) {
        return List.of(items);
    }
}