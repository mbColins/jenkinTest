package dfoodbackend.dto;

import dfoodbackend.entity.Address;
import dfoodbackend.entity.Availability;
import dfoodbackend.entity.Menus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class RestaurantDto {
    private String name;
    private String ownerName;
    @DBRef
    private Address address;
    @DBRef
    private Availability availability;
    @DBRef
    private List<Menus> menus;

}
