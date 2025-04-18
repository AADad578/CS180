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
     * Gracerver.
     */
    void disconnectFromServer();

    /**
     * Sends a serialized request object to the server.
     */
    void sendRequest(Object request) throws IOException;

    /**
     * Receives a response object from the server.
     */
    Object receiveResponse() throws IOException, ClassNotFoundException;

    /**
     * Logs in the user and stores session state, if any.
     * Returns true if login is successful.
     */
    boolean login(String username, String password) throws IOException, ClassNotFoundException;

    /**
     * Returns a list of items available to the user.
     */
    List<Item> getAvailableItems() throws IOException, ClassNotFoundException;

    /**
     * Returns the list of chats/messages associated with the user.
     */
    List<Chat> getUserChats() throws IOException, ClassNotFoundException;

    /**
     * Returns true if the client is currently connected.
     */
    boolean isConnected();
}
