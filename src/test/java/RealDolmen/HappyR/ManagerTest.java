package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.ManagerRepository;
import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.ManagerService;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.TeamUser;
import RealDolmen.HappyR.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManagerTest {

    @InjectMocks
    private ManagerService managerService;
    @InjectMocks
    private TeamService teamService;
    @InjectMocks
    private UserService userService;

    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TeamRepository teamRepository;

    @Test
    void testGetAllManagers() {
        User user2 = new User();
        user2.setId(3L);
        user2.setFirstName("Jeff");
        user2.setLastName("Burrows");
        userRepository.save(user2);

        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("Hugh");
        user1.setLastName("Hargraves");
        userRepository.save(user1);

        Team team = new Team();
        team.setId(1L);
        team.setGroupName("Development");
        teamRepository.save(team);

        Manager manager = new Manager();
        manager.setId(1L);
        manager.setUser(userService.getUserById(2));
        manager.setTeam(teamService.getTeamById(1));
        managerRepository.save(manager);

        Manager manager1 = new Manager();
        manager1.setId(2L);
        manager1.setUser(userService.getUserById(3));
        manager1.setTeam(teamService.getTeamById(1));
        managerRepository.save(manager1);

        List<Manager> managerList = new ArrayList<>();
        managerList.add(manager);
        managerList.add(manager1);


        when(managerRepository.findAll()).thenReturn(managerList);
        List<Manager> managers = managerService.getAllManagers();

        assertEquals(2, managers.size());

        assertEquals(userService.getUserById(2), managers.get(0).getTeam());
        assertEquals(teamService.getTeamById(1), managers.get(0).getUser());

        assertEquals(userService.getUserById(3), managers.get(1).getTeam());
        assertEquals(teamService.getTeamById(1), managers.get(1).getUser());
    }

    @Test
    void testCreateTeamUser() {
        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("Hugh");
        user1.setLastName("Hargraves");
        userRepository.save(user1);

        Team team = new Team();
        team.setId(1L);
        team.setGroupName("Development");
        teamRepository.save(team);

        managerService.createManager(team,user1);

        // Verify that save method was called with the correct arguments
        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    @Test
    void testEditTeamUser() {
        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("Hugh");
        user1.setLastName("Hargraves");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(3L);
        user2.setFirstName("Jeff");
        user2.setLastName("Burrows");
        userRepository.save(user2);

        Team team = new Team();
        team.setId(1L);
        team.setGroupName("Development");
        teamRepository.save(team);

        Manager manager = new Manager();
        manager.setId(1L);
        manager.setUser(userService.getUserById(2));
        manager.setTeam(teamService.getTeamById(1));

        Manager manager1 = new Manager();
        manager1.setId(1L);
        manager1.setUser(userService.getUserById(3));
        manager1.setTeam(teamService.getTeamById(1));

        when(managerRepository.findById(5L)).thenReturn(Optional.of(manager));

        managerService.editManager(5, manager1);

        verify(managerRepository, times(1)).save(manager);

        assertEquals(1L, manager.getId());
        assertEquals(userService.getUserById(2), manager.getTeam());
        assertEquals(teamService.getTeamById(1), manager.getUser());
    }

    @Test
    void testDeleteTemplate() {
        Manager manager = new Manager();
        manager.setId(1L);
        manager.setUser(userService.getUserById(2));
        manager.setTeam(teamService.getTeamById(1));

        managerService.deleteManager(1);

        verify(managerRepository, times(1)).deleteById(1L);
    }
}
