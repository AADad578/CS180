package Server;

import java.io.Serializable;

public class Request implements Serializable {
    String action;
    Object data;

    public String getAction() {
        return this.action;
    }

    public Object getPayload() {
        return this.data;
    }

    public Request(String action, Object data){
        this.action = action;
        this.data = data;
    }
}
