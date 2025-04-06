package Chat;

import java.io.Serializable;
import java.util.ArrayList;


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
    public boolean equals(Object o) {
        if (!(o instanceof Chat)) return false;
        Chat chat = (Chat) o;

        User[] users1 = chat.getUsers();
        User[] users2 = this.getUsers();

        return (users1[0].equals(users2[0]) && users1[1].equals(users2[1])) ||
                (users1[0].equals(users2[1]) && users1[1].equals(users2[0]));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User u : users) {
            sb.append(u.getUserName()).append(" (").append(u.getName()).append(")");
            sb.append("\n");
        }

        for (Message m : messages) {
            sb.append(m.toString()).append("\n");
        }

        return sb.toString();
    }
}
