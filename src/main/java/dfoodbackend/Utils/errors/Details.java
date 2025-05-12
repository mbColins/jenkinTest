package dfoodbackend.Utils.errors;

import lombok.*;

import java.util.Date;

@Getter @Builder
@Data
public class Details {
    private final Date timestamp ;
    private String message;
    private String details;

    public Details(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
