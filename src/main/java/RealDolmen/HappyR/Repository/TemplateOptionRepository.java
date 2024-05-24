package RealDolmen.HappyR.Repository;

import org.springframework.stereotype.Repository;
import RealDolmen.HappyR.model.TemplateOption;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for TemplateOption entities, providing CRUD operations.
 */
@Repository
public interface TemplateOptionRepository extends JpaRepository<TemplateOption, Long> {
}