package Server;

/**
 * InvalidInputException
 * 
 * Called if the input to a Server method is invalid
 * 
 * @version 4/6/2025
 * 
 * @author Ankur Raghavan
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }

}
