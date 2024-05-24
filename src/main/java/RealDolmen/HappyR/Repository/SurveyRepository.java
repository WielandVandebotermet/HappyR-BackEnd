package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Result;
import RealDolmen.HappyR.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
/**
 * Repository interface for Survey entities, providing CRUD operations and custom query methods.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    /**
     * Finds surveys by user ID.
     *
     * @param userId The ID of the user.
     * @return The list of surveys for the given user ID.
     */
    @Query("SELECT DISTINCT s FROM Survey s WHERE (s.id IN (SELECT s1.id FROM Survey s1 JOIN s1.groupList gl WHERE gl IN (SELECT tu.team.id FROM TeamUser tu WHERE tu.user.id = :userId)) OR s.id IN (SELECT s2.id FROM Survey s2 WHERE EXISTS (SELECT m FROM Manager m WHERE m.user.id = :userId AND s2.id IN (SELECT s3.id FROM Survey s3 JOIN s3.groupList gl WHERE m.team.id = gl)))) " +
            "AND NOT EXISTS (SELECT r FROM Result r WHERE r.survey.id = s.id AND r.userId = :userId)")
    List<Survey> findSurveysByUserId(@Param("userId") Long userId);

    /**
     * Finds surveys by manager ID.
     *
     * @param userId The ID of the manager.
     * @return The list of surveys for the given manager ID.
     */
    @Query(value = "SELECT DISTINCT s.* " +
            "FROM Survey s " +
            "JOIN Team t ON t.group_name IN ( " +
            "    SELECT group_name " +
            "    FROM Survey survey " +
            ") " +
            "JOIN Manager m ON t.id = m.group_id " +
            "WHERE m.user_id = :userId ",
            nativeQuery = true)
    List<Survey> findSurveysByManagerId(@Param("userId") int userId);

    /**
     * Finds surveys results by manager ID.
     *
     * @param userId The ID of the manager.
     * @return The list of surveys results for the given manager ID.
     */
    @Query(value = "SELECT DISTINCT s.* " +
            "FROM Result r " +
            "RIGHT JOIN Survey s ON r.survey_id = s.id " +
            "JOIN Team t ON t.group_name IN ( " +
            "    SELECT group_name " +
            "    FROM Survey survey " +
            ") " +
            "JOIN Manager m ON t.id = m.group_id " +
            "WHERE m.user_id = :userId ",
            nativeQuery = true)
    List<Survey> findSurveysResultsByManagerId(@Param("userId") int userId);

    /**
     * Finds surveys with start date before the current date and not yet started.
     *
     * @param currentDate The current date.
     * @return The list of surveys with start date before the current date and not yet started.
     */
    List<Survey> findByStartDateBeforeAndStartedFalse(Calendar currentDate);

}
