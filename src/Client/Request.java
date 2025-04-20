package Client;
import java.io.Serializable;

public class Request implements Serializable {
    private final String action;
    private final Object payload;

    public Request(String action, Object payload) {
        this.action = action;
        this.payload = payload;
    }

    public String getAction() {
        return action;
    }

    public Object getPayload() {
        return payload;
    }
}

