package Client;

import Chat.Chat;
import Item.Item;
import java.util.ArrayList;

public interface ClientInterface {

    void connectToServer(String address, int port);

    void disconnectFromServer();

    ArrayList<Chat> receiveChats();

    ArrayList<Item> receiveItems();

    boolean login(String username, String password);

}
