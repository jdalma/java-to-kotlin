package travelator;

import java.util.Objects;
import java.util.UUID;

public class Id<T> {
    private final String raw;

    private Id(String raw) {
        this.raw = raw;
    }

    public static <T> chapter18.travelator.Id<T> of(String raw) {
        return new chapter18.travelator.Id<T>(raw);
    }

    public static <T> String raw(chapter18.travelator.Id<T> id) {
        return id.raw;
    }

    public static <T> chapter18.travelator.Id<T> mint() {
        return chapter18.travelator.Id.of(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        chapter18.travelator.Id<?> id = (chapter18.travelator.Id<?>) o;
        return raw.equals(id.raw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raw);
    }

    @Override
    public String toString() {
        return raw;
    }
}
