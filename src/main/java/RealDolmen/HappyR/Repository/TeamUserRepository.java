package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.TeamUser;
import RealDolmen.HappyR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
    List<TeamUser> findByTeamId(Long teamId);

    List<TeamUser> findByUserId(Long userId);
}
