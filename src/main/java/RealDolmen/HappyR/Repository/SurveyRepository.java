package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Result;
import RealDolmen.HappyR.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("SELECT DISTINCT s FROM Survey s WHERE (s.id IN (SELECT s1.id FROM Survey s1 JOIN s1.groupList gl WHERE gl IN (SELECT tu.team.id FROM TeamUser tu WHERE tu.user.id = :userId)) OR s.id IN (SELECT s2.id FROM Survey s2 WHERE EXISTS (SELECT m FROM Manager m WHERE m.user.id = :userId AND s2.id IN (SELECT s3.id FROM Survey s3 JOIN s3.groupList gl WHERE m.team.id = gl)))) " +
            "AND NOT EXISTS (SELECT r FROM Result r WHERE r.survey.id = s.id AND r.userId = :userId)")
    List<Survey> findSurveysByUserId(@Param("userId") Long userId);

    @Query("SELECT DISTINCT s FROM Survey s WHERE s.started = false AND (s.id IN (SELECT s1.id FROM Survey s1 JOIN s1.groupList gl WHERE gl IN (SELECT m.team.id FROM Manager m WHERE m.user.id = :userId)) OR s.id IN (SELECT s2.id FROM Survey s2 WHERE EXISTS (SELECT m FROM Manager m WHERE m.user.id = :userId AND s2.id IN (SELECT s3.id FROM Survey s3 JOIN s3.groupList gl WHERE m.team.id = gl))))")
    List<Survey> findSurveysByManagerId(@Param("userId") Long userId);

    @Query(
            """
            SELECT DISTINCT s
            FROM Survey s
            LEFT JOIN Team t ON t.id IN (
                SELECT m.team.id FROM Manager m WHERE m.user.id = :userId
            )
            """)
    List<Survey> findSurveysResultsByManagerId(@Param("userId") int userId);

    List<Survey> findByStartDateBeforeAndStartedFalse(Calendar currentDate);

}
