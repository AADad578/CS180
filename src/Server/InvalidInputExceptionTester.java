package Server;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class InvalidInputExceptionTester {

    @Test
    void testException() {
        InvalidInputException e = new InvalidInputException("This is a test");
        assertEquals(e.getMessage(), "This is a test");
    }
}
