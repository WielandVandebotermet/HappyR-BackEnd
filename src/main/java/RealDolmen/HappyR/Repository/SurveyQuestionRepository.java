package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Survey;
import RealDolmen.HappyR.model.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for SurveyQuestion entities, providing CRUD operations.
 */
@Repository
public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {

}
