package Client;

import Chat.Chat;
import Item.Item;
import Message.Message;
import User.User;
import java.io.IOException;
import java.util.List;

/**
 * ClientInterface:
 *
 * The framework for creating the Client class, and defines which functionalities must exist in a client class
 *
 * @version 4/18/2025
 * Author: Karthik Nandagiri
 */
public interface ClientInterface {

    /**
     * Establishes a connection to the remote server.
     */
    void connectToServer(String address, int port) throws IOException;

    /**
     * Gracefully disconnects from the server.
     */
    void disconnectFromServer();

    /**
     * Sends a serialized request object to the server.
     * @throws IOException if unable to send
     */
    void sendRequest(Object request) throws IOException;

    /**
     * Receives a response object from the server.
     * @throws IOException if the stream fails
     * @throws ClassNotFoundException if the response object cannot be cast
     */
    Object receiveResponse() throws IOException, ServerResponseException;

    /**
     * Creates a new user in the system.
     * @param user User object to register
     */
    void createNewUser(User user) throws IOException, ServerResponseException;

    /**
     * Adds a new item to the system.
     * @param item Item object to add
     */
    void createNewItem(Item item) throws IOException, ServerResponseException;

    /**
     * Creates a new chat between users.
     * @param chat Chat object containing user1 and user2
     */
    void createNewChat(Chat chat) throws IOException, ServerResponseException;

    /**
     * Adds a message to an existing chat.
     * @param message Message object to add
     */
    void addMessage(Message message) throws IOException, ServerResponseException;

    /**
     * Logs in a user.
     * @param user User with username and password to check
     */
    void logInUser(User user) throws IOException, ServerResponseException;

    /**
     * Returns the current list of users in the system.
     *
     * @return list of users
     */
    User[] getUsers() throws IOException, ServerResponseException;

    /**
     * Returns the list of chats a given user is part of.
     * @param user the user to find chats for
     * @return list of Chat objects
     */
    Chat[] getChats(User user) throws IOException, ServerResponseException;

    /**
     * Searches for items matching a term.
     * @param term string to search by
     * @return list of matching Item objects
     */
    Item[] searchItems(String term) throws IOException, ServerResponseException;

    void updateUser(User user) throws IOException, ServerResponseException;

    /**
     * Returns true if the client is currently connected.
     * @return true if connected
     */
    boolean isConnected();
}
