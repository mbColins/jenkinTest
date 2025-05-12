package dfoodbackend.dto;

import dfoodbackend.entity.Restaurant;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class MenuDto {
    private String name;
    private String description;
    private Boolean special;
    private String restaurant;
}
