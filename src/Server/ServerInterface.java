package Server;

import java.util.ArrayList;

import Chat.Chat;
import Item.Item;
import Message.Message;
import User.User;

/**
 * ServerInterface
 * 
 * The interface for the server class
 * 
 * @version 4/6/2025
 * 
 * @author Ankur Raghavan
 */
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
     * gets all users from the database
     * 
     * @return List of all users
     */
    public User[] getUsers();

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
    public Item[] searchItems(String searchTerm);

    /**
     * Checks a user's username and password against a list of all the usernames and passwords
     * @param user the user object that contains the username and password
     * @throws InvalidInputException if password is wrong or username is not found.
     */
    public void logIn (User user) throws InvalidInputException;

    /**
     * Gets the chat between two users
     * @param user1 
     * @param user2 
     * @return one chat that is the one between the two users
     * @throws InvalidInputException IF useres have the same username or there aren't any chats between them.
     */
    public Chat getChat(User user1, User user2) throws InvalidInputException;


    /**
     * Adds a message to the chat between two users. If no chat is found. a new one is created
     * @param message the message to add
     * @throws InvalidInputException If the users have the same username or the users aren't in the chat.
     */
    public void addMessage(Message message) throws InvalidInputException;

    /**
     * Gets all chats from/to one user
     * @param user
     * @return all chats that include the user
     */
    public Chat[] getChats(User user);

    /**
     * The function that contains all of the logic for recieving and responding to client communications
     * 
     * Gets called on thread startup and stays until client disconnects.
     */
    public void run();
}
