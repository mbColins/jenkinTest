package dfoodbackend.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AvailabilityDto {
    private String id;
    private String restaurantId;
    private String day;
    private String openTime;
    private String closeTime;
}
