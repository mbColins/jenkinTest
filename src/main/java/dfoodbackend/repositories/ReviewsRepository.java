package dfoodbackend.repositories;

import dfoodbackend.entity.Reviews;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ReviewsRepository extends ReactiveMongoRepository<Reviews,String> {
    Mono<Reviews> findByRestaurantId(String restaurantId);
}
