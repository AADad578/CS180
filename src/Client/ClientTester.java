package Client;

import static org.junit.jupiter.api.Assertions.*;

import Chat.Chat;
import User.User;
import Message.Message;
import org.junit.jupiter.api.*;

import java.util.Arrays;

/**
 * ClientTester
 * 
 * tests the client class
 * 
 * @version 4/6/2025
 * 
 * @author Karthik Nandagiri
 */
public class ClientTester {
    /**
     * Turns on Client, connects to server and sends a bunch of commands to test it
     * Must have server running on port 8000 before this runs
     */
    @Test
    void testClientServerComms() {
        Client client = new Client();
        try {
            client.connectToServer("localhost", 8000);

            User[] usersLoc = { new User("test1", 1, "t1", "TEST1"),
                    new User("test2", 2, "t2", "TEST2"),
                    new User("test3", 3, "t3", "TEST3"),
                    new User("test4", 4, "t4", "TEST4"),};

            client.createNewUser(usersLoc[0]);
            System.out.println("Create New User");
            client.logInUser(usersLoc[0]);
            System.out.println("LogIn User");
            client.createNewUser(usersLoc[1]);
            System.out.println("Create New User2");
            client.createNewChat(new Chat(usersLoc[0], usersLoc[1]));
            System.out.println("Create New Chat");
            client.addMessage(new Message("this is a message", 100, usersLoc[0], usersLoc[1]));
            System.out.println("Add Message");
            System.out.println(Arrays.toString(client.getUsers()));
            System.out.println(Arrays.toString(client.getChats(usersLoc[0])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
