package Database;

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
public interface DatabaseInterface {
    public Item[] getItems();

    public Chat[] getChats();

    public User[] getUsers();

    public void setItems(Item[] items);

    public void setChats(Chat[] chats);

    public void setUsers(User[] users);

    @Override
    public String toString();
}
