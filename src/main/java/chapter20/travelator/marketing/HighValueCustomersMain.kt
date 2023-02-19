package chapter20.travelator.marketing

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
            reader
                .asLineSequence()
                .toHighValueCustomerReport()
                .writeTo(writer)
        }
    }
}

fun Reader.asLineSequence() = buffered().lineSequence()

fun Sequence<CharSequence>.writeTo(writer: Writer) {
    writer.appendLines(this)
}

fun Writer.appendLines(lines: Sequence<CharSequence>): Writer {
    return this.also {
        lines.forEach(this::appendLine)
    }
}
