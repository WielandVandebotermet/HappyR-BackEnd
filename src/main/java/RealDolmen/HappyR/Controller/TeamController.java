package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable("id") String id) {
        return teamService.getTeamById(Integer.parseInt(id));
    }

    @DeleteMapping("/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteTeam
            (@PathVariable("id") String id) {
        teamService.deleteTeam(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void EditTeam(@RequestBody String updatedName, @PathVariable int groupId) {
        teamService.editTeam(updatedName, groupId);
    }

    @PostMapping("/Create/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void createTeam(@RequestBody String GroupName, @PathVariable int userId) {
        teamService.createTeam(GroupName, userId);
    }
}
