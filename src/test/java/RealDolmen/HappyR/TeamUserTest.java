package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.Repository.TeamUserRepository;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.TeamUserService;
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
import static org.mockito.Mockito.times;
// Import statements for required classes and libraries

// Annotations to indicate the use of Mockito for testing
@ExtendWith(MockitoExtension.class)
public class TeamUserTest {

    // Injecting service under test and mocking repository
    @InjectMocks
    private TeamUserService teamUserService;

    @Mock
    private TeamUserRepository teamUserRepository;

    // Testing method for creating a team user
    @Test
    void testCreateTeamUser() {
        // Creating sample team and user objects
        Team team = new Team();
        User user = new User();

        // Invoking the method under test
        teamUserService.createGroupUser(team, user);

        // Verifying that the save method is called on the team user repository
        verify(teamUserRepository, times(1)).save(any(TeamUser.class));
    }

    // Testing method for editing a team user
    @Test
    void testEditTeamUser() {
        // Creating sample team user request and existing team user
        TeamUser teamUserRequest = new TeamUser();
        teamUserRequest.setUser(new User());
        teamUserRequest.setTeam(new Team());

        TeamUser teamUser = new TeamUser();

        // Mocking behavior of team user repository
        when(teamUserRepository.findById(anyLong())).thenReturn(Optional.of(teamUser));

        // Invoking the method under test
        teamUserService.editGroupUser(1, teamUserRequest);

        // Verifying that the save method is called on the team user repository and the team user attributes are updated correctly
        assertEquals(teamUserRequest.getUser(), teamUser.getUser());
        assertEquals(teamUserRequest.getTeam(), teamUser.getTeam());
        verify(teamUserRepository, times(1)).save(any(TeamUser.class));
    }

    // Testing method for deleting a team user
    @Test
    void testDeleteTeamUser() {
        // Invoking the method under test
        teamUserService.deleteGroupUser(1);

        // Verifying that the deleteById method is called on the team user repository
        verify(teamUserRepository, times(1)).deleteById(anyLong());
    }

    // Testing method for getting all team users
    @Test
    void testGetAllTeamUsers() {
        // Invoking the method under test
        teamUserService.getAllGroupUsers();

        // Verifying that the findAll method is called on the team user repository
        verify(teamUserRepository, times(1)).findAll();
    }

    // Testing method for getting a team user by ID
    @Test
    void testGetTeamUserById() {
        // Creating a sample team user
        TeamUser teamUser = new TeamUser();

        // Mocking behavior of team user repository
        when(teamUserRepository.findById(anyLong())).thenReturn(Optional.of(teamUser));

        // Invoking the method under test
        TeamUser result = teamUserService.getGroupUserById(1);

        // Verifying that the retrieved team user matches the expected one
        assertEquals(teamUser, result);
        // Verifying that the findById method is called on the team user repository
        verify(teamUserRepository, times(1)).findById(anyLong());
    }
}
