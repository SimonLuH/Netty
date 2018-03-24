package heartbeat.common;

import java.io.Serializable;

public class RequestVo implements Serializable{

    private String id;
    private String requestMessage;
    private Object object;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "RequestVo{" +
                "id='" + id + '\'' +
                ", requestMessage='" + requestMessage + '\'' +
                ", object=" + object +
                '}';
    }
}
