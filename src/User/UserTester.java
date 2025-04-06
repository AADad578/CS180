package User;

import static org.junit.jupiter.api.Assertions.*;

import Item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;

class UserTester {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Alice", 100.0, new Item[5],"alice123", "securePass");
    }

    @Test
    void testGetName() {
        assertEquals("Alice", user.getName());
    }

    @Test
    void testSetName() {
        user.setName("Bob");
        assertEquals("Bob", user.getName());
    }

    @Test
    void testGetUserName() {
        assertEquals("alice123", user.getUserName());
    }

    @Test
    void testSetUserName() {
        user.setUserName("bob456");
        assertEquals("bob456", user.getUserName());
    }

    @Test
    void testGetBalance() {
        assertEquals(100.0, user.getBalance());
    }

    @Test
    void testSetBalance() {
        user.setBalance(200.0);
        assertEquals(200.0, user.getBalance());
    }

    @Test
    void testGetPassword() {
        assertEquals("securePass", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newPass123");
        assertEquals("newPass123", user.getPassword());
    }

    @Test
    void testToString() {
        String expected = "User: \n   username: alice123'\n   name: Alice'\n   balance: 100.0";
        assertEquals(expected, user.toString());
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        // Serialize the user object
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(user);

        // Deserialize the user object
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        User deserializedUser = (User) in.readObject();

        // Verify that the deserialized object is equal to the original
        assertEquals(user.getName(), deserializedUser.getName());
        assertEquals(user.getUserName(), deserializedUser.getUserName());
        assertEquals(user.getBalance(), deserializedUser.getBalance());

        // Password will be null if it's marked transient
        assertEquals(user.getPassword(), deserializedUser.getPassword());
    }
}
