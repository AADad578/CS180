import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

public class MessageTester {
    private final PrintStream originalOutput = System.out;
    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreOutput() {
        System.setOut(originalOutput);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test(timeout = 1000)
    public void testMessageMethods() {
        User sender = new User("Vincent", 54.65, "vhollow", "etefev443");
        User receiver = new User("NotVincent", 64.65, "novince", "teertk43j");
        Message message = new Message("Can I buy your phone?", 1756,
                sender, receiver);
        String expected = "To: NotVincent\nMessage: Can I buy your " +
                "phone?\nFrom: Vincent\nTime: 1756\n";
        message.sendMessage();
        String stuOut = getOutput();
        stuOut = stuOut.replace("\r\n", "\n");
        assertEquals("Error in sending message", expected.trim(), stuOut.trim());

        expected = "Here's your messages:\nTo: NotVincent\nMessage: Can I buy your " +
                "phone?\nFrom: Vincent\nTime: 1756\n";
        String result = message.viewMessages();
        assertEquals("Error in viewing messages", expected.trim(), result.trim());
    }

    @Test
    public void testToString() {
        User sender = new User("Vincent", 54.65, "vhollow", "etefev443");
        User receiver = new User("NotVincent", 64.65, "novince", "teertk43j");
        Message message = new Message("Can I buy your phone?", 1756,
                sender, receiver);
        String expected = "To: NotVincent\nMessage: Can I buy your " +
                "phone?\nFrom: Vincent\nTime: 1756\n";
        assertEquals("Error in toString method", expected.trim(), message.toString().trim());
    }

    @Test
    public void testGetters() {
        User sender = new User("Vincent", 54.65, "vhollow", "etefev443");
        User receiver = new User("NotVincent", 64.65, "novince", "teertk43j");
        Message message = new Message("Can I buy your phone?", 1756,
                sender, receiver);
        assertEquals("Error in getMessageContent()", "Can I buy your phone?",
                message.getMessageContent());
        assertEquals("Error in getTimeSent()", 1756, message.getTimeSent());
        assertEquals("Error in getSender()", sender, message.getSender());
        assertEquals("Error in getReceiver()", receiver, message.getReceiver());
    }

    @Test
    public void testSetters() {
        User sender = new User("Vincent", 54.65, "vhollow", "etefev443");
        User receiver = new User("NotVincent", 64.65, "novince", "teertk43j");
        Message message = new Message("Can I buy your phone?", 1756,
                sender, receiver);
        assertEquals("Error in getMessageContent()", "Can I buy your phone?",
                message.getMessageContent());
        assertEquals("Error in getTimeSent()", 1756, message.getTimeSent());
        assertEquals("Error in getSender()", sender, message.getSender());
        assertEquals("Error in getReceiver()", receiver, message.getReceiver());

        message.setMessageContent("Can I not buy anything?");
        message.setTimeSent(1845);
        sender = new User("Bob", 24.65, "bobbytheman", "12345ok");
        message.setSender(sender);
        receiver = new User("NotBob", 44.65, "notbobtheguy", "54311ok");
        message.setReceiver(receiver);

        assertEquals("Error in setMessageContent()", "Can I not buy anything?",
                message.getMessageContent());
        assertEquals("Error in setTimeSent()", 1845, message.getTimeSent());
        assertEquals("Error in setSender()", sender, message.getSender());
        assertEquals("Error in setReceiver()", receiver, message.getReceiver());
    }

}
