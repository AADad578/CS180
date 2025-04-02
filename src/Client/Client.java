package Client;

public interface Client {

    void connectToServer(String address, int port);

    void disconnectFromServer();

    //List<Chat> recieveChats(); TO BE ADDED

    //List<Item> receiveItems(); TO BE ADDED

    void sendMessage(String message);

    //void sendNewItem(Item item); TO BE ADDED

    boolean login(String username, String password);

    boolean payment(double amount);

}
