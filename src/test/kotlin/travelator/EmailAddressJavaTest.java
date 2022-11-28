package travelator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailAddressJavaTest {

    @Test
    void parsing() {
        assertEquals(
            new EmailAddressJava("test", "parse"),
            EmailAddressJava.parse("test@parse")
        );
    }

    @Test
    void parsingFailures() {
        assertThrows(
            IllegalArgumentException.class,
            () -> EmailAddressJava.parse("@")
        );
    }
}
