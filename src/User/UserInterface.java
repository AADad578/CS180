package User;

public interface UserInterface {
    String getName();

    void setName(String name);

    void setUserName(String userName);

    String getUserName();

    void setPassword(String password);

    String getPassword();

    void setBalance(double balance);

    double getBalance();

    String toString();

    boolean equals(Object o);

    // If we ever plan to make a USER page
    // public boolean addItem(Item item);
    // public boolean removeItem(Item item);
    // public void setItems(Item[] item);
    // public Item[] getItems();
}