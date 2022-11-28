package travelator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class EmailAddressTest {

    @Test
    fun equals() {
        var email1 = EmailAddress.parse("test@naver.com");
        var email2 = EmailAddress.parse("test@naver.com");

        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    fun parsingFailures() {
        assertThrows(
            IllegalArgumentException::class.java
        ) { EmailAddressJava.parse("@") }

        assertThrows<IllegalArgumentException> {
            EmailAddress.parse("test");
        }
    }

    @Test
    fun copy() {
        var email1 = EmailAddress.parse("test@naver.com");
        var email2 = email1.copy(domain = "google.com");

        assertEquals(email1.localPart, email2.localPart);
        assertNotEquals(email1.domain, email2.domain);
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }
}
