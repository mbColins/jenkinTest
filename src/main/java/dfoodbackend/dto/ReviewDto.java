package dfoodbackend.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private String userId;
    private String restaurantId;
    private int rating;
    private String comment;
}
