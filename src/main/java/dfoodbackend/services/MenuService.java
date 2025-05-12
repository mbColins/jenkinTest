//package dfoodbackend.services;
//
//import dfoodbackend.Utils.Utils;
//import dfoodbackend.Utils.exceptions.ResourceNotFoundException;
//import dfoodbackend.dto.MenuDto;
//import dfoodbackend.dto.ResponseMessage;
//import dfoodbackend.repositories.MenusRepository;
//import dfoodbackend.repositories.RestaurantRepository;
//import org.springframework.context.MessageSource;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//
//import java.util.Locale;
//
//@Service
//public class MenuService {
//
//    private final MenusRepository menusRepository;
//    private final RestaurantRepository restaurantRepository;
//    private final RestaurantService restaurantService;
//    private final MessageSource messageSource;
//    public MenuService(MenusRepository menusRepository, RestaurantRepository restaurantRepository, RestaurantService restaurantService, MessageSource messageSource) {
//        this.menusRepository = menusRepository;
//        this.restaurantRepository = restaurantRepository;
//        this.restaurantService = restaurantService;
//        this.messageSource = messageSource;
//    }
//
//    public Mono<ResponseMessage> addNewMenu(Mono<MenuDto> menuDtoMono, ServerHttpRequest request){
//        return menuDtoMono.map(Utils::MenusDtoToEntity)
//                .flatMap(menusRepository::insert)
//                .map(rsp -> new ResponseMessage(getResponseMessage("menu.saved",Utils.getLocaleFromRequest(request)),null));
//    }
//
////    public Mono<MenuDto> getMenuById(String menuId){
////       return menusRepository.findById(menuId).map(Utils::menuEntityToDto);
////    }
//
//    public Mono updateMenu(String menuId, Mono<MenuDto> menuDtoMono, ServerHttpRequest request){
//        return menusRepository.findById(menuId)
//                .switchIfEmpty(Mono.error(new ResourceNotFoundException("restaurant.not.found")))
//                .flatMap(restaurant -> menuDtoMono.map(Utils::MenusDtoToEntity))
////                .doOnNext(e -)
//                .flatMap(menusRepository::insert)
//                .map(restaurant -> new ResponseMessage(getResponseMessage("restaurant.updated", Utils.getLocaleFromRequest(request)),null));
//    }
//
//    public Mono<ResponseMessage> deleteMenuById(String menuId, ServerHttpRequest request){
//        return menusRepository.deleteById(menuId)
//                .switchIfEmpty(Mono.error(new ResourceNotFoundException("menu.not.found")))
//                .map(rsp -> new  ResponseMessage(getResponseMessage("menu.deleted",Utils.getLocaleFromRequest(request)),null));
//    }
//
//    private String getResponseMessage(String errorCode, Locale locale) {
//        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
//        return Utils.format(message, null);
//    }
//
//}
