import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test
import java.util.EmptyStackException
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

    @Test
    fun dataClassReferenceTest() {
        var employee1 :Employee = Employee("Origin" , "Origin");
        var employee2 = employee1.copy()

        assertNotEquals(System.identityHashCode(employee1), System.identityHashCode(employee2))

        employee2.name = "Updated";

        assertEquals(employee2.name, "Updated")
        assertNotEquals(employee1.name, "Updated")

        var employee3 = Employee("Origin", "Origin")

        assertNotEquals(System.identityHashCode(employee1), System.identityHashCode(employee3))
    }
}
