package dfoodbackend.controller;


import dfoodbackend.Utils.exceptions.ResponseHandler;
import dfoodbackend.dto.ReviewDto;
import dfoodbackend.services.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewsService reviewsService;

    public ReviewController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping("")
    public Mono<ResponseEntity<Object>> addReview(@RequestBody Mono<ReviewDto> reviewDtoMono, ServerHttpRequest request){
        return reviewsService.writeReview(reviewDtoMono,request)
                .map(responseMessage -> ResponseHandler.generateResponse(HttpStatus.OK,false, responseMessage.getMessage(), null));
    }

    @GetMapping("/{restaurantId}")
    public Mono<ResponseEntity<Object>> getRestaurantReviews(@PathVariable String restaurantId){
        Mono<ReviewDto> reviewDtoMono = reviewsService.getReviewPerRestaurant(restaurantId);
      return   reviewDtoMono.map(review -> ResponseHandler.generateResponse(HttpStatus.OK,false,null,review));
    }
}
