package Message;

import java.io.Serializable;

import User.User;
/**
 * Team Project Phase 1 -- Message Class
 *
 * This class is for messages between users in a marketplace
 * where items can be bought or sold
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 5, 2025
 *
 */
public class Message implements MessageInterface, Serializable {
    private String messageContent; //message content for a message
    private int timeSent; //time a message was sent in military time
    private User sender; //sender of a message
    private User receiver; //receiver for a message

    /**
     * Message Constructor Method
     * with four parameters
     *
     * @param messageContent message content for a message
     * @param timeSent time a message was sent in military time
     * @param sender sender of a message
     * @param receiver receiver for a message
     */
    public Message(String messageContent, int timeSent, User sender, User receiver) {
        this.messageContent = messageContent;
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * A toString() method that returns a formatted String including
     * message content, time sent, sender, and receiver
     *
     * @return the formatted String
     */
    public String toString() {
        return String.format("To: %s\nMessage: %s\nFrom: %s\nTime: %d\n",
                receiver.getName(), messageContent, sender.getName(), timeSent);
    }

    /**
     * A equals() method that determines whether two Message objects
     * are equal to each other
     *
     * @return whether two Message objects are equal to each other
     * @method getMessageContent() of Message Class
     * @method getTimeSent() of Message Class
     * @method getReceiver() of Message Class
     * @method getSender() of Message Class
     */
    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message m = (Message) o; //object o is cast as a Message object
            return this.messageContent.equals(m.getMessageContent()) && this.timeSent == m.getTimeSent()
                    && this.sender.equals(m.getSender()) && this.receiver.equals(m.getReceiver());
        }
        return false;
    }

    /**
     * A getter method for message content
     *
     * @return message content
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * A getter method for message time sent
     *
     * @return message time sent
     */
    public int getTimeSent() {
        return timeSent;
    }

    /**
     * A getter method for message receiver
     *
     * @return message receiver
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * A getter method for message sender
     *
     * @return message sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * A setter method for message content
     *
     * @param messageContent message content
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * A setter method for message time sent
     *
     * @param timeSent message time sent
     */
    public void setTimeSent(int timeSent) {
        this.timeSent = timeSent;
    }

    /**
     * A setter method for message sender
     *
     * @param sender message sender
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * A setter method for message receiver
     *
     * @param receiver message receiver
     */
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
