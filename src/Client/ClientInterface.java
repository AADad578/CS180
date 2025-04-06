package Client;

import Chat.Chat;
import Item.Item;
import java.util.ArrayList;

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
