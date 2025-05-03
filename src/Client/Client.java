package Client;

import Chat.Chat;
import Item.Item;
import Message.Message;
import User.User;
import java.io.*;
import java.net.Socket;

import Server.Request;

/**
 * Client:
 *
 * Implementation using object-based Request architecture.
 * All actions use structured Request objects.
 * Thread-safe. Throws ServerResponseException when server returns errors.
 *
 * @version 4/18/2025
 * @author Karthik Nandagiri
 */
public class Client implements ClientInterface {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final Object ioLock = new Object();
    private volatile boolean connected;
    private String host;
    private volatile String currentUsername;

    /**
     * Connects to the server using the specified address and port.
     * @param address the server address
     * @param port the port number to connect to
     * @throws IOException if connection fails
     */
    @Override
    public void connectToServer(String address, int port) throws IOException {
        synchronized (this) {
            this.host = address;
            this.socket = new Socket(host, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.connected = true;
        }
    }

    /**
     * Disconnects from the server and closes all resources.
     */
    @Override
    public void disconnectFromServer() {
        synchronized (this) {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException e) {
                System.err.println("Error while disconnecting: " + e.getMessage());
            } finally {
                connected = false;
            }
        }
    }

    /**
     * Sends a request object to the server.
     * @param request the request to send
     * @throws IOException if sending fails
     */
    @Override
    public void sendRequest(Object request) throws IOException {
        if (!connected) throw new IOException("Not connected to server.");
        synchronized (ioLock) {
            out.writeObject(request);
            out.flush();
        }
    }

    /**
     * Receives and processes the server's response.
     * @return the response Request object
     * @throws IOException if receiving fails
     * @throws ServerResponseException if server returns an error action
     */
    @Override
    public Request receiveResponse() throws IOException, ServerResponseException {
        if (!connected) throw new IOException("Not connected to server.");
        Request response;
        synchronized (ioLock) {
            try {
                response = (Request) in.readObject();
                if (response.getAction().equals("ERROR")) {
                    throw new ServerResponseException((String) response.getPayload());
                }
            } catch (ClassCastException | ClassNotFoundException e) {
                throw new ServerResponseException("Failed to transmit data correctly");
            }
        }
        return response;
    }

    /**
     * Sends a request to create a new user on the server.
     * @param user the user to create
     * @throws IOException if sending fails
     * @throws ServerResponseException if server returns an error
     */
    @Override
    public void createNewUser(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("CreateNewUser", user));
        receiveResponse();
    }

    /**
     * Sends a request to create a new item on the server.
     * @param item the item to create
     * @throws IOException if sending fails
     * @throws ServerResponseException if server returns an error
     */
    @Override
    public void createNewItem(Item item) throws IOException, ServerResponseException {
        sendRequest(new Request("CreateNewItem", item));
        receiveResponse();
    }

    /**
     * Sends a request to create a new chat on the server.
     * @param chat the chat to create
     * @throws IOException if sending fails
     * @throws ServerResponseException if server returns an error
     */
    @Override
    public void createNewChat(Chat chat) throws IOException, ServerResponseException {
        sendRequest(new Request("CreateNewChat", chat));
        receiveResponse();
    }

    /**
     * Sends a message in a chat to the server.
     * @param message the message to send
     * @throws IOException if sending fails
     * @throws ServerResponseException if server returns an error
     */
    @Override
    public void addMessage(Message message) throws IOException, ServerResponseException {
        sendRequest(new Request("AddMessage", message));
        receiveResponse();
    }

    /**
     * Attempts to log in a user by sending credentials to the server.
     * @param user the user to log in
     * @throws IOException if sending fails
     * @throws ServerResponseException if login fails
     */
    @Override
    public void logInUser(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("LogInUser", user));
        receiveResponse();
        this.currentUsername = user.getUserName();
    }

    /**
     * Retrieves the list of users from the server.
     * @return an array of User objects
     * @throws IOException if sending or receiving fails
     * @throws ServerResponseException if server returns an error or payload mismatch
     */
    @Override
    public User[] getUsers() throws IOException, ServerResponseException {
        sendRequest(new Request("GetUsers", null));
        Request response = receiveResponse();
        try {
            return (User[]) response.getPayload();
        } catch (ClassCastException e) {
            throw new ServerResponseException("Payload not a User[]");
        }
    }

    /**
     * Retrieves all chats associated with a given user.
     * @param user the user whose chats to fetch
     * @return an array of Chat objects
     * @throws IOException if sending or receiving fails
     * @throws ServerResponseException if server returns an error or payload mismatch
     */
    @Override
    public Chat[] getChats(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("GetChats", user));
        Request response = receiveResponse();
        try {
            return (Chat[]) response.getPayload();
        } catch (ClassCastException e) {
            throw new ServerResponseException("Payload not a Chat[]");
        }
    }

    /**
     * Searches for items matching the given term.
     * @param term the search keyword
     * @return an array of matching Item objects
     * @throws IOException if sending or receiving fails
     * @throws ServerResponseException if server returns an error or payload mismatch
     */
    @Override
    public Item[] searchItems(String term) throws IOException, ServerResponseException {
        sendRequest(new Request("SearchItems", term));
        Request response = receiveResponse();
        try {
            return (Item[]) response.getPayload();
        } catch (ClassCastException e) {
            throw new ServerResponseException("Payload not a Item[]");
        }
    }

    /**
     * Updates a user's data on the server.
     * @param user the updated user data
     * @throws IOException if sending fails
     * @throws ServerResponseException if update fails
     */
    @Override
    public void updateUser(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("UpdateUser", user));
        receiveResponse();
    }

    /**
     * Sends a request to remove an item from the server.
     *
     * @param item the item to be removed
     * @throws IOException if sending fails or the connection is broken
     * @throws ServerResponseException if the server responds with an error
     */
    @Override
    public void removeItem(Item item) throws IOException, ServerResponseException {
        sendRequest(new Request("RemoveItem", item));
        receiveResponse();
    }

    /**
     * Sends a request to remove a user from the server.
     *
     * @param user the item to be removed
     * @throws IOException if sending fails or the connection is broken
     * @throws ServerResponseException if the server responds with an error
     */
    public void removeUser(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("removeUser", user));
        receiveResponse();
    }


    /**
     * Returns true if the client is connected to the server.
     * @return true if connected, false otherwise
     */
    @Override
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * Returns the username of the currently logged-in user.
     * @return the current username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }
}
