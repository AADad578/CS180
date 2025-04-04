package Server;

import Database.Database;
import User.User;

public interface ServerInterface {

    /**
     * adds a user to the database and saves it
     * 
     * @param user the user to add
     */
    public void addUser(User user);

    /**
     * removes a user from the database if there is an exact match and saves it
     * 
     * @param user the user to add
     */
    public void removeUser(User user);

    /**
     * adds an item to the database and saves it
     * 
     * @param item the item to add
     */
    public void addItem(Item item);

    /**
     * removes an item from the database if there is an exact match and saves it
     * 
     * @param item the item to add
     */
    public void removeItem(Item item);

    /**
     * adds a chat to the database and saves it
     * 
     * @param chat the chat to add
     */
    public void addChat(Chat chat);

    /**
     * removes a chat from the database if there is an exact match and saves it
     * 
     * @param chat the chat to add
     */
    public void removeChat(Chat chat);

    /**
     * Saves the database to a file.
     * FileName = "database.db"
     * Should be called after any change to db
     */
    public void saveDatabase();

    /**
     * recalls the database from a file.
     * FileName = "database.db"
     * Should be called on startup
     */
    public Database recallDatabase();

    /**
     * returns all items that match the search term
     * 
     * @param searchTerm the term to match with
     * @return all items that match
     */
    public Item[] searchItems(String searchTerm);

}
