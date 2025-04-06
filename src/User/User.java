package User;

import java.io.Serializable;
import Item.Item;

public class User implements Serializable, UserInterface {
    private String username;
    private String name;
    private double balance;
    private Item[] items;
    private String password;

    public User(String name, double balance, Item[] items, String username, String password) {
        this.name = name;
        this.balance = balance;
        this.items = items;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setUserName(String userName) {
        this.username = userName;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: \n" +
                "   username: " + username + "\'\n" +
                "   name: " + name + "\'\n" +
                "   balance: " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return user.getUserName().equals(this.getUserName());
    }

}