package heartbeat.common;

import java.io.Serializable;

public class ResponseVo implements Serializable{

    private String id;
    private String responseMessage;
    private Object object;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "id='" + id + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", object=" + object +
                '}';
    }
}
