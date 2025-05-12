package dfoodbackend.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliveries")
@Data
@Builder
public class Deliveries {
    @Id
    private String id;
    private String orderId;  // Refers to an Order
    private String driverId; // Refers to the Driver (User)
    private String status;   // e.g., "in progress", "delivered"
}
