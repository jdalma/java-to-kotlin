package travelator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import travelator.Legs.findLongestLegOver
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.Collections.emptyList
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LongestLegOverTests {
    private val legs = listOf(
        leg("one hour", Duration.ofHours(1)),
        leg("one day", Duration.ofDays(1)),
        leg("two hours", Duration.ofHours(2))
    )
    private val oneDay = Duration.ofDays(1)

    @Test
    fun is_absent_when_no_legs() {
        // optional
        Assertions.assertEquals(
            Optional.empty<Any>(),
            findLongestLegOver(emptyList(), Duration.ZERO)
        )

        // nullable
        assertNull(emptyList<Leg>().longestLegOver(Duration.ZERO))
    }

    @Test
    fun is_absent_when_no_legs_long_enough() {
        // optional
        Assertions.assertEquals(
            Optional.empty<Any>(),
            findLongestLegOver(legs, oneDay)
        )

        // nullable
        assertNull(legs.longestLegOver(oneDay))
    }

    @Test
    fun is_longest_leg_when_one_match() {
        // optional
        Assertions.assertEquals(
            "one day",
            findLongestLegOver(legs, oneDay.minusMillis(1))
                .orElseThrow().description
        )

        // nullable
        assertEquals(
            "one day",
            legs.longestLegOver(Duration.ofMillis(-1))
            !!.description
        )
    }

    @Test
    fun is_longest_leg_when_more_than_one_match() {
        // optional
        Assertions.assertEquals(
            "one day",
            findLongestLegOver(legs, Duration.ofMinutes(59))
                .orElseThrow().description
        )

        // nullable
        assertEquals(
            "one day",
            legs.longestLegOver(Duration.ofMinutes(59))
            ?.description
        )
    }

    private fun leg(description: String, duration: Duration): Leg {
        val start = ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt().toLong()),
            ZoneId.of("UTC"));
        return Leg(description, start, start.plus(duration));
    }
}
