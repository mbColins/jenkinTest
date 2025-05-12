package dfoodbackend.services;

import dfoodbackend.Utils.Utils;
import dfoodbackend.dto.AddressDto;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.repositories.AddressRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final MessageSource messageSource;

    public AddressService(AddressRepository addressRepository, MessageSource messageSource) {
        this.addressRepository = addressRepository;
        this.messageSource = messageSource;
    }

    // TODO extract user or restaurant id from connected user
    public Mono<ResponseMessage> setAddress(Mono<AddressDto> addressDtoMono, ServerHttpRequest request){
        return addressDtoMono
                .map(Utils::addressDtoToEntity)
                .flatMap(addressRepository::insert)
                .map(address -> new ResponseMessage(getResponseMessage("address.setup",null,Utils.getLocaleFromRequest(request)),address));
    }

    private String getResponseMessage(String errorCode, Map<String, Object> args, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return Utils.format(message, args);
    }
}
