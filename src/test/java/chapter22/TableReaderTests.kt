package chapter22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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

    @Test
    fun `two lines of input with default field names`() {
        assertEquals(
            listOf(
                mapOf("0" to "row0field0", "1" to "row0field1"),
                mapOf("0" to "row1field0", "1" to "row1field1")
            ),
            readTable(listOf(
                "row0field0,row0field1",
                "row1field0,row1field1"
            ))
        )
    }

    @Test
    fun `takes headers form header line`() {
        assertEquals(
            listOf(
                mapOf("H0" to "field0", "H1" to "field1")
            ),
            readTableWithHeader(
                listOf(
                    "H0,H1",
                    "field0,field1"
                )
            )
        )
    }

    @Test
    fun `readTableWithHeader on empty list returns empty list`() {
        assertEquals(
            emptyList(),
            readTableWithHeader(
                emptyList()
            )
        )
    }

    @Test
    fun `can specify header names when there is no header row`() {
        val headers = listOf("apple", "banana")
        assertEquals(
            listOf(
                mapOf(
                    "apple" to "field0",
                    "banana" to "field1",
                )
            ),
            readTable(
                listOf("field0,field1"),
                headers::get
            )
        )
    }
}