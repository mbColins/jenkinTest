package dfoodbackend.controller;

import dfoodbackend.Utils.exceptions.ResponseHandler;
import dfoodbackend.dto.UserDto;
import dfoodbackend.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

    @RestController
    @RequestMapping("/api/v1/user")
    public class UsersController {

        private final UsersService usersService;
        public UsersController(UsersService usersService) {
            this.usersService = usersService;
        }

        @PostMapping("")
        public Mono<ResponseEntity<Object>> saveUser(@RequestBody Mono<UserDto> userDtoMono, ServerHttpRequest request) {
            return usersService.saveUser(userDtoMono, request)
                    .map(responseMessage -> ResponseHandler.generateResponse(HttpStatus.CREATED, false, "success",
                            responseMessage.getMessage()));
        }

        @GetMapping("")
        public Mono<ResponseEntity<Object>> getAllUsers(
                @RequestParam(name = "filter") String filter,
                @RequestParam(name = "value") String value,
                @RequestParam(name = "page") int page,
                @RequestParam(name = "size") int size
        ) {
            Flux<UserDto> users = usersService.getUsers(filter, value, page, size);
            return users.collectList()
                    .map(list -> ResponseHandler.generateResponse(HttpStatus.OK, false, null, list));
        }


        @GetMapping("/{id}")
        public Mono<ResponseEntity<Object>> getUser(@PathVariable String id){
            Mono<UserDto> user = usersService.findUserById(id);
           return user.map(userDto -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",userDto));
        }

        @PatchMapping("/{id}")
        public Mono<ResponseEntity<Object>> updateUser(@RequestBody Mono<UserDto> userDtoMono,@PathVariable String id,ServerHttpRequest request){
            return  usersService.updateUser(userDtoMono,id,request)
                    .map(responseMessage -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",responseMessage.getMessage()));
        }


        @DeleteMapping("/{id}")
        public Mono<ResponseEntity<Object>> deleteUser(@PathVariable String id,ServerHttpRequest request){
            return usersService.deleteUser(id,request)
                    .map(responseMessage -> ResponseHandler.generateResponse(HttpStatus.OK,false,"success",responseMessage.getMessage()));
        }

    }
