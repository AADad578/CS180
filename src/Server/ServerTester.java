package Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import User.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Chat.Chat;
import Database.Database;
import Item.Item;

/**
 * ServerTester
 * 
 * Tests the Server class
 * 
 * @version 4/6/2025
 * 
 * @author Ankur Raghavan
 */
public class ServerTester {
    private Database db;
    private Item[] items;
    private Chat[] chats;
    private User[] users;
    private Server server;

    @BeforeEach
    void setUp() {

        Item[] itemsLoc = { new Item("test1", 1, "loc1", "pic1.png"),
            new Item("test2", 2, "loc2", "pic2.png"),
            new Item("test3", 3, "loc3", "pic3.png") };
        Item[] item1 = { itemsLoc[0] };
        Item[] item2 = { itemsLoc[1] };

        User[] usersLoc = { new User("test1", 1, item1, "t1", "TEST1"),
            new User("test2", 2, item2, "t2", "TEST2") };
        Chat[] chatsLoc = { new Chat(usersLoc[0], usersLoc[1]) };
        db = new Database(itemsLoc, chatsLoc, usersLoc);
        this.items = itemsLoc;
        this.chats = chatsLoc;
        this.users = usersLoc;

        Server.db = db;
        try {
            server = new Server(-1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests the addUser method
     */
    @Test
    void testAddUser() {
        Item[] item1 = { items[0] };
        Item[] item2 = { items[1] };
        Item[] item3 = { items[2] };

        User[] users2 = { new User("test1", 1, item1, "t1", "TEST1"),
            new User("test2", 2, item2, "t2", "TEST2"),
            new User("test3", 3, item3, "t3", "TEST3") };

        try {
            server.addUser(users2[2]);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.toString(Server.db.getUsers()), Arrays.toString(users2));

        try {
            server.addUser(users2[0]);
            fail();
        } catch (InvalidInputException ignored) {
            System.out.println("good catch");
        }
    }

    /**
     * Tests the removeUser method
     */
    @Test
    void testRemoveUser() {
        Item[] item1 = { items[0] };
        Item[] item2 = { items[1] };
        Item[] item3 = { items[2] };

        User[] users2 = { new User("test1", 1, item1, "t1", "TEST1") };
        User remove = new User("test2", 2, item2, "t2", "TEST2");
        User invalid = new User("test3", 3, item3, "t3", "TEST3");

        try {
            server.removeUser(remove);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.toString(Server.db.getUsers()), Arrays.toString(users2));

        try {
            server.removeUser(invalid);
            fail();
        } catch (InvalidInputException ignored) {
            System.out.println("good catch");
        }
    }

    /**
     * Tests the addItem method
     */
    @Test
    void testAddItem() {
        Item[] items2 = { new Item("test1", 1, "loc1", "pic1.png"),
            new Item("test2", 2, "loc2", "pic2.png"),
            new Item("test3", 3, "loc3", "pic3.png"),
            new Item("test4", 4, "loc4", "pic4.png") };

        try {
            server.addItem(items2[3]);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.toString(Server.db.getItems()), Arrays.toString(items2));

        try {
            server.addItem(items2[0]);
            fail();
        } catch (InvalidInputException ignored) {
            System.out.println("good catch");
        }
    }

    /**
     * tests the removeItem method
     */
    @Test
    void testRemoveItem() {
        Item[] items2 = { new Item("test1", 1, "loc1", "pic1.png"),
            new Item("test2", 2, "loc2", "pic2.png") };
        Item remove = new Item("test3", 3, "loc3", "pic3.png");
        Item invalid = new Item("test4", 4, "loc4", "pic4.png");

        try {
            server.removeItem(remove);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.toString(Server.db.getItems()), Arrays.toString(items2));

        try {
            server.removeItem(invalid);
            fail();
        } catch (InvalidInputException ignored) {
            System.out.println("good catch");
        }
    }

    /**
     * tests the addChat method
     */
    @Test
    void testAddChat() {
        Item[] item1 = { items[0] };
        Item[] item2 = { items[1] };
        Item[] item3 = { items[2] };

        User[] users2 = { new User("test1", 1, item1, "t1", "TEST1"),
            new User("test2", 2, item2, "t2", "TEST2"),
            new User("test3", 3, item3, "t3", "TEST3") };

        Chat[] chats2 = { new Chat(users2[0], users2[1]),
            new Chat(users2[1], users2[2]) };

        try {
            server.addChat(chats2[1]);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.toString(Server.db.getChats()), Arrays.toString(chats2));

        try {
            server.addChat(chats2[0]);
            fail();
        } catch (InvalidInputException ignored) {
            System.out.println("good catch");
        }
    }

    /**
     * tests the removeChat method
     */
    @Test
    void testRemoveChat() {
        Item[] item1 = { items[0] };
        Item[] item2 = { items[1] };
        Item[] item3 = { items[2] };

        User[] users2 = { new User("test1", 1, item1, "t1", "TEST1"),
            new User("test2", 2, item2, "t2", "TEST2"),
            new User("test3", 3, item3, "t3", "TEST3") };

        Chat[] chats2 = {};
        Chat remove = new Chat(users2[0], users2[1]);
        Chat invalid = new Chat(users2[0], users2[2]);

        try {
            server.removeChat(remove);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

        assertEquals(Arrays.toString(Server.db.getChats()), Arrays.toString(chats2));

        try {
            server.removeChat(invalid);
            fail();
        } catch (InvalidInputException ignored) {
            System.out.println("good catch");
        }
    }

    /**
     * tests the saveDatabase and the recallDatabase methods
     */
    @Test
    void testSaveRecallDatabase() {
        server.saveDatabase();
        Database loc = Server.db;
        Server.db = null;
        Server.recallDatabase();
        assertEquals(Server.db.toString(), loc.toString());
    }
}
