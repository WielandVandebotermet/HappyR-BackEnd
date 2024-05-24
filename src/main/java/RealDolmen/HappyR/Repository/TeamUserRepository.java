package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.TeamUser;
import RealDolmen.HappyR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository interface for TeamUser entities, providing CRUD operations and custom query methods.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {

    /**
     * Finds team users by team ID.
     *
     * @param teamId The ID of the team.
     * @return The list of team users for the given team ID.
     */
    List<TeamUser> findByTeamId(Long teamId);

    /**
     * Finds team users by user ID.
     *
     * @param userId The ID of the user.
     * @return The list of team users for the given user ID.
     */
    List<TeamUser> findByUserId(Long userId);
}