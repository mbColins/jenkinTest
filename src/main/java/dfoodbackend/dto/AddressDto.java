package dfoodbackend.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String id;
    private String userId;
    private String restaurantId;
    private String driverId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private double latitude;
    private double longitude;
}
