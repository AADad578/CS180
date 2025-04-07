package User;

/**
 * Team Project Phase 1 -- UserInterface
 * <p>
 * The UserInterface defines the contract for user-related functionality
 * in the marketplace system. Implementing classes must provide mechanisms for
 * managing user identity, authentication, and account balance.
 * <p/>
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public interface UserInterface {

    /**
     * Returns the full name of the user.
     *
     * @return the user's name
     */
    String getName();

    /**
     * Updates the user's full name.
     *
     * @param name the new name to assign to the user
     */
    void setName(String name);

    /**
     * Sets the username of the user.
     *
     * @param userName the new username to assign
     */
    void setUserName(String userName);

    /**
     * Retrieves the current username.
     *
     * @return the user's username
     */
    String getUserName();

    /**
     * Updates the user's password.
     *
     * @param password the new password to assign
     */
    void setPassword(String password);

    /**
     * Retrieves the user's password.
     *
     * @return the user's password
     */
    String getPassword();

    /**
     * Sets the user's account balance.
     *
     * @param balance the new balance to assign
     */
    void setBalance(double balance);

    /**
     * Returns the user's current account balance.
     *
     * @return the user's balance
     */
    double getBalance();

    /**
     * Returns a string representation of the user object.
     *
     * @return a string describing the user's public details
     */
    String toString();

    /**
     * Compares this user to another object for equality.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal based on implementation;
     *         {@code false} otherwise
     */
    boolean equals(Object o);
}
