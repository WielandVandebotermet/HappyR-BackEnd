package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.TeamRequest;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeamsByUserId(@PathVariable int id) {
        return teamService.getAllTeamsByUserId(id);
    }

    @GetMapping("/survey/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeamsBySurveyId(@PathVariable int id) {
        return teamService.getAllTeamsBySurveyId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable("id") String id) {
        return teamService.getTeamById(Integer.parseInt(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam
            (@PathVariable("id") String id) {
        teamService.deleteTeam(Integer.parseInt(id));
    }

    @PutMapping("/edit/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void editTeam(@RequestBody TeamRequest request, @PathVariable int groupId) {
        teamService.editTeam(request, groupId);
    }

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void createTeam(@RequestBody TeamRequest  request, @PathVariable int userId) {
        teamService.createTeam(request, userId);
    }
}
