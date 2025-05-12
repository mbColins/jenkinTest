package dfoodbackend.repositories;

import dfoodbackend.entity.Restaurant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RestaurantRepository extends ReactiveMongoRepository<Restaurant,String> {
Mono<Restaurant> deleteByOwnerName(String ownerName);
Mono<Restaurant> findByOwnerName(String ownerName);
}
