package org.selenium.selenium;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greet_withName_returnsGreeting() {
        String result = service.greet("Elif");
        assertEquals("Hello, Elif!", result);
    }

    @Test
    void greet_withNull_returnsDefaultGreeting() {
        String result = service.greet(null);
        assertEquals("Hello, World!", result);
    }

    @Test
    void greet_withEmpty_returnsDefaultGreeting() {
        String result = service.greet("  ");
        assertEquals("Hello, World!", result);
    }
}
