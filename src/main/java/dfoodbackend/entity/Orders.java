package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
@Data
public class Orders {
    @Id
    private String id;
    private String customerId;  // Refers to the user/customer
    private String status;      // e.g., "placed", "in progress", "delivered"
    private double totalPrice;

    @DBRef
    private List<OrderItem> items;
}
