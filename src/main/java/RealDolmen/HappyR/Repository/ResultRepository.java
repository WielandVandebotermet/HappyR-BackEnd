package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Result entities, providing CRUD operations and custom query methods.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface ResultRepository extends JpaRepository<Result, Long> {

    /**
     * Finds a result by user ID and survey ID.
     *
     * @param userId   The ID of the user.
     * @param surveyId The ID of the survey.
     * @return The result matching the given user ID and survey ID.
     */
    Result findResultByUserIdAndSurveyId(int userId, int surveyId);

    /**
     * Finds results by survey ID.
     *
     * @param surveyId The ID of the survey.
     * @return The list of results for the given survey ID.
     */
    List<Result> findResultsBySurveyId(int surveyId);

    /**
     * Finds distinct results by survey and user.
     *
     * @param surveyId The ID of the survey.
     * @param userId   The ID of the user.
     * @return The list of distinct results for the given survey and user.
     */
    @Query("SELECT distinct r FROM Result r  JOIN Survey s ON r.survey.id = s.id JOIN Team t ON t.id IN (SELECT survey.groupList FROM Survey survey WHERE survey.id = 1 ) JOIN Manager m ON t.id = m.team.id WHERE m.user.id = :userId AND r.survey.id = :surveyId")
    List<Result> findDistinctBySurveyAndUser(@Param("surveyId") Long surveyId, @Param("userId") Long userId);

}