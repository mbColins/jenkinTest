package dfoodbackend.Utils;

import dfoodbackend.dto.*;
import dfoodbackend.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.awt.*;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern NAMED_PLACEHOLDER_PATTERN = Pattern.compile("\\{([a-zA-Z0-9_]+)\\}");

    private final MessageSource messageSource;

    public Utils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static UserDto UserEntityToDto(Users users){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(users,userDto);
        return userDto;
    }

    public static Users UserDtoToEntity(UserDto userDto) {
        Users users = new Users();
        BeanUtils.copyProperties(userDto,users);
        return users;
    }

    public static CategoryDto categoryDtoToEntity(Category category){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category,categoryDto);
        return categoryDto;
    }

    public static Category categoryEntityToDto(CategoryDto categoryDto){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto,category);
        return category;
    }


    public static AddressDto addressEntityToDto(Address address){
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address,addressDto);
        return addressDto;
    }

    public static Address addressDtoToEntity(AddressDto addressDto){
        Address address = new Address();
        BeanUtils.copyProperties(addressDto,address);
        return address;
    }


    public static Reviews reviewsDtoToEntity(ReviewDto reviewDto){
        Reviews reviews = new Reviews();
        BeanUtils.copyProperties(reviewDto,reviews);
        return reviews;
    }


    public static ReviewDto reviewEntityToDto(Reviews reviews){
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(reviews,reviewDto);
        return reviewDto;
    }

    public static RestaurantDto restaurantEntityToDto(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurant,restaurantDto);
        return restaurantDto;
    }

    public static Restaurant restaurantDtoToEntity(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantDto,restaurant);
        return restaurant;
    }

    public static Availability restaurantAvailabilityDtoToEntity(AvailabilityDto availabilityDto){
        Availability availability = new Availability();
        BeanUtils.copyProperties(availabilityDto,availability);
        return availability;
    }

    public static  AvailabilityDto restaurantAvailabilityEntityToDto(Availability availability){
        AvailabilityDto availabilityDto = new AvailabilityDto();
        BeanUtils.copyProperties(availability,availabilityDto);
        return availabilityDto;
    }

    public static Menus MenusDtoToEntity(MenuDto menuDto){
        Menus menus = new Menus();
        BeanUtils.copyProperties(menuDto,menus);
        return menus;
    }

    public static MenuDto menuEntityToDto(Menus menus){
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menus,menuDto);
        return menuDto;
    }
    public static Locale getLocaleFromRequest(ServerHttpRequest request) {
        return request.getHeaders()
                .getAcceptLanguageAsLocales()
                .stream()
                .findFirst()
                .orElse(Locale.ENGLISH);  // Default to English if no locale is found
    }

    public static String format(String message, Map<String, Object> args) {
        if (message == null || args == null || args.isEmpty()) {
            return message;
        }

        Matcher matcher = NAMED_PLACEHOLDER_PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            Object replacement = args.get(placeholder);
            if (replacement != null) {
                matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement.toString()));
            } else {
                matcher.appendReplacement(sb, Matcher.quoteReplacement("{" + placeholder + "}"));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String generateUsername(String firstName, String lastName) {
        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0 to 99

        String baseUsername = (firstName + "." + lastName).toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
        return baseUsername + randomNumber;
    }

    public Address buildAddressFromUser(Users savedUser) {
        Address address = new Address();
        address.setId(savedUser.getId());  // Set address ID = user ID
        address.setStreet(savedUser.getStreet());
        address.setCity(savedUser.getCity());
        address.setCountry(savedUser.getCountry());
        address.setPostalCode(savedUser.getPostalCode());
        address.setLatitude(savedUser.getLatitude());
        address.setLongitude(savedUser.getLongitude());
        return address;
    }



}
