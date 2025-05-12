package dfoodbackend.services;

import dfoodbackend.Utils.Utils;
import dfoodbackend.dto.ResponseMessage;
import dfoodbackend.dto.ReviewDto;
import dfoodbackend.repositories.ReviewsRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;
    private final MessageSource messageSource;

    public ReviewsService(ReviewsRepository reviewsRepository, MessageSource messageSource) {
        this.reviewsRepository = reviewsRepository;
        this.messageSource = messageSource;
    }

    public Mono<ResponseMessage> writeReview(Mono<ReviewDto> reviewDtoMono, ServerHttpRequest request){
        return reviewDtoMono
                .map(Utils::reviewsDtoToEntity)
                .flatMap(reviewsRepository::insert)
                .map(rsp -> new ResponseMessage(getResponseMessage("review.wrote",null,Utils.getLocaleFromRequest(request)),null ));
    }

    public Mono<ReviewDto> getReviewPerRestaurant(String restaurantId){
        return reviewsRepository.findByRestaurantId(restaurantId)
                .map(Utils::reviewEntityToDto);
    }

    private String getResponseMessage(String errorCode, Map<String, Object> args, Locale locale) {
        // Retrieve the message based on errorCode, using default errorCode as fallback
        String message = messageSource.getMessage(errorCode, null, errorCode, locale);
        // Format the message using the provided arguments
        return Utils.format(message, args);
    }
}
