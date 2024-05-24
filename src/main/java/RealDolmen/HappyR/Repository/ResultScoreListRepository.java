package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.ResultScoreList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for ResultScoreList entities, providing CRUD operations.
 */
@Repository
public interface ResultScoreListRepository extends JpaRepository<ResultScoreList, Long> {
}
