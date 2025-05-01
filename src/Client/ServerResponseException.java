package Client;

/**
 * ServerResponeException:
 * The Exception to be thrown whenever there is an error in Client-Server
 * Communication
 *
 * @version 4/18/2025
 * @author Karthik Nandagiri
 */
public class ServerResponseException extends Exception {
    public ServerResponseException(String message) {
        super(message);
    }
}
