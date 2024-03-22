package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.SurveyQuestion;
import RealDolmen.HappyR.model.SurveyReoccuring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyReoccuringRepository extends JpaRepository<SurveyReoccuring, Long> {
}
