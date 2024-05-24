package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.TeamRequest;

import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.Service.ManagerService;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Import statements for required classes and libraries

// Annotations to indicate the use of Mockito for testing
@ExtendWith(MockitoExtension.class)
public class TeamTest {
    // Mocking repositories and injecting services for testing
    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserService userService;

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private TeamService teamService;

    // Testing method for creating a team (successful case)
    @Test
    void testCreateTeam_Success() {
        // Creating a sample team request and user ID
        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setGroupName("Test Team");
        int userId = 1;

        // Creating a sample user
        User user = new User();
        user.setId((long) userId);

        // Mocking behavior of user service
        when(userService.getUserById(anyInt())).thenReturn(user);

        // Invoking the method under test
        teamService.createTeam(teamRequest, userId);

        // Verifying that the save method is called on the team repository and createManager method is called on the manager service
        verify(teamRepository, times(1)).save(any(Team.class));
        verify(managerService, times(1)).createManager(any(Team.class), any(User.class));
    }

    // Testing method for editing a team (successful case)
    @Test
    void testEditTeam_Success() {
        // Creating a sample team request and team ID
        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setGroupName("Updated Team Name");
        int teamId = 1;

        // Creating a sample existing team
        Team existingTeam = new Team();
        existingTeam.setId((long) teamId);

        // Mocking behavior of team repository
        when(teamRepository.findById(anyLong())).thenReturn(Optional.of(existingTeam));

        // Invoking the method under test
        teamService.editTeam(teamRequest, teamId);

        // Verifying that the save method is called on the team repository and the group name is updated correctly
        assertEquals("Updated Team Name", existingTeam.getGroupName());
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    // Testing method for deleting a team (successful case)
    @Test
    void testDeleteTeam_Success() {
        // Creating a sample team ID
        int teamId = 1;

        // Invoking the method under test
        teamService.deleteTeam(teamId);

        // Verifying that the deleteById method is called on the team repository
        verify(teamRepository, times(1)).deleteById(anyLong());
    }
}
