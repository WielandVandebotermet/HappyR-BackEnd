package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
}
