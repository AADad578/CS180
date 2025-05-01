package Server;

import java.io.Serializable;

/**
 * Request
 * 
 * This class is used to pass data between client and server
 * 
 * @version 4/20/2025
 * 
 * @author Ankur Raghavan
 */
public class Request implements Serializable {
    String action;
    Object data;

    public String getAction() {
        return this.action;
    }

    public Object getPayload() {
        return this.data;
    }

    public Request(String action, Object data) {
        this.action = action;
        this.data = data;
    }
}
