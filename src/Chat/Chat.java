package Chat;

import java.io.Serializable;
import java.util.ArrayList;

import Message.Message;
import User.User;

/**
 * Team Project Phase 1 -- Chat Class
 * <p>
 * This class creates an object, that stores all the messages in-between 2 Users
 * </p>
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public class Chat implements ChatInterface, Serializable {

    /**
     * The two users participating in the chat.
     */
    private final User[] users;

    /**
     * The list of messages exchanged in the chat.
     */
    private final ArrayList<Message> messages;

    /**
     * Constructs an empty chat with no users or messages.
     */
    public Chat() {
        users = new User[2];
        messages = new ArrayList<>();
    }

    /**
     * Constructs a chat between two users with no initial messages.
     *
     * @param user1 The first user in the chat.
     * @param user2 The second user in the chat.
     */
    public Chat(User user1, User user2) {
        users = new User[2];
        users[0] = user1;
        users[1] = user2;
        messages = new ArrayList<>();
    }

    /**
     * Constructs a chat between two users with an initial list of messages.
     *
     * @param user1    The first user in the chat.
     * @param user2    The second user in the chat.
     * @param messages The initial list of messages in the chat.
     */
    public Chat(User user1, User user2, ArrayList<Message> messages) {
        users = new User[2];
        users[0] = user1;
        users[1] = user2;
        this.messages = messages;
    }

    /**
     * Adds a message to the chat if the sender is a participant in the chat.
     *
     * @param message  The content of the message.
     * @param sentBy   The user sending the message.
     * @param timesent The timestamp of when the message was sent.
     * @throws MessageException If the sender is not a participant in the chat.
     */
    @Override
    public void addMessage(String message, User sentBy, int timesent) throws MessageException {
        if (sentBy == null || (!sentBy.equals(users[0]) && !sentBy.equals(users[1]))) {
            throw new MessageException("Sender is not a participant in this chat.");
        }

        // Determining which user in the array sent the message
        User sender = sentBy.equals(users[0]) ? users[0] : users[1];
        User receiver = sender.equals(users[0]) ? users[1] : users[0];

        Message m = new Message(message, timesent, sender, receiver);
        messages.add(m);
    }

    /**
     * Returns the two users participating in the chat.
     *
     * @return An array containing the two users in the chat.
     */
    @Override
    public User[] getUsers() {
        return users;
    }

    /**
     * Returns the list of messages exchanged in the chat.
     *
     * @return An ArrayList of {@link Message} objects representing the chat history.
     */
    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Checks if this chat is equal to another object.
     * Two chats are considered equal if they involve the same two users, regardless of order.
     *
     * @param o The object to compare with this chat.
     * @return True if the chats are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chat))
            return false;
        Chat chat = (Chat) o;

        User[] users1 = chat.getUsers();
        User[] users2 = this.getUsers();

        return (users1[0].equals(users2[0]) && users1[1].equals(users2[1])) ||
                (users1[0].equals(users2[1]) && users1[1].equals(users2[0]));
    }

    /**
     * Returns a string representation of the chat, including the users and messages.
     *
     * @return A formatted string with user details and chat messages.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chat { ");
        for (User u : users) {
            sb.append(u.getUserName()).append(" (").append(u.getName()).append(")");
            sb.append("\n");
        }

        for (Message m : messages) {
            sb.append(m.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public void addMessage(Message message) throws MessageException {
        if (message.getSender() == null || (!message.getSender().equals(users[0])
                && !message.getSender().equals(users[1]))) {
            throw new MessageException("Sender is not a participant in this chat.");
        }
        if (message.getReceiver() == null || (!message.getReceiver().equals(users[0])
                && !message.getReceiver().equals(users[1]))) {
            throw new MessageException("Reciever is not a participant in this chat.");
        }

        messages.add(message);
    }
}