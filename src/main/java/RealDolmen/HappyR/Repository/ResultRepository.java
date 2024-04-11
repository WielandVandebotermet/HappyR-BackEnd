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

    Result findResultBySurveyId(int surveyId);


    @Query("SELECT r FROM Result r " +
            "JOIN Manager m ON r.userId = m.id " +
            "JOIN Team t ON m.team.id = t.id " +
            "JOIN Survey s ON s.id IN (SELECT DISTINCT survey.id FROM Survey survey JOIN survey.groupList groupList WHERE groupList IN (SELECT DISTINCT team.id FROM Team team JOIN team.managers manager WHERE manager.id = :userId)) " +
            "WHERE m.user.id = :userId")
    List<Result> findResultsByManagerUserId(@Param("userId") int userId);

}
