package dfoodbackend.controller;

import dfoodbackend.Utils.exceptions.ResponseHandler;
import dfoodbackend.dto.RestaurantDto;
import dfoodbackend.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping
@RestController("/api/v1/restaurant")
public class RestaurantController { 
    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

//    @PostMapping("")
//    public Mono<ResponseEntity<Object>> createRestaurant(@RequestBody Mono<RestaurantDto> restaurantDtoMono, ServerHttpRequest request){
//        return restaurantService.createNewRestaurant(restaurantDtoMono,request)
//                .map(restaurant -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",null));
//    }

//    @PutMapping("/{restaurantId}")
//    public Mono<ResponseEntity<Object>> updateRestaurant(@RequestBody Mono<RestaurantDto> restaurantDtoMono,@PathVariable String restaurantId,ServerHttpRequest request){
//        return restaurantService.updateRestaurant(restaurantId,restaurantDtoMono,request)
//                .map(restaurant -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",null));
//    }


    @DeleteMapping("/{name}")
    public Mono<ResponseEntity<Object>> deleteRestaurantByName(@PathVariable String name, ServerHttpRequest request){
        return restaurantService.deleteRestaurantByOwnerName(name,request)
                .map(restaurant -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",restaurant));
    }

    @GetMapping("/{restaurantId}")
    public Mono<ResponseEntity<Object>> getRestaurantById(@PathVariable String restaurantId) {
        return restaurantService.getRestaurantById(restaurantId)
                .map(dto -> ResponseHandler.generateResponse(HttpStatus.OK, false, "success", dto));
    }

    @GetMapping("/")
    public Mono<ResponseEntity<Object>> getAllRestaurants(@RequestParam(name = "filter") String filter,
                                                  @RequestParam(name = "search") String search,
                                                  @RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size) {
        Flux<RestaurantDto> restaurantDto = restaurantService.getAllRestaurants(filter,search,page,size);
        return restaurantDto.collectList()
                .map(list -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",list));
    }

}
