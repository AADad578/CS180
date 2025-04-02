package User;

public interface UserInterface {
    public String getName();

    public void setName(String name);

    public void setUserName(String userName);

    public String getUserName();

    public void setPassword(String password);

    public String getPassword();

    public void setBalance(double balance);

    public double getBalance();

    public String toString();


    // If we ever plan to make a USER page
    // public boolean addItem(Item item);
    // public boolean removeItem(Item item);
    // public void setItems(Item[] item);
    // public Item[] getItems();
}