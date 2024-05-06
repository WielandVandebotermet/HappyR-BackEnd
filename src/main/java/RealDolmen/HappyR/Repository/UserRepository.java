package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Result;
import RealDolmen.HappyR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String Email);

    Optional<User> findUserByAuthId(String authId);

}
