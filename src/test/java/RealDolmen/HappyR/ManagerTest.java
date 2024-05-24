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
// Import necessary packages and classes

// Annotation to enable Mockito extensions for JUnit 5
@ExtendWith(MockitoExtension.class)
public class ManagerTest {

    // Mocking the ManagerRepository
    @Mock
    private ManagerRepository managerRepository;

    // Injecting mocked ManagerService into test class
    @InjectMocks
    private ManagerService managerService;

    // Test case for creating a manager
    @Test
    void testCreateManager() {
        // Creating test instances of Team and User
        Team team = new Team();
        User user = new User();

        // Invoking the method under test
        managerService.createManager(team, user);

        // Verifying that save method of managerRepository is called once with any Manager object
        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    // Test case for editing a manager
    @Test
    void testEditManager() {
        // Creating test Manager instances
        Manager managerRequest = new Manager();
        managerRequest.setUser(new User());
        managerRequest.setTeam(new Team());

        // Creating a mock Manager object and configuring behavior of managerRepository mock
        Manager manager = new Manager();
        when(managerRepository.findById(anyLong())).thenReturn(Optional.of(manager));

        // Invoking the method under test
        managerService.editManager(1, managerRequest);

        // Verifying that manager's properties are updated and save method of managerRepository is called once with any Manager object
        assertEquals(managerRequest.getUser(), manager.getUser());
        assertEquals(managerRequest.getTeam(), manager.getTeam());
        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    // Test case for deleting a manager
    @Test
    void testDeleteManager() {
        // Invoking the method under test
        managerService.deleteManager(1);

        // Verifying that deleteById method of managerRepository is called once with anyLong argument
        verify(managerRepository, times(1)).deleteById(anyLong());
    }

    // Test case for retrieving all managers
    @Test
    void testGetAllManagers() {
        // Invoking the method under test
        managerService.getAllManagers();

        // Verifying that findAll method of managerRepository is called once
        verify(managerRepository, times(1)).findAll();
    }

    // Test case for retrieving a manager by ID
    @Test
    void testGetManagerById() {
        // Creating a mock Manager object and configuring behavior of managerRepository mock
        Manager manager = new Manager();
        when(managerRepository.findById(anyLong())).thenReturn(Optional.of(manager));

        // Invoking the method under test
        Manager result = managerService.getManagerById(1);

        // Verifying that retrieved manager is equal to the expected one and findById method of managerRepository is called once with anyLong argument
        assertEquals(manager, result);
        verify(managerRepository, times(1)).findById(anyLong());
    }
}