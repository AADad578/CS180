package Chat;

import User.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
    private User[] users;
    //private ArrayList<Message> messages;

    public Chat() {
        users = new User[2];
        //messages = new ArrayList<>();
    }

    public Chat(User user1, User user2) {
        users = new User[2];
        users[0] = user1;
        users[1] = user2;
        //messages = new ArrayList<>();
    }

//    public Chat(User user1, User user2/*, ArrayList<Message> messages*/) {
//        users = new User[2];
//        users[0] = user1;
//        users[1] = user2;
//        //this.messages = messages;
//    }

    public void addMessage(String message, User user) throws MessageError {
        if (user == null || !(user.equals(users[0]) && !user.equals(users[1]))) {
            throw new MessageError("The users are not the same as the chat");
        }
        //Message m = new Message(message, user);
        //messages.add(m);
        //m = null;
    }


}
