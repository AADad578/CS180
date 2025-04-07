package Database;

import java.io.Serializable;
import java.util.Arrays;

import Chat.Chat;
import Item.Item;
import User.User;

/**
 * DatabaseInterface
 * 
 * Acts as an interface for the Database class,
 * This will be written into a file and will contain all the users, items, and
 * chats
 * 
 * @author Ankur Raghavan
 * 
 * @version 4/3/25
 */
public class Database implements Serializable, DatabaseInterface {

    private Item[] items;
    private Chat[] chats;
    private User[] users;

    public Database(Item[] items, Chat[] chats, User[] users) {
        this.items = items;
        this.chats = chats;
        this.users = users;
    }

    @Override
    public Item[] getItems() {
        return items;
    }

    @Override
    public void setItems(Item[] items) {
        this.items = items;
    }

    @Override
    public Chat[] getChats() {
        return chats;
    }

    @Override
    public void setChats(Chat[] chats) {
        this.chats = chats;
    }

    @Override
    public User[] getUsers() {
        return users;
    }

    @Override
    public void setUsers(User[] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return String.format("Database: \n    Items: %s\n    Chats: %s\n    Users: %s", Arrays.toString(items),
                Arrays.toString(chats), Arrays.toString(users));
    }
}
