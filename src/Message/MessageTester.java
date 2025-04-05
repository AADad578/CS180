import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
/**
 * Team Project Phase 1 -- MessageTester Class
 *
 * This class is the testing class for the Message class
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 5, 2025
 *
 */
public class MessageTester {
    private Item[] items; //test list of items for User objects
    private User sender; //test User object for Message object
    private User receiver; //test User object for the Message object
    private Message message; //test Message object

    /**
     * A setUp() that sets up the exact Message, User, and Item objects for each test method
     */
    @Before
    public void setUp() {
        items = new Item[]{
                new Item("iPhone7", 140.99, "Portland", "image1.jpg"),
                new Item("Galaxy Phone", 199.99, "Miami", "image2.jpg")
        }; //test list of items for User objects
        sender = new User("Vincent", 54.65, items, "vhollow", "etefev443"); //test User object for Message object
        receiver = new User("NotVincent", 64.65, items, "novince", "teertk43j");  //test User object for the Message object
        message = new Message("Can I buy your phone?", 1756,
                sender, receiver); //test Message object
    }

    /**
     * Tests the toString() method
     */
    @Test
    public void testToString() {
        String expected = "To: NotVincent\nMessage: Can I buy your " +
                "phone?\nFrom: Vincent\nTime: 1756\n"; //String of expected output
        assertEquals("Error in toString method", expected.trim(), message.toString().trim());
    }

    /**
     * Tests the toEquals() method
     */
    @Test
    public void testEquals() {
        Message message2 = new Message("Can I buy your phone?", 1756,
                sender, receiver); //test message object that's equal to message
        Message message3 = new Message("Can I sell my phone?", 1756,
                sender, receiver);
                    //test message object that's not equal to message due to differing content
        Message message4 = new Message("Can I buy your phone?", 1356,
                sender, receiver);
                    //test message object that's not equal to message due to differing time sent
        User newSender = new User("Billy", 54.65, items, "billest", "eteffdgev443");
                        //differing test sender
        User newReceiver = new User("NotBilly", 64.65, items, "nonobilly", "teggrtk43j");
                        //differing test receiver
        Message message5 = new Message("Can I buy your phone?", 1756,
                newSender, receiver);
            //test message object that's not equal to message due to differing receiver
        Message message6 = new Message("Can I buy your phone?", 1756,
                sender, newReceiver);
            //test message object that's not equal to message due to differing sender

        Item item = new Item("iPhone8", 340.99, "Chicago", "image.jpg");
                    //test Item object

        assertTrue("Message should equal Message2", message.equals(message2));
        assertFalse("Message shouldn't equal Message3", message.equals(message3));
        assertFalse("Message shouldn't equal Message4", message.equals(message4));
        assertFalse("Message shouldn't equal Message5", message.equals(message5));
        assertFalse("Message shouldn't equal Message6", message.equals(message6));
        assertFalse("Message shouldn't equal a non-Message", message.equals(item));
    }

    /**
     * Tests the getMessageContent(), getTimeSent(), getSender(), and getReceiver() methods
     */
    @Test
    public void testGetters() {
        assertEquals("Error in getMessageContent()", "Can I buy your phone?",
                message.getMessageContent());
        assertEquals("Error in getTimeSent()", 1756, message.getTimeSent());
        assertEquals("Error in getSender()", sender, message.getSender());
        assertEquals("Error in getReceiver()", receiver, message.getReceiver());
    }

    /**
     * Tests the setMessageContent(), setTimeSent(), setSender(), and setReceiver() methods
     */
    @Test
    public void testSetters() {
        message.setMessageContent("Can I not buy anything?");
        message.setTimeSent(1845);
        sender = new User("Bob", 24.65, items, "bobbytheman", "12345ok"); //new test sender
        message.setSender(sender);
        receiver = new User("NotBob", 44.65, items, "notbobtheguy", "54311ok"); //new test receiver
        message.setReceiver(receiver);

        assertEquals("Error in setMessageContent()", "Can I not buy anything?",
                message.getMessageContent());
        assertEquals("Error in setTimeSent()", 1845, message.getTimeSent());
        assertEquals("Error in setSender()", sender, message.getSender());
        assertEquals("Error in setReceiver()", receiver, message.getReceiver());
    }

}
