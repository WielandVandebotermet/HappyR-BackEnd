package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.model.Team;
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
    public void DeleteKoers
            (@PathVariable("id") String id) {
        teamService.deleteTeam(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditKoers
            (@PathVariable("id") String id, @RequestBody Team team) {
        teamService.editTeam(Integer.parseInt(id), team);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createKoers
            (@RequestBody Team team, int UserId) {
        teamService.createTeam(team, UserId);
    }
}
