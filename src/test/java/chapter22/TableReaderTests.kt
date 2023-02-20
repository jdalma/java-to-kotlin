package chapter22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TableReaderTests {

    @Test
    fun `empty list returns empty list`() {
        Assertions.assertEquals(
            emptyList<String>(),
            readTable(emptyList())
        )
    }

    @Test
    fun `one line of input with default field names`() {
        Assertions.assertEquals(
            listOf(
               mapOf("0" to "field0", "1" to "field1")
            ),
            readTable(
                listOf(
                    "field0,field1"
                )
            )
        )
    }

    @Test
    fun `empty line returns empty map`() {
        Assertions.assertEquals(
            listOf(
                emptyMap<String, String>()
            ),
            readTable(listOf(
                ""
            ))
        )
    }
}