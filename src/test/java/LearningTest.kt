import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import travelator.InMemoryTrips

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

    @Test
    fun <T> mutableList() {
        val strings = arrayOf("first" , "second", "third")
        val firstList: List<String> = mutableListOf(*strings)

        /**
         * 코틀린의 List와 Collection 인터페이스는 상태를 변경하는 메소드가 정의되어 있지 않다.
         * 가변으로 사용하고 싶다면 MutableList 타입으로 사용하여야 한다.
         */

        val secondList: MutableList<String> = mutableListOf(*strings)
        secondList.removeAt(0)

        assertEquals(secondList.size, strings.size - 1)
    }

    @Test
    fun referenceTest() {
        class ValueType(
            val strings: List<String>
        ) {
            val first: String? = strings.firstOrNull()
        }

        val list = MutableList(3) {index -> "test$index" }
        val data = ValueType(list)

        assertEquals(data.first, data.strings.first())

        list[0] = "banana"
        assertEquals(data.strings.first(), "banana")
        assertEquals(data.first, "test0")
        assertNotEquals(data.first, data.strings.first())
    }

    @Test
    fun listFilter() {

        val pattern = "3"
        val size : Int = 5
        var list = mutableListOf<MutableList<Employee>>()

        for (i in 0..size) {
            val inner = mutableListOf<Employee>()
            for (j in 0.. size) {
                inner.add(Employee("$i$j" , "$i$j"))
            }
            list.add(inner)
        }

        val afterList = list.filter { nameStartsWith(pattern,it) }

        for (employees in afterList) {
            for (employee in employees) {
                assertTrue(employee.name.startsWith(pattern))
            }
        }
    }

    private fun nameStartsWith(pattern: String, employees: List<Employee>): Boolean {
        for (employee in employees)
            when {
                !employee.name.startsWith(pattern) -> return false
            }
        return true
    }

    @Test
    fun unit() {
        val test : Unit = method()
        val test1 : Unit = method()

        assertEquals(System.identityHashCode(test), System.identityHashCode(test1))
    }

    private fun method() {

    }
}
