package Chat;

import User.User;
import Message.Message;
import java.util.List;

public interface ChatInterface {

    User[] getUsers();

    List<Message> getMessages();

    void addMessage(String message, User sentBy, int timesent) throws MessageError;
}

