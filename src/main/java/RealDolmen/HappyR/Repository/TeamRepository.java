package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository interface for Team entities, providing CRUD operations and custom query methods.
 */
@Repository // Indicates that this interface is a Spring repository component
public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Finds teams by user ID.
     *
     * @param userId The ID of the user.
     * @return The list of teams for the given user ID.
     */
    @Query("SELECT t FROM Team t LEFT JOIN t.teamUsers tu LEFT JOIN t.managers m WHERE tu.user.id = :userId OR m.user.id = :userId")
    List<Team> findTeamsByUserId(@Param("userId") Long userId);

    /**
     * Finds all teams associated with a survey ID.
     *
     * @param surveyId The ID of the survey.
     * @return The list of teams associated with the given survey ID.
     */
    @Query("SELECT DISTINCT t FROM Team t JOIN Survey s ON s.id = :surveyId WHERE t.id MEMBER OF s.groupList")
    List<Team> findAllBySurveyId(@Param("surveyId") Long surveyId);

}
