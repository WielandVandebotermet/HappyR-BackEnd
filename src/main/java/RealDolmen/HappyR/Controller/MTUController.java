package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.MTURequest;
import RealDolmen.HappyR.Service.ManagerService;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.TeamUserService;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.TeamUser;
import RealDolmen.HappyR.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/MTU")
@RequiredArgsConstructor
public class MTUController {
    private final ManagerService managerService;
    private final TeamUserService teamUserService;
    private final TeamService teamService;
    private final UserService userService;

    //Team Users
    @GetMapping("TU/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamUser> getAllTeamUsers() {
        return teamUserService.getAllGroupUsers();
    }

    @GetMapping("TU/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamUser getTeamUserById(@PathVariable("id") String id) {
        return teamUserService.getGroupUserById(Integer.parseInt(id));
    }

    @DeleteMapping("TU/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeamUser
            (@PathVariable("id") String id) {
        teamUserService.deleteGroupUser(Integer.parseInt(id));
    }

    @PutMapping("TU/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editTeamUser
            (@PathVariable("id") String id, @RequestBody TeamUser teamUser) {
        teamUserService.editGroupUser(Integer.parseInt(id), teamUser);
    }
    @PostMapping("TU/")
    @ResponseStatus(HttpStatus.OK)
    public void createTeamUser
            (@RequestBody MTURequest request) {
        int teamId = request.getTeamId();
        int userId = request.getUserId();

        Team team = teamService.getTeamById(teamId);
        User user = userService.getUserById(userId);
        if(user != null && team != null) {
            teamUserService.createGroupUser(team,user);
        }
    }



    //Managers
    @GetMapping("M/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("M/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager getTeamById(@PathVariable("id") String id) {
        return managerService.getManagerById(Integer.parseInt(id));
    }

    @DeleteMapping("M/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteManager
            (@PathVariable("id") String id) {
        managerService.deleteManager(Integer.parseInt(id));
    }

    @PutMapping("M/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editManager
            (@PathVariable("id") String id, @RequestBody Manager manager) {
        managerService.editManager(Integer.parseInt(id), manager);
    }
    @PostMapping("M/")
    @ResponseStatus(HttpStatus.OK)
    public void createManager
            (@RequestBody MTURequest request) {
        int teamId = request.getTeamId();
        int userId = request.getUserId();

        Team team = teamService.getTeamById(teamId);
        User user = userService.getUserById(userId);
        if(user != null && team != null) {
            managerService.createManager(team, user);
        }
    }




    @GetMapping("TU/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamUser> getTeamUsersByTeamId(@PathVariable("id") Long teamId) {
        return teamUserService.getAllGroupUsersByTeamId(teamId);
    }
    @GetMapping("M/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> getManagersByTeamId(@PathVariable("id") Long teamId) {
        return managerService.getAllManagersByTeamId(teamId);
    }

    @GetMapping("TU/teams/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamUser> getTeamsByTUUserId(@PathVariable("id") Long UserId) {
        return teamUserService.getAllTeamsByUserId(UserId);
    }

    @GetMapping("M/teams/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> getTeamsByMUserId(@PathVariable("id") Long UserId) {
        return managerService.getAllTeamsByUserId(UserId);
    }
}
