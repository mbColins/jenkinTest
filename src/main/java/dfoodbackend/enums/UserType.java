package dfoodbackend.enums;

public enum UserType {
    RESTAURANT_OWNER("Restaurant Owner"),
    DRIVER("Deliver meals to customers"),
    CUSTOMER("Person Ordering the Meal");

    private String description;


    UserType(String description) {
        this.description = description;
    }
}
