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

public class DatabaseTester {
    private Database db;
    private Item[] items;
    private Chat[] chats;
    private User[] users;

    @BeforeEach
    void setUp() {
        Item[] items = { new Item("test1", 1, "loc1", "pic1.png"),
                new Item("test2", 2, "loc2", "pic2.png"),
                new Item("test3", 3, "loc3", "pic3.png") };
        Item[] item1 = { items[0] };
        Item[] item2 = { items[1] };
        Item[] item3 = { items[2] };

        User[] users = { new User("test1", 1, item1, "t1", "TEST1"),
                new User("test2", 2, item2, "t2", "TEST2"),
                new User("test3", 3, item3, "t3", "TEST3") };
        Chat[] chats = { new Chat(users[0], users[1]), new Chat(users[1], users[2]), new Chat(users[2], users[0]) };
        db = new Database(items, chats, users);
        this.items = items;
        this.chats = chats;
        this.users = users;
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
        String expected = "Database: \n    Items: '\n    Chats: \n    Users: ";
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
        assertEquals(db.getChats(), deserializedDB.getChats());
        assertEquals(db.getItems(), deserializedDB.getItems());
        assertEquals(db.getUsers(), deserializedDB.getUsers());

    }
}
