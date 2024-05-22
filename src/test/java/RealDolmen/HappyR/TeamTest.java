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


@ExtendWith(MockitoExtension.class)
public class TeamTest {
    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserService userService;

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private TeamService teamService;

    @Test
    void testCreateTeam_Success() {
        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setGroupName("Test Team");
        int userId = 1;

        User user = new User();
        user.setId((long) userId);

        when(userService.getUserById(anyInt())).thenReturn(user);

        teamService.createTeam(teamRequest, userId);

        verify(teamRepository, times(1)).save(any(Team.class));
        verify(managerService, times(1)).createManager(any(Team.class), any(User.class));
    }

    @Test
    void testEditTeam_Success() {
        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setGroupName("Updated Team Name");
        int teamId = 1;

        Team existingTeam = new Team();
        existingTeam.setId((long) teamId);

        when(teamRepository.findById(anyLong())).thenReturn(Optional.of(existingTeam));

        teamService.editTeam(teamRequest, teamId);

        assertEquals("Updated Team Name", existingTeam.getGroupName());
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void testDeleteTeam_Success() {
        int teamId = 1;

        teamService.deleteTeam(teamId);

        verify(teamRepository, times(1)).deleteById(anyLong());
    }
}
