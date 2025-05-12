package dfoodbackend.services;

import dfoodbackend.Utils.Utils;
import dfoodbackend.Utils.exceptions.ResourceNotFoundException;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.dto.UserDto;
import dfoodbackend.entity.Users;
import dfoodbackend.repositories.AddressRepository;
import dfoodbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final AddressRepository addressRepository;
    private final MessageSource messageSource;
    private final ReactiveMongoTemplate mongoTemplate;
    @Autowired
    public UsersService(UsersRepository usersRepository, AddressRepository addressRepository, MessageSource messageSource, ReactiveMongoTemplate mongoTemplate) {
        this.usersRepository = usersRepository;
        this.addressRepository = addressRepository;
        this.messageSource = messageSource;
        this.mongoTemplate = mongoTemplate;
    }

    @Transactional
    public Mono<ResponseMessage> saveUser(Mono<UserDto> userDtoMono, ServerHttpRequest request) {
        return userDtoMono
                // Convert UserDto to Users entity and generate a username
                .map(userDto -> {
                    Users user = Utils.UserDtoToEntity(userDto);
                    user.setUserName(Utils.generateUsername(user.getFirstName(), user.getLastName()));
                    return user;
                }).flatMap(usersRepository::insert).map(Utils::UserEntityToDto).map(userDto ->
                        new ResponseMessage(getResponseMessage("message.user.created", null, Utils.getLocaleFromRequest(request)), null));
    }

    public Flux<UserDto> getUsers(String filter, String value, int page, int size) {
        Query query = new Query();

        if (filter != null && value != null && !filter.isBlank() && !value.isBlank()) {
            query.addCriteria(Criteria.where(filter).regex(Pattern.quote(value), "i"));
        }
        query.skip((long) page * size).limit(size);
        return mongoTemplate.find(query, Users.class)
                .map(Utils::UserEntityToDto);
    }



    public Mono<UserDto> findUserById(String id){
        return usersRepository.findById(id).map(Utils::UserEntityToDto);
    }

    public Mono<ResponseMessage> updateUser(Mono<UserDto> userDtoMono, String id, ServerHttpRequest request){
        return usersRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("user.not.found")))
                .flatMap(users -> userDtoMono.map(Utils::UserDtoToEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(usersRepository::insert)
                .map(user -> new ResponseMessage(getResponseMessage("user.updated",null,Utils.getLocaleFromRequest(request)),null));
    }

    public Mono<ResponseMessage> deleteUser(String id, ServerHttpRequest request){
        return usersRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("user.not.found  ")))
                .map(response -> new ResponseMessage(getResponseMessage("user.deleted",null,Utils.getLocaleFromRequest(request)),null));

    }


    private String getResponseMessage(String errorCode, Map<String, Object> args, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return Utils.format(message, args);
    }


}
