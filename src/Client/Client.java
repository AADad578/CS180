package Client;

import Chat.Chat;
import Item.Item;
import Message.Message;

import java.util.List;

public interface Client {

    void connectToServer(String address, int port);

    void disconnectFromServer();

    List<Chat> recieveChats();

    List<Item> receiveItems();

    void sendMessage(Message message);

    void sendNewItem(Item item);

    boolean login(String username, String password);

    boolean payment(double amount);

}
