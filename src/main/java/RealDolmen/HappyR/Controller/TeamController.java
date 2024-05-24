package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.TeamRequest;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Controller for managing teams
@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService; // Service for handling team-related operations

    // Endpoint to retrieve all teams
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    // Endpoint to retrieve all teams associated with a specific user ID
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeamsByUserId(@PathVariable int id) {
        return teamService.getAllTeamsByUserId(id);
    }

    // Endpoint to retrieve all teams associated with a specific survey ID
    @GetMapping("/survey/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeamsBySurveyId(@PathVariable int id) {
        return teamService.getAllTeamsBySurveyId(id);
    }

    // Endpoint to retrieve a team by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable("id") String id) {
        return teamService.getTeamById(Integer.parseInt(id));
    }

    // Endpoint to delete a team by its ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable("id") String id) {
        teamService.deleteTeam(Integer.parseInt(id));
    }

    // Endpoint to edit a team by its group ID
    @PutMapping("/edit/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void editTeam(@RequestBody TeamRequest request, @PathVariable int groupId) {
        teamService.editTeam(request, groupId);
    }

    // Endpoint to create a new team associated with a user ID
    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void createTeam(@RequestBody TeamRequest request, @PathVariable int userId) {
        teamService.createTeam(request, userId);
    }
}
