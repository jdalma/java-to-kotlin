package chapter19.travelator

import chapter19.travelator.handlers.RegistrationData
import dev.forkhandles.result4k.*


class CustomerRegistration(
    private val customers: Customers,
    private val exclusionList: ExclusionList
) : IRegisterCustomers {

    override fun register(
        data: RegistrationData
    ): Result<Customer, RegistrationProblem> {
        return when {
            exclusionList.exclude(data) -> Failure(Excluded)
            else -> customers.add(data.name, data.email)
                .mapFailure { problem: CustomersProblem ->
                    when (problem) {
                        is DuplicateCustomerProblem ->
                            Duplicate(problem.message)
                        is DatabaseCustomerProblem ->
                            DatabaseProblem(problem.message)
                    }
                }
        }
    }
}
