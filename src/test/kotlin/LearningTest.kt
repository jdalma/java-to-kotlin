import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test
import kotlin.test.assertNotEquals

class LearningTest {

    data class Employee(
        var name: String,
        val email: String
    )

    @Test
    fun equals() {
        var employee1 = Employee("First" , "First")
        var employee2 = Employee("First" , "First")

        assertEquals(employee1.hashCode(), employee2.hashCode())
        assertEquals(employee1, employee2)

        employee2.name = "second"

        assertNotEquals(employee1.hashCode() , employee2.hashCode())
        assertNotEquals(employee1, employee2)
    }

    @Test
    fun copy() {
        var employee1 = Employee("Origin" , "Origin")
        var employee2 = employee1.copy(name = "Updated");

        assertNotEquals(employee1.hashCode(), employee2.hashCode())
        assertNotEquals(employee1.name, employee2.name)
        assertEquals(employee1.email, employee2.email)
    }
}
