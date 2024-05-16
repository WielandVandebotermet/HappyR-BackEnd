package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Result findResultByUserIdAndSurveyId(int userId, int surveyId);

    List<Result> findResultsBySurveyId(int surveyId);


    @Query("SELECT distinct r FROM Result r  JOIN Survey s ON r.survey.id = s.id JOIN Team t ON t.id IN (SELECT survey.groupList FROM Survey survey WHERE survey.id = 1 ) JOIN Manager m ON t.id = m.team.id WHERE m.user.id = :userId AND r.survey.id = :surveyId")
            List<Result> findDistinctBySurveyAndUser(Long surveyId, Long userId);

}
