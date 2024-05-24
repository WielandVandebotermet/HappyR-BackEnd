package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.SurveyQuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for SurveyQuestionOption entities, providing CRUD operations.
 */
@Repository
public interface SurveyQuestionOptionRepository extends JpaRepository<SurveyQuestionOption, Long> {
}
