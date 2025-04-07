package Database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import User.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Chat.Chat;
import Item.Item;

/**
 * DatabaseTester
 * 
 * Tests the Database class
 * 
 * @version 4/6/2025
 * 
 * @author Ankur Raghavan
 */
public class DatabaseTester {
    private Database db;
    private Item[] items;
    private Chat[] chats;
    private User[] users;

    @BeforeEach
    void setUp() {
        Item[] itemsLoc = { new Item("test1", 1, "loc1", "pic1.png"),
            new Item("test2", 2, "loc2", "pic2.png"),
            new Item("test3", 3, "loc3", "pic3.png") };
        this.items = itemsLoc;
        Item[] item1 = { itemsLoc[0] };
        Item[] item2 = { itemsLoc[1] };
        Item[] item3 = { itemsLoc[2] };

        User[] usersLoc = { new User("test1", 1, item1, "t1", "TEST1"),
            new User("test2", 2, item2, "t2", "TEST2"),
            new User("test3", 3, item3, "t3", "TEST3") };
        this.users = usersLoc;
        Chat[] chatsLoc = { new Chat(usersLoc[0], usersLoc[1]), new Chat(usersLoc[1], usersLoc[2]),
            new Chat(usersLoc[2], usersLoc[0]) };
        this.chats = chatsLoc;
        db = new Database(itemsLoc, chatsLoc, usersLoc);
    }

    @Test
    void testGetItems() {
        assertEquals(items, db.getItems());
    }

    @Test
    void testSetItems() {
        Item[] items2 = { new Item("test1", 1, "loc1", "pic1.png"),
            new Item("test3", 3, "loc3", "pic3.png") };
        db.setItems(items2);
        assertEquals(items2, db.getItems());
    }

    @Test
    void testGetChats() {
        assertEquals(chats, db.getChats());
    }

    @Test
    void testSetChats() {
        Chat[] chats2 = { new Chat(), new Chat() };
        db.setChats(chats2);
        assertEquals(chats2, db.getChats());
    }

    @Test
    void testGetUsers() {
        assertEquals(users, db.getUsers());
    }

    @Test
    void testSetUsers() {
        Item[] item1 = { items[0] };
        Item[] item3 = { items[2] };
        User[] users2 = { new User("test1", 1, item1, "t1", "TEST1"),
            new User("test3", 3, item3, "t3", "TEST3") };
        db.setUsers(users2);
        assertEquals(users2, db.getUsers());
    }

    @Test
    void testToString() {
        String expected = "Database: \n" +
                "    Items: [Item {Name: test1\n" +
                "Price: $1.00\n" +
                "Location: loc1\n" +
                " }, Item {Name: test2\n" +
                "Price: $2.00\n" +
                "Location: loc2\n" +
                " }, Item {Name: test3\n" +
                "Price: $3.00\n" +
                "Location: loc3\n" +
                " }]\n" +
                "    Chats: [Chat { t1 (test1)\n" +
                "t2 (test2)\n" +
                "}, Chat { t2 (test2)\n" +
                "t3 (test3)\n" +
                "}, Chat { t3 (test3)\n" +
                "t1 (test1)\n" +
                "}]\n" +
                "    Users: [User: \n" +
                "   username: t1'\n" +
                "   name: test1'\n" +
                "   balance: 1.0, User: \n" +
                "   username: t2'\n" +
                "   name: test2'\n" +
                "   balance: 2.0, User: \n" +
                "   username: t3'\n" +
                "   name: test3'\n" +
                "   balance: 3.0]";
        assertEquals(expected, db.toString());
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        // Serialize the user object
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(db);

        // Deserialize the user object
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Database deserializedDB = (Database) in.readObject();

        // Verify that the deserialized object is equal to the original
        assertEquals(db.toString(), deserializedDB.toString());

    }
}
