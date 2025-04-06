package Server;

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
import Database.Database;
import Item.Item;

public class ServerTester {
    private Database db;
    private Item[] items;
    private Chat[] chats;
    private User[] users;
    private Server server;

    @BeforeEach
    void setUp() {

        Item[] items = { new Item("test1", 1, "loc1", "pic1.png"), new Item("test2", 2, "loc2", "pic2.png"),
                new Item("test3", 3, "loc3", "pic3.png") };
        Item[] item1 = { items[0] };
        Item[] item2 = { items[1] };
        Item[] item3 = { items[2] };

        User[] users = { new User("test1", 1, item1, "t1", "TEST1"), new User("test2", 2, item2, "t2", "TEST2"),
                new User("test3", 3, item3, "t3", "TEST3") };
        Chat[] chats = { new Chat(users[0], users[1]), new Chat(users[1], users[2]), new Chat(users[2], users[0]) };
        db = new Database(items, chats, users);
        this.items = items;
        this.chats = chats;
        this.users = users;
    }
}
