package Client;

import Chat.Chat;
import Item.Item;
import Message.Message;
import User.User;
import java.io.IOException;
import java.util.List;

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
    Object receiveResponse() throws IOException, ClassNotFoundException;

    /**
     * Creates a new user in the system.
     * @param user User object to register
     */
    void createNewUser(User user) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Adds a new item to the system.
     * @param item Item object to add
     */
    void createNewItem(Item item) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Creates a new chat between users.
     * @param chat Chat object containing user1 and user2
     */
    void createNewChat(Chat chat) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Adds a message to an existing chat.
     * @param message Message object to add
     */
    void addMessage(Message message) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Logs in a user.
     * @param user User with username and password to check
     */
    void logInUser(User user) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Returns the current list of users in the system.
     * @return list of users
     */
    List<User> getUsers() throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Returns the list of chats a given user is part of.
     * @param user the user to find chats for
     * @return list of Chat objects
     */
    List<Chat> getChats(User user) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Searches for items matching a term.
     * @param term string to search by
     * @return list of matching Item objects
     */
    List<Item> searchItems(String term) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Returns true if the client is currently connected.
     * @return true if connected
     */
    boolean isConnected();
}
