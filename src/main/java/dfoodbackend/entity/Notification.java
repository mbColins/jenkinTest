package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notifications")
public class Notification {
    private String userId;
    private String message;
    private String type; // "email", "sms", "push"
    private boolean read;
}
