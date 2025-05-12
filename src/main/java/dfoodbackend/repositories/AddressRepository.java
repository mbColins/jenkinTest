package dfoodbackend.repositories;

import dfoodbackend.entity.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AddressRepository extends ReactiveMongoRepository<Address,String> {
}
