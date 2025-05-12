package dfoodbackend.services;

import dfoodbackend.Utils.Utils;
import dfoodbackend.Utils.exceptions.ResourceNotFoundException;
import dfoodbackend.dto.AvailabilityDto;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.dto.RestaurantDto;
import dfoodbackend.repositories.AvailabilityRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;


    private final MessageSource messageSource;

    public AvailabilityService(AvailabilityRepository availabilityRepository, MessageSource messageSource) {
        this.availabilityRepository = availabilityRepository;
        this.messageSource = messageSource;
    }

    public Mono<ResponseMessage> setRestaurantAvailability(Mono<AvailabilityDto> availabilityDtoMono, ServerHttpRequest request){
        return availabilityDtoMono
                .map(Utils::restaurantAvailabilityDtoToEntity)
                .flatMap(availabilityRepository::insert)
                .map(rsp -> new ResponseMessage(getResponseMessage("availability.set",null,Utils.getLocaleFromRequest(request)),null));
    }


//    public Mono<ResponseMessage> updateRestaurantAvailability(String restaurantId, Mono<AvailabilityDto> availabilityDtoMono){
//        return availabilityRepository.findByRestaurantId(restaurantId)
//                .orElseThrow(new ResourceNotFoundException("restaurant.not.found"))
//                .setCloseTime(availabilityDtoMono.get);
//    }


    private String getResponseMessage(String errorCode, Map<String, Object> args, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return Utils.format(message, args);
    }
}
