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

@ExtendWith(MockitoExtension.class)
public class TeamUserTest {

    @InjectMocks
    private TeamUserService teamUserService;

    @Mock
    private TeamUserRepository teamUserRepository;


    @Test
    void testCreateTeamUser() {
        Team team = new Team();
        User user = new User();

        teamUserService.createGroupUser(team, user);

        verify(teamUserRepository, times(1)).save(any(TeamUser.class));
    }

    @Test
    void testEditTeamUser() {
        TeamUser teamUserRequest = new TeamUser();
        teamUserRequest.setUser(new User());
        teamUserRequest.setTeam(new Team());

        TeamUser teamUser = new TeamUser();
        when(teamUserRepository.findById(anyLong())).thenReturn(Optional.of(teamUser));

        teamUserService.editGroupUser(1, teamUserRequest);

        assertEquals(teamUserRequest.getUser(), teamUser.getUser());
        assertEquals(teamUserRequest.getTeam(), teamUser.getTeam());
        verify(teamUserRepository, times(1)).save(any(TeamUser.class));
    }

    @Test
    void testDeleteTeamUser() {
        teamUserService.deleteGroupUser(1);

        verify(teamUserRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllTeamUsers() {
        teamUserService.getAllGroupUsers();

        verify(teamUserRepository, times(1)).findAll();
    }

    @Test
    void testGetTeamUserById() {
        TeamUser teamUser = new TeamUser();
        when(teamUserRepository.findById(anyLong())).thenReturn(Optional.of(teamUser));

        TeamUser result = teamUserService.getGroupUserById(1);

        assertEquals(teamUser, result);
        verify(teamUserRepository, times(1)).findById(anyLong());
    }


}
