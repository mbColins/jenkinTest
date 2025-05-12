package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payments")
@Data
public class Payment {
    @Id
    private String id;
    private String orderId;   // Refers to an Order
    private double amount;
    private String paymentMethod; // e.g., "credit card", "cash"
    private String status;    // e.g., "completed", "pending"

}
