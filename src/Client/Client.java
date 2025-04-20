package Client;

import Chat.Chat;
import Item.Item;
import Message.Message;
import User.User;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Client:
 *
 * Final Phase 2 implementation using object-based Request architecture.
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

    @Override
    public boolean login(String username, String password) throws IOException, ClassNotFoundException {
        if (!connected)
            throw new IOException("Not connected to server.");
        sendRequest(new Request("Login", new String[]{username, password}));

        return false;
    }

    @Override
    public List<Item> getAvailableItems() throws IOException, ClassNotFoundException {
        sendRequest(new Request("GetAvailableItems", "All items"));
        return (List<Item>) this.receiveResponse();
    }

    @Override
    public List<Chat> getUserChats() throws IOException, ClassNotFoundException {
        sendRequest(new Request("GetUserChats", this.currentUsername));
        return (List<Chat>) this.receiveResponse();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean createNewUser(User user) throws IOException, ClassNotFoundException, ServerResponseException {
        sendRequest(new Request("CreateNewUser", user));
        String response = (String) this.receiveResponse();
        if (!response.equals("OK")){
            throw new ServerResponseException(response);
        }
        return true;
    }

    @Override
    public boolean createNewItem(Item item) throws IOException, ClassNotFoundException, ServerResponseException {
        this.sendRequest(new Request("CreateNewItem", item));
        String response = (String) this.receiveResponse();
        if (!response.equals("OK")){
            throw new ServerResponseException(response);
        }
        return true;
    }

    @Override
    public boolean createNewChat(Chat chat) throws IOException, ClassNotFoundException, ServerResponseException {
        this.sendRequest(new Request("CreateNewChat", chat));
        String response = (String) this.receiveResponse();
        if (!response.equals("OK")){
            throw new ServerResponseException(response);
        }
        return true;
    }

    @Override
    public boolean addMessage(Message message) throws IOException, ClassNotFoundException, ServerResponseException {
        this.sendRequest(new Request("AddMessage", message));
        String response = (String) this.receiveResponse();
        if (!response.equals("OK")){
            throw new ServerResponseException(response);
        }
        return true;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
