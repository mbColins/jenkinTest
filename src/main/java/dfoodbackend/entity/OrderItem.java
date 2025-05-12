package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_item")
@Data
public class OrderItem {
    @Id
    private String id;
    @DBRef
    private MenuItems item;
    private int quantity;
}
