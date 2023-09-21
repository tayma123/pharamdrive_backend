package login.repository;

import com.pharamdrive.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserLoginRepository extends MongoRepository<User, String> {

	User findByUsername(String username);
	User findByEmail(String email);
	List<User> findAllByEmail(String email);
	Optional<User> findById(String id);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);


}

