package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Category entities, providing CRUD operations.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
