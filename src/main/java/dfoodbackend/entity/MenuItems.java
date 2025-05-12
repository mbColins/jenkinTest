package dfoodbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "menu_items")
@Data
public class MenuItems {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;

}
