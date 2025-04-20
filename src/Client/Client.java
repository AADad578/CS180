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
    public Object receiveResponse() throws IOException, ClassNotFoundException {
        if (!connected) throw new IOException("Not connected to server.");
        synchronized (ioLock) {
            return in.readObject();
        }
    }

    private void handleOkResponse() throws IOException, ClassNotFoundException, ServerResponseException {
        Object response = receiveResponse();
        if (!(response instanceof String str) || !str.equals("OK")) {
            throw new ServerResponseException(response.toString());
        }
    }

    @Override
    public void createNewUser(User user) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("CreateNewUser", user));
        handleOkResponse();
    }

    @Override
    public void createNewItem(Item item) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("CreateNewItem", item));
        handleOkResponse();
    }

    @Override
    public void createNewChat(Chat chat) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("CreateNewChat", chat));
        handleOkResponse();
    }

    @Override
    public void addMessage(Message message) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("AddMessage", message));
        handleOkResponse();
    }

    @Override
    public void logInUser(User user) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("LogInUser", user));
        handleOkResponse();
        this.currentUsername = user.getUserName();
    }

    @Override
    public List<User> getUsers() throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("GetUsers", null));
        Object response = receiveResponse();
        if (response instanceof List<?> list && !list.isEmpty() && list.get(0) instanceof User) {
            return (List<User>) list;
        } else if (response instanceof String && response.equals("OK")) {
            return new ArrayList<>();
        } else {
            throw new ServerResponseException(response.toString());
        }
    }

    @Override
    public List<Chat> getChats(User user) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("GetChats", user));
        Object response = receiveResponse();
        if (response instanceof List<?> list && !list.isEmpty() && list.get(0) instanceof Chat) {
            return (List<Chat>) list;
        } else if (response instanceof String && response.equals("OK")) {
            return new ArrayList<>();
        } else {
            throw new ServerResponseException(response.toString());
        }
    }

    @Override
    public List<Item> searchItems(String term) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("SearchItems", term));
        Object response = receiveResponse();
        if (response instanceof List<?> list && !list.isEmpty() && list.get(0) instanceof Item) {
            return (List<Item>) list;
        } else if (response instanceof String && response.equals("OK")) {
            return new ArrayList<>();
        } else {
            throw new ServerResponseException(response.toString());
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
