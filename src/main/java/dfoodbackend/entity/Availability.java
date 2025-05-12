package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "availabilities") @Data
public class Availability {
    @Id
    private String id;
    private String restaurantId;
    private String day;
    private String openTime;
    private String closeTime;
}
