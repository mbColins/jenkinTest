package dfoodbackend.controller;

import dfoodbackend.Utils.exceptions.ResponseHandler;
import dfoodbackend.dto.AddressDto;
import dfoodbackend.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

//    @PostMapping("")
//    public  Mono<ResponseEntity<Object>> setAddress(Mono<AddressDto> addressDtoMono, ServerHttpRequest request){
//        return addressService.setAddress(addressDtoMono,request)
//                .map(responseMessage -> ResponseHandler.generateResponse(HttpStatus.OK,false,responseMessage.getMessage(),responseMessage.getObject()));
//    }
}
