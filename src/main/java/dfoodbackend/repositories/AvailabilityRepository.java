package dfoodbackend.repositories;

import dfoodbackend.entity.Availability;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface AvailabilityRepository extends ReactiveMongoRepository< Availability,String> {
    Optional<Availability> findByRestaurantId(String restaurantId);
}
