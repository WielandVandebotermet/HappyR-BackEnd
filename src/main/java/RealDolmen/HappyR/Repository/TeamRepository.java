package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t LEFT JOIN t.teamUsers tu LEFT JOIN t.managers m WHERE tu.user.id = :userId OR m.user.id = :userId")
    List<Team> findTeamsByUserId(@Param("userId") Long userId);

    @Query("SELECT DISTINCT t FROM Team t JOIN Survey s ON s.id = :surveyId WHERE t.id MEMBER OF s.groupList")
    List<Team> findAllBySurveyId(@Param("surveyId")Long surveyId);

}
