package Client;

import Chat.Chat;
import Item.Item;
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
     * Logs in the user and stores session state, if any.
     * @param username the username to log in
     * @param password the password associated
     * @return true if login is successful
     * @throws IOException if network communication fails
     * @throws ClassNotFoundException if deserialization fails
     * @throws ServerResponseException if login fails due to invalid credentials
     */
    boolean login(String username, String password) throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Returns a list of items available to the user.
     * @return list of Item objects
     * @throws IOException if data retrieval fails
     * @throws ClassNotFoundException if casting the result fails
     * @throws ServerResponseException if the server responds with an error
     */
    List<Item> getAvailableItems() throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Returns the list of chats/messages associated with the user.
     * @return list of Chat objects
     * @throws IOException if communication fails
     * @throws ClassNotFoundException if deserialization fails
     * @throws ServerResponseException if the server indicates a problem
     */
    List<Chat> getUserChats() throws IOException, ClassNotFoundException, ServerResponseException;

    /**
     * Returns true if the client is currently connected.
     * @return true if connected
     */
    boolean isConnected();
}
