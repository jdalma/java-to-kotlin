package chapter19.travelator

import chapter19.travelator.handlers.RegistrationData
import dev.forkhandles.result4k.orThrow
import kotlin.jvm.Throws

class CustomerRegistration (
    private val customers: Customers,
    private val exclusionList: ExclusionList
) : IRegisterCustomers {

    @Throws(ExcludedException::class, DuplicateException::class)
    override fun register(data: RegistrationData): Customer {
        when {
            (exclusionList.exclude(data)) -> throw DuplicateException()
            else -> return customers.addToo(data.name, data.email).orThrow()
        }
    }
}
