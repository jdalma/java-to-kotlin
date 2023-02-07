package chapter19.travelator

import com.natpryce.Failure
import com.natpryce.Success
import java.util.*
import kotlin.jvm.Throws

interface Customers {

    @Throws(DuplicateException::class)
    fun add(name: String, email: String): Customer
    fun addToo(name: String, email: String) =
        try {
            Success(add(name, email))
        } catch (x: DuplicateException) {
            Failure(x)
        }
    fun find(id: String): Optional<Customer>
}
