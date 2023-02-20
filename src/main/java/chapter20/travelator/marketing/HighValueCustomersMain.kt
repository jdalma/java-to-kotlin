package chapter20.travelator.marketing

import java.io.Closeable
import java.io.OutputStreamWriter
import java.io.Reader
import java.io.Writer

fun main() {
    System.`in`.reader().use { reader ->
        System.out.writer().use { writer ->
//            [1]
//            generate(reader.readLines())
//                .forEach { line ->
//                    writer.append(line)
//                }
//            [2]
//            writer.appendLines(
//                generate(reader.readLines().asSequence().constrainOnce())
//            )
//            [3]
//            val errorLines = mutableListOf<ParseFailure>()
//            val reportLines = reader
//                .asLineSequence()
//                .toHighValueCustomerReport {
//                    errorLines += it
//                }
//            if (errorLines.isNotEmpty()) {
//                System.err.writer().use {error ->
//                    error.appendLine("Lines with errors")
//                    errorLines.asSequence().map { parseFailure ->
//                        "${parseFailure::class.simpleName} in ${parseFailure.line}"
//                    }.writeTo(error)
//                }
//                exitProcess(-1)
//            } else {
//                reportLines.writeTo(writer)
//            }
            val statusCode = using(
                System.`in`.reader(),
                System.out.writer(),
                System.err.writer()
            ) { reader, writer, error ->
                val errorLines = mutableListOf<ParseFailure>()
                val reportLines = reader
                    .asLineSequence()
                    .toHighValueCustomerReport {
                        errorLines += it
                    }
                if (errorLines.isEmpty()) {
                    reportLines.writeTo(writer)
                    0
                } else {
                    errorLines.writeTo(error)
                    -1
                }
            }
        }
    }
}

inline fun <A : Closeable, B : Closeable, C : Closeable, R> using(
    a: A,
    b: B,
    c: C,
    block: (A,B,C) -> R
) : R =
    a.use {
        b.use {
            c.use {
                block(a,b,c)
            }
        }
    }

fun Reader.asLineSequence() = buffered().lineSequence()

fun List<ParseFailure>.writeTo(error: OutputStreamWriter) {
    error.appendLine("Lines with Errors")
    asSequence().map { parseFailure ->
        "${parseFailure::class.simpleName} in ${parseFailure.line}"
    }.writeTo(error)
}
fun Sequence<CharSequence>.writeTo(writer: Writer) {
    writer.appendLines(this)
}

fun Writer.appendLines(lines: Sequence<CharSequence>): Writer {
    return this.also {
        lines.forEach(this::appendLine)
    }
}
