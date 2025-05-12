package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
public class Reviews {
    @Id
    private String id;
    private String userId;
    private String restaurantId;
    private int rating;
    private String comment;
}
