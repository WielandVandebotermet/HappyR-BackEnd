package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.ManagerService;
import RealDolmen.HappyR.Service.TeamUserService;
import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.TeamUser;
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

    @DeleteMapping("TU/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteTeamUser
            (@PathVariable("id") String id) {
        teamUserService.deleteGroupUser(Integer.parseInt(id));
    }

    @PutMapping("TU/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditTeamUser
            (@PathVariable("id") String id, @RequestBody TeamUser teamUser) {
        teamUserService.editGroupUser(Integer.parseInt(id), teamUser);
    }
    @PostMapping("TU/")
    @ResponseStatus(HttpStatus.OK)
    public void createTeamUser
            (@RequestBody TeamUser teamUser) {
        teamUserService.createGroupUser(teamUser);
    }

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

    @DeleteMapping("M/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteManager
            (@PathVariable("id") String id) {
        managerService.deleteManager(Integer.parseInt(id));
    }

    @PutMapping("M/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditManager
            (@PathVariable("id") String id, @RequestBody Manager manager) {
        managerService.editManager(Integer.parseInt(id), manager);
    }
    @PostMapping("M/")
    @ResponseStatus(HttpStatus.OK)
    public void createManager
            (@RequestBody Manager manager) {
        managerService.createManager(manager);
    }
}
