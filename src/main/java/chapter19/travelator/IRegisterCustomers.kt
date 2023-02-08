package chapter19.travelator

import chapter19.travelator.handlers.RegistrationData
import dev.forkhandles.result4k.Result

interface IRegisterCustomers {
    fun register(data: RegistrationData):
            Result<Customer, RegistrationProblem>
}

sealed class RegistrationProblem

object Excluded : RegistrationProblem()

data class Duplicate(val message: String) : RegistrationProblem()

data class DatabaseProblem(val message: String) : RegistrationProblem()
