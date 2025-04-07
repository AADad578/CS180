package Chat;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MessageExceptionTester {

    @Test
    void testException() {
        MessageException e = new MessageException("This is a test");
        assertEquals("This is a test", e.getMessage());
    }
}
