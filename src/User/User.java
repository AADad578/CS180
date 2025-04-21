package User;

import java.io.Serializable;

/**
 * Team Project Phase 1 -- User
 *
 * <p>
 * The User class represents a user within the marketplace system.
 * It provides functionality for managing user details such as name, balance,
 * username, password, and associated items. The class implements the
 * UserInterface and Serializable for interface compliance
 * and object serialization.
 * </p>
 * This class is part of the User package and interacts with the Item class.
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public class User implements Serializable, UserInterface {
    private String username;
    private String name;
    private double balance;
    private String password;

    /**
     * Constructs a new User with the specified details.
     *
     * @param name     the full name of the user
     * @param balance  the initial balance of the user
     * @param username the unique username for login
     * @param password the password for authentication
     */
    public User(String name, double balance, String username, String password) {
        this.name = name;
        this.balance = balance;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructs a new User with the specified details.
     *
     * @param username the unique username for login
     * @param password the password for authentication
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the user's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Updates the user's full name.
     *
     * @param name the new name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's current balance.
     *
     * @return the user's balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * Updates the user's balance.
     *
     * @param balance the new balance to set
     */
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Updates the user's username.
     *
     * @param userName the new username to set
     */
    @Override
    public void setUserName(String userName) {
        this.username = userName;
    }

    /**
     * Returns the user's username.
     *
     * @return the user's username
     */
    @Override
    public String getUserName() {
        return username;
    }

    /**
     * Updates the user's password.
     *
     * @param password the new password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the User object, excluding the password
     * and items.
     *
     * @return a formatted string summarizing the user's public data
     */
    @Override
    public String toString() {
        return "User: \n" +
                "   username: " + username + "\'\n" +
                "   name: " + name + "\'\n" +
                "   balance: " + balance;
    }

    /**
     * Compares this user to another object for equality based on username.
     *
     * @param o the object to compare with
     * @return {@code true} if the object is a User with the same username;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return user.getUserName().equals(this.getUserName());
    }
}
