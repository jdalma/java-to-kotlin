import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LearningTestJava {

    class Employee {
        String name;
        String email;

        public Employee(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    @Test
    void equals() {
        Employee e1 = new Employee("First", "First");
        Employee e2 = new Employee("First", "First");

        Assertions.assertNotEquals(e1.hashCode(), e2.hashCode());
        Assertions.assertNotEquals(e1, e2);
    }
}
