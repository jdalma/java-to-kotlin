package chapter22

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TableReaderAcceptanceTests {

    data class Measurement(
        val t: Double,
        val x: Double,
        val y: Double
    )

    @Test
    fun `acceptance test`() {
        val input = listOf(
            "time,x,y",
            "0.0, 1.0, 1.0",
            "0.1, 1.1, 1.2",
            "1.0, 2.0, 3.0"
        )

        val expected = listOf(
            Measurement(0.0, 1.0, 1.0),
            Measurement(0.1, 1.1, 1.2),
            Measurement(1.0, 2.0, 3.0)
        )

        val actual = readTableWithHeader(input.asSequence()).map { record ->
            Measurement(
                record["time"]?.toDouble() ?: error("in time"),
                record["x"]?.toDouble() ?: error("in x"),
                record["y"]?.toDouble() ?: error("in y")
            )
        }.toList()

        assertEquals(
            expected,
            actual
        )
    }
}
