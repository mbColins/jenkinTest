package dfoodbackend.controller;

import dfoodbackend.Utils.exceptions.ResponseHandler;
import dfoodbackend.dto.CategoryDto;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public Mono<ResponseEntity<Object>> addCategory(Mono<CategoryDto> categoryDtoMono, ServerHttpRequest request) {
     return  categoryService.addCategory(categoryDtoMono, request)
             .map(responseMessage -> ResponseHandler.generateResponse(HttpStatus.OK, false, "success", responseMessage.getMessage()));
    }
}
