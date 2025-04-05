/**
 * Team Project Phase 1 -- Message Interface
 *
 * This interface is for the interface for the
 * Message Class
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 5, 2025
 *
 */
public interface MessageInterface {
    /**
     * A toString() method that returns a formatted String including
     * message content, time sent, sender, and receiver
     */
    String toString();

    /**
     * A equals() method that determines whether two Message objects
     * are equal to each other
     */
    boolean equals(Object o);

    /**
     * A getter method for message content
     */
    String getMessageContent();

    /**
     * A getter method for time sent
     */
    int getTimeSent();

    /**
     * A getter method for message receiver
     */
    User getReceiver();

    /**
     * A getter method for message sender
     */
    User getSender();

    /**
     * A setter method for message content
     */
    void setMessageContent(String messageContent);

    /**
     * A setter method for time sent
     */
    void setTimeSent(int timeSent);

    /**
     * A setter method for message sender
     */
    void setSender(User sender);

    /**
     * A setter method for message receiver
     */
    void setReceiver(User receiver);
}
