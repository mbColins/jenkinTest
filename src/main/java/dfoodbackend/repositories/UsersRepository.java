package dfoodbackend.repositories;

import dfoodbackend.entity.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

public interface UsersRepository extends ReactiveMongoRepository<Users,String> {
}
