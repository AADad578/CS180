package Client;

import Chat.Chat;
import Item.Item;
import Message.Message;
import User.User;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import Server.Request;

/**
 * Client:
 *
 * Implementation using object-based Request architecture.
 * All actions use structured Request objects.
 * Thread-safe. Throws ServerResponseException when server returns errors.
 *
 * @version 4/18/2025
 * Author: Karthik Nandagiri
 */
public class Client implements ClientInterface {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final Object ioLock = new Object();
    private volatile boolean connected;
    private String host;
    private int port;
    private volatile String currentUsername;

    @Override
    public void connectToServer(String address, int port) throws IOException {
        synchronized (this) {
            this.host = address;
            this.port = port;
            this.socket = new Socket(host, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.connected = true;
        }
    }

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

    @Override
    public void sendRequest(Object request) throws IOException {
        if (!connected) throw new IOException("Not connected to server.");
        synchronized (ioLock) {
            out.writeObject(request);
            out.flush();
        }
    }

    @Override
    public Request receiveResponse() throws IOException, ServerResponseException {
        if (!connected) throw new IOException("Not connected to server.");
        Request response;
        synchronized (ioLock) {
            try {
                response = (Request) in.readObject();
                if (response.getAction() == "Error") {
                    throw new ServerResponseException((String) response.getPayload());
                }
            } catch (ClassCastException | ClassNotFoundException e) {
                throw new ServerResponseException("Failed to transmit data correctly");
            }
        }
        return response;
    }

    @Override
    public void createNewUser(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("CreateNewUser", user));
        receiveResponse();
    }

    @Override
    public void createNewItem(Item item) throws IOException, ServerResponseException {
        sendRequest(new Request("CreateNewItem", item));
        receiveResponse();
    }

    @Override
    public void createNewChat(Chat chat) throws IOException, ServerResponseException {
        sendRequest(new Request("CreateNewChat", chat));
        receiveResponse();
    }

    @Override
    public void addMessage(Message message) throws IOException, ServerResponseException {
        sendRequest(new Request("AddMessage", message));
        receiveResponse();
    }

    @Override
    public void logInUser(User user) throws IOException, ServerResponseException {
        sendRequest(new Request("LogInUser", user));
        receiveResponse();
        this.currentUsername = user.getUserName();
    }

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

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
