package Database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import User.User;

public class DatabaseTester {
    private Database db;
    private Item[] items;
    private Chat[] chats;
    private User[] users;

    @BeforeEach
    void setUp() {
        Item[] items = { new Item(), new Item(), new Item() };
        Chat[] chats = { new Chat(), new Chat(), new Chat() };
        User[] users = { new User("test1", 1, "t1", "TEST1"), new User("test2", 2, "t2", "TEST2"),
                new User("test3", 3, "t3", "TEST3") };
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
        Item[] items2 = { new Item(), new Item() };
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
        db.setItems(chats2);
        assertEquals(chats2, db.getChats());
    }

    @Test
    void testGetUsers() {
        assertEquals(users, db.getUsers());
    }

    @Test
    void testSetUsers() {
        User[] users2 = { new User("test1", 1, "t1", "TEST1"),
                new User("test3", 3, "t3", "TEST3") };
        db.setItems(users2);
        assertEquals(users2, db.getUsers());
    }

    @Test
    void testToString() {
        String expected = "User: \n   username: alice123'\n   name: Alice'\n   balance: 100.0";
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
        User deserializedUser = (User) in.readObject();

        // Verify that the deserialized object is equal to the original
        assertEquals(db.getName(), deserializedUser.getName());
        assertEquals(db.getUserName(), deserializedUser.getUserName());
        assertEquals(db.getBalance(), deserializedUser.getBalance());

        // Password will be null if it's marked transient
        assertEquals(db.getPassword(), deserializedUser.getPassword());
    }
}
