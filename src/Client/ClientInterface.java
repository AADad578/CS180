package Client;

import Chat.Chat;
import Item.Item;

import java.util.ArrayList;

/**
 * Team Project Phase 1 -- ClientInterface
 * <p>
 * The ClientInterface defines the functionality of the Client(TO BE IMPLEMENTED IN PHASE 2 ) class,
 * such as managing Items, Users, Chats on the client side.
 * <p/>
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public interface ClientInterface {

    void connectToServer(String address, int port);

    void disconnectFromServer();

    ArrayList<Chat> receiveChats();

    ArrayList<Item> receiveItems();

    void sendMessage(String message);

    void sendNewItem(Item item);

    boolean login(String username, String password);

    boolean payment(double amount);

}
