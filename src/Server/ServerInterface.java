package Server;

import java.util.ArrayList;

import Chat.Chat;
import Item.Item;
import User.User;

public interface ServerInterface {

    /**
     * adds a user to the database and saves it
     * 
     * @param user the user to add
     * @throws InvalidInputException
     */
    public void addUser(User user) throws InvalidInputException;

    /**
     * removes a user from the database if there is an exact match and saves it
     * 
     * @param user the user to add
     * @throws InvalidInputException
     */
    public void removeUser(User user) throws InvalidInputException;

    /**
     * adds an item to the database and saves it
     * 
     * @param item the item to add
     */
    public void addItem(Item item) throws InvalidInputException;

    /**
     * removes an item from the database if there is an exact match and saves it
     * 
     * @param item the item to add
     * @throws InvalidInputException
     */
    public void removeItem(Item item) throws InvalidInputException;

    /**
     * adds a chat to the database and saves it
     * 
     * @param chat the chat to add
     */
    public void addChat(Chat chat) throws InvalidInputException;

    /**
     * removes a chat from the database if there is an exact match and saves it
     * 
     * @param chat the chat to add
     * @throws InvalidInputException
     */
    public void removeChat(Chat chat) throws InvalidInputException;

    /**
     * Saves the database to a file.
     * FileName = "database.db"
     * Should be called after any change to db
     */
    public void saveDatabase();

    /**
     * returns all items that match the search term
     * 
     * @param searchTerm the term to match with
     * @return all items that match
     */
    public ArrayList<Item> searchItems(String searchTerm);

}
