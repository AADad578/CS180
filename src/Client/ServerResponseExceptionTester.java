package Client;

import Chat.MessageException;
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
public class ServerResponseExceptionTester {

    @Test
    void testException() {
        ServerResponseException e = new ServerResponseException("This is a test");
        assertEquals("This is a test", e.getMessage());
    }
}
