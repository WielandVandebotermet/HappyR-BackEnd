package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.TeamUser;
import RealDolmen.HappyR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Manager entities, providing CRUD operations and additional query methods.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    /**
     * Finds managers by their team ID.
     *
     * @param teamId The ID of the team.
     * @return A list of managers belonging to the specified team.
     */
    List<Manager> findByTeamId(Long teamId);

    /**
     * Finds managers by their user ID.
     *
     * @param userId The ID of the user.
     * @return A list of managers associated with the specified user.
     */
    List<Manager> findByUserId(Long userId);
}
