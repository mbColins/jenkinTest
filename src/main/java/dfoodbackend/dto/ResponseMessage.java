package dfoodbackend.dto;

import lombok.Data;

@Data
public class ResponseMessage {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private Object object;

    public ResponseMessage(String message, Object object) {
        this.message = message;
        this.object = object;
    }
}
