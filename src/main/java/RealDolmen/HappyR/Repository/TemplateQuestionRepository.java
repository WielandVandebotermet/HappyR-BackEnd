package RealDolmen.HappyR.Repository;

import org.springframework.stereotype.Repository;
import RealDolmen.HappyR.model.TemplateQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TemplateQuestionRepository extends JpaRepository<TemplateQuestion, Long> {
}
