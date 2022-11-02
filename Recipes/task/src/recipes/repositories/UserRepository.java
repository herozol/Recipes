package recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.models.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByEmail(String email);
}
