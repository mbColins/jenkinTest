package dfoodbackend.services;

import dfoodbackend.Utils.Utils;
import dfoodbackend.dto.CategoryDto;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.repositories.CategoryRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;

    public CategoryService(CategoryRepository categoryRepository, MessageSource messageSource) {
        this.categoryRepository = categoryRepository;
        this.messageSource = messageSource;
    }

    public Mono<ResponseMessage> addCategory(Mono<CategoryDto> categoryDtoMono, ServerHttpRequest request) {
        return categoryDtoMono
                .map(Utils::categoryEntityToDto)
                .flatMap(categoryRepository::insert)
                .map(rsp -> new ResponseMessage(getResponseMessage("category.created", null, Utils.getLocaleFromRequest(request)), null));
    }


    private String getResponseMessage(String errorCode, Map<String, Object> args, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return Utils.format(message, args);
    }

}
