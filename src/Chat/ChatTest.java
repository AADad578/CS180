package Chat;

import Message.Message;
import User.User;
import Item.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Team Project Phase 1 -- ChatTest
 * <p>
 * The ChatTest tests all the methods in the Chat Class,
 * and see if they work as intended
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public class ChatTest {
    private User user1;
    private User user2;
    private Chat chat;

    @BeforeEach
    public void setUp() {
        // Empty item arrays for now; can fill in when Item class is available
        Item[] items = new Item[0];
        user1 = new User("Alice", 100.0, items, "alice123", "pass1");
        user2 = new User("Bob", 150.0, items, "bob456", "pass2");
        chat = new Chat(user1, user2);
    }

    @Test
    public void testUsersInChat() {
        User[] users = chat.getUsers();
        assertEquals(2, users.length);
        assertEquals(user1, users[0]);
        assertEquals(user2, users[1]);
    }

    @Test
    public void testAddValidMessage() throws MessageError {
        chat.addMessage("Hello, Bob!", user1, 1000);

        ArrayList<Message> messages = chat.getMessages();
        assertEquals(1, messages.size());

        Message m = messages.get(0);
        assertEquals("Hello, Bob!", m.getMessageContent());
        assertEquals(user1, m.getSender());
        assertEquals(user2, m.getReceiver());
        assertEquals(1000, m.getTimeSent());
    }

    @Test
    public void testAddMessageFromNonParticipantThrows() {
        Item[] items = new Item[0];
        User outsider = new User("Charlie", 50.0, items, "charlie789", "pass3");

        Exception exception = assertThrows(MessageError.class, () -> {
            chat.addMessage("Can I join?", outsider, 1234);
        });

        assertTrue(exception.getMessage().contains("Sender is not a participant"));
    }

    @Test
    public void testGetMessagesInitiallyEmpty() {
        assertTrue(chat.getMessages().isEmpty());
    }

    @Test
    public void testConstructorWithMessages() {
        ArrayList<Message> msgs = new ArrayList<>();
        msgs.add(new Message("Hey", 500, user2, user1));
        Chat chatWithMsgs = new Chat(user1, user2, msgs);

        assertEquals(1, chatWithMsgs.getMessages().size());
        assertEquals("Hey", chatWithMsgs.getMessages().get(0).getMessageContent());
    }

    @Test
    public void testToString() {
        String expected = "";
        assertEquals(expected, chat.toString());
    }
}
