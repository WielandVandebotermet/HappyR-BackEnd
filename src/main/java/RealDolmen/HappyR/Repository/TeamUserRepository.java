package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
}
