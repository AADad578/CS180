package Server;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

/**
 * RequestTester
 *
 * Tests the Request class
 *
 * @version 4/20/2025
 *
 * @author Ankur Raghavan
 */
public class RequestTester {

    @Test
    void testRequest() {
        Request r = new Request("ACTIOn", "Payload");
        assertEquals("ACTIOn", r.getAction());
        assertEquals("Payload", r.getPayload());
    }
}
