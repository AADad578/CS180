package Chat;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * MessageExceptionTester
 * 
 * tests the MessageException class
 * 
 * @version 4/6/2025
 * 
 * @author Ankur Raghavan
 */
public class MessageExceptionTester {

    @Test
    void testException() {
        MessageException e = new MessageException("This is a test");
        assertEquals("This is a test", e.getMessage());
    }
}
