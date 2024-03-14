package RealDolmen.HappyR.Repository;

import org.springframework.stereotype.Repository;
import RealDolmen.HappyR.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
}
