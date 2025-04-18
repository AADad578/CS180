package Client;

import Chat.Chat;
import Item.Item;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Client
 *
 * Handles the User interactions with the Server
 *
 * @version 4/15/2025
 *
 * @author Karthik Nandagiri
 */
public class Client implements ClientInterface {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final Object ioLock = new Object(); // Lock for I/O
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
        sendRequest("LOGIN:" + username + ":" + password);
        Object response = receiveResponse();
        if (response instanceof Boolean && (Boolean) response) {
            this.currentUsername = username;
            return true;
        }
        return false;
    }

    @Override
    public List<Item> getAvailableItems() throws IOException, ClassNotFoundException {
        sendRequest("GET_ITEMS");
        Object response = receiveResponse();

        if (response instanceof List<?>) {
            return (List<Item>) response;
        }
        throw new IOException("Unexpected response from server.");
    }

    @Override
    public List<Chat> getUserChats() throws IOException, ClassNotFoundException {
        sendRequest("GET_CHATS:" + currentUsername);
        Object response = receiveResponse();

        if (response instanceof List<?>) {
            return (List<Chat>) response;
        }
        throw new IOException("Unexpected response from server.");
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
