package dfoodbackend.services;

import dfoodbackend.Utils.Utils;
import dfoodbackend.Utils.exceptions.ResourceNotFoundException;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.dto.RestaurantDto;
import dfoodbackend.entity.Restaurant;
import dfoodbackend.entity.Users;
import dfoodbackend.repositories.RestaurantRepository;
import dfoodbackend.repositories.UsersRepository;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.core.query.Query;
import java.util.Locale;
import java.util.regex.Pattern;


@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MessageSource messageSource;
    private final UsersRepository usersRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public RestaurantService(RestaurantRepository restaurantRepository, MessageSource messageSource, UsersRepository usersRepository, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.restaurantRepository = restaurantRepository;
        this.messageSource = messageSource;
        this.usersRepository = usersRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    //auto generate password
//    public Mono<ResponseMessage> createNewRestaurant(Mono<RestaurantDto> restaurantDtoMono, ServerHttpRequest request){
//        return restaurantDtoMono
//                .map(Utils::restaurantDtoToEntity).flatMap(restaurantRepository::insert).map(restaurant -> Users.builder()
//                        .userName(restaurant.getOwnerName())
//                        .password(restaurant.getOwnerName())
//                        .build()).flatMap(usersRepository::insert)
//                .map(restaurant -> new ResponseMessage(getResponseMessage("restaurant.created", Utils.getLocaleFromRequest(request)), null));
//    }

//    public Mono<ResponseMessage> updateRestaurant(String restaurantId, Mono<RestaurantDto> restaurantDtoMono, ServerHttpRequest request){
//        return restaurantRepository.findById(restaurantId)
//                .switchIfEmpty(Mono.error(new ResourceNotFoundException("restaurant.not.found")))
//                .flatMap(restaurant -> restaurantDtoMono.map(Utils::restaurantDtoToEntity))
//                .doOnNext(e -> e.setId(restaurantId))
//                .flatMap(restaurantRepository::insert)
//                .map(restaurant -> new ResponseMessage(getResponseMessage("restaurant.updated", Utils.getLocaleFromRequest(request)),null));
//    }

    public Mono<ResponseMessage> deleteRestaurantByOwnerName(String name,ServerHttpRequest request){
        return restaurantRepository.deleteByOwnerName(name)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("restaurant.not.found")))
                .map(restaurant -> new ResponseMessage(getResponseMessage("restaurant.deleted", Utils.getLocaleFromRequest(request)),null));
    }

    public Mono<RestaurantDto> getRestaurantById(String restaurantId){
        return restaurantRepository.findById(restaurantId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("restaurant.not.found")))
                .map(Utils::restaurantEntityToDto);
    }

    public Flux<RestaurantDto> getAllRestaurants(String filter,String search,int page, int size){
        Query query = new Query();
        if(filter != null && search != null && !filter.isBlank() && !search.isBlank()){
            query.addCriteria(Criteria.where(filter).regex(Pattern.quote(search), "i"));
        }
        query.skip((long) page * size).limit(size);
        return reactiveMongoTemplate.find(query, Restaurant.class)
                .map(Utils::restaurantEntityToDto);
    }

    private String getResponseMessage(String errorCode, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return Utils.format(message, null);
    }

}
