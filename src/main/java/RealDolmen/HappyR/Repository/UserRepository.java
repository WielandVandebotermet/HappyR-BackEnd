package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Result;
import RealDolmen.HappyR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository interface for User entities, providing CRUD operations.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return The user with the specified email address.
     */
    User findUserByEmail(String email);

    /**
     * Finds a user by their authentication ID.
     *
     * @param authId The authentication ID of the user to find.
     * @return An optional containing the user with the specified authentication ID, if found.
     */
    Optional<User> findUserByAuthId(String authId);
}
