package chapter22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.StringReader
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TableReaderTests {

    @Test
    fun `Table contains headers`() {
        val result: Table = readTableWithHeader(
            listOf(
                "H0,H1",
                "field0,field1"
            ).asSequence()
        )
        assertEquals(
            listOf("H0", "H1"),
            result.headers
        )
    }

    @Test
    fun `Table not contains headers`() {
        val result: Table = readTableWithHeader(
            listOf(
                "field0,field1"
            ).asSequence()
        )
        assertEquals(
            listOf("H0", "H1"),
            result.headers
        )
    }

    @Test
    fun `empty input returns empty`() {
        checkReadTable(
            lines = emptyList(),
            shouldReturn = emptyList()
        )
    }

    @Test
    fun `read from reader`() {
        val fileContents = """
            H0,H1
            row0field0,row0field1
            row1field0,row1field1
        """.trimIndent()
        StringReader(fileContents).useLines { lines ->
            val result = readTableWithHeader(lines).toList()
            assertEquals(
                listOf(
                    mapOf("H0" to "row0field0", "H1" to "row0field1"),
                    mapOf("H0" to "row1field0", "H1" to "row1field1")
                ),
                result
            )
        }
    }

    @Test
    fun `double call sequence`() {
        val strings = listOf("A", "B")
        val sequence = sequenceOf("A" , "B")
        val destruct = sequence.destruct()

        destruct?.let { (first , second) ->
            assertEquals("A", first)
            assertNotEquals(strings, second.toList())
        }
    }

    @Test
    fun `empty list returns empty list`() {
        Assertions.assertEquals(
            emptyList<String>(),
            readTable(emptyList())
        )
    }

    @Test
    fun `one line of input with default field names`() {
        checkReadTable(
            lines = listOf("field0,field1"),
            shouldReturn = listOf(
                mapOf("0" to "field0", "1" to "field1")
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
        checkReadTable(
            lines = listOf("field0,field1"),
            withHeaderProvider = headers::get,
            shouldReturn = listOf(
                mapOf(
                    "apple" to "field0",
                    "banana" to "field1",
                )
            )
        )
    }

    @Test
    fun `can specify splitter`() {
        Assertions.assertEquals(
            listOf(
                mapOf(
                    "header1" to "field0",
                    "header2" to "field1",
                )
            ),
            readTableWithHeader(
                listOf(
                    "header1\theader2",
                    "field0\tfield1"
                ),
                splitOnTab
            )
        )
    }

    private fun checkReadTable(
        lines: List<String>,
        withHeaderProvider: (Int) -> String = Int::toString,
        shouldReturn: List<Map<String, String>>
    ) {
        assertEquals(
            shouldReturn,
            readTable(
                lines.asSequence().constrainOnce(),
                headerProvider = withHeaderProvider,
                splitter = splitOnComma
            ).toList()
        )
    }

    private fun readTableWithHeader(
        lines: List<String>,
        splitter: (String) -> List<String> = splitOnComma
    ): List<Map<String, String>> =
        readTableWithHeader(
            lines.asSequence(),
            splitter
        ).toList()

    private fun readTable(
        lines: List<String>,
        headerProvider: (Int) -> String = Int::toString,
        splitter: (String) -> List<String> = splitOnComma
    ): List<Map<String, String>> =
//    lines.map {
//        [1]
//        parseLine(it, headerProvider) { line ->
//            line.splitFields(",")
//        }
//        [2]
//        val splitOnComma: (String) -> List<String> = { line ->
//            line.splitFields(",")
//        }
//        parseLine(it, headerProvider, splitOnComma)
//    }
        readTable(
            lines.asSequence(),
            headerProvider,
            splitter
        ).toList()

}
