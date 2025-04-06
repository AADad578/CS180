package Chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import Message.Message;
import User.User;

public class Chat implements ChatInterface, Serializable {
    private final User[] users;
    private final ArrayList<Message> messages;

    public Chat() {
        users = new User[2];
        messages = new ArrayList<>();
    }

    public Chat(User user1, User user2) {
        users = new User[2];
        users[0] = user1;
        users[1] = user2;
        messages = new ArrayList<>();
    }

    public Chat(User user1, User user2, ArrayList<Message> messages) {
        users = new User[2];
        users[0] = user1;
        users[1] = user2;
        this.messages = messages;
    }

    @Override
    public void addMessage(String message, User sentBy, int timesent) throws MessageError {
        if (sentBy == null || (!sentBy.equals(users[0]) && !sentBy.equals(users[1]))) {
            throw new MessageError("Sender is not a participant in this chat.");
        }

        // Determining which user in the array sent the message
        User sender = sentBy.equals(users[0]) ? users[0] : users[1];
        User receiver = sender.equals(users[0]) ? users[1] : users[0];

        Message m = new Message(message, timesent, sender, receiver);
        messages.add(m);
    }

    @Override
    public User[] getUsers() {
        return users;
    }

    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
      return String.format("Chat {Users: %s\nMessages: $%s}", Arrays.toString(users), messages);
    };
}
