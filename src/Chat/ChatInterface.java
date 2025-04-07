package Chat;

import User.User;
import Message.Message;

import java.util.List;


/**
 * Team Project Phase 1 -- ChatInterface
 * <p>
 * The ChatInterface defines the contract for chat functionality,
 * including managing users, messages, and equality checks.
 * </p>
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public interface ChatInterface {

    User[] getUsers();

    List<Message> getMessages();

    void addMessage(String message, User sentBy, int timesent) throws MessageError;

    boolean equals(Object o);

    String toString();
}

