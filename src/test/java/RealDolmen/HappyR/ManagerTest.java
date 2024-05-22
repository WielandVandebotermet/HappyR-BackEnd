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

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerService managerService;

    @Test
    void testCreateManager() {
        Team team = new Team();
        User user = new User();

        managerService.createManager(team, user);

        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    @Test
    void testEditManager() {
        Manager managerRequest = new Manager();
        managerRequest.setUser(new User());
        managerRequest.setTeam(new Team());

        Manager manager = new Manager();
        when(managerRepository.findById(anyLong())).thenReturn(Optional.of(manager));

        managerService.editManager(1, managerRequest);

        assertEquals(managerRequest.getUser(), manager.getUser());
        assertEquals(managerRequest.getTeam(), manager.getTeam());
        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    @Test
    void testDeleteManager() {
        managerService.deleteManager(1);

        verify(managerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllManagers() {
        managerService.getAllManagers();

        verify(managerRepository, times(1)).findAll();
    }

    @Test
    void testGetManagerById() {
        Manager manager = new Manager();
        when(managerRepository.findById(anyLong())).thenReturn(Optional.of(manager));

        Manager result = managerService.getManagerById(1);

        assertEquals(manager, result);
        verify(managerRepository, times(1)).findById(anyLong());
    }
}
