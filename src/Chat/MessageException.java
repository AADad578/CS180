package Chat;

/**
 * Team Project Phase 1 -- MessageError
 * <p>
 * The MessageError Exception is thrown whenever there is a problem with messaging in Chat
 *
 * @author Karthik Nandagiri
 * @version April 6, 2025
 */
public class MessageException extends Exception {
    public MessageException(String message) {
        super(message);
    }
}
