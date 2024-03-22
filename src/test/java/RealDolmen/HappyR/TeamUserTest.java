package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.Repository.TeamUserRepository;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.Service.TeamUserService;
import RealDolmen.HappyR.Service.UserService;
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
    @InjectMocks
    private TeamService teamService;
    @InjectMocks
    private UserService userService;

    @Mock
    private TeamUserRepository teamUserRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TeamRepository teamRepository;


    @Test
    void testGetAllTeamUser() {
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

        TeamUser teamUser = new TeamUser();
        teamUser.setId(1L);
        teamUser.setUser(userService.getUserById(2));
        teamUser.setTeam(teamService.getTeamById(1));
        teamUserRepository.save(teamUser);

        TeamUser teamUser1 = new TeamUser();
        teamUser1.setId(2L);
        teamUser1.setUser(userService.getUserById(3));
        teamUser1.setTeam(teamService.getTeamById(1));
        teamUserRepository.save(teamUser1);

        List<TeamUser> teamUserList = new ArrayList<>();
        teamUserList.add(teamUser);
        teamUserList.add(teamUser1);


        when(teamUserRepository.findAll()).thenReturn(teamUserList);
        List<TeamUser> teamUsers = teamUserService.getAllGroupUsers();

        assertEquals(2, teamUsers.size());

        assertEquals(userService.getUserById(2), teamUsers.get(0).getTeam());
        assertEquals(teamService.getTeamById(1), teamUsers.get(0).getUser());

        assertEquals(userService.getUserById(3), teamUsers.get(1).getTeam());
        assertEquals(teamService.getTeamById(1), teamUsers.get(1).getUser());
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


        teamUserService.createGroupUser(team,user1);

        // Verify that save method was called with the correct arguments
        verify(teamUserRepository, times(1)).save(any(TeamUser.class));
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

        TeamUser teamUser = new TeamUser();
        teamUser.setId(1L);
        teamUser.setUser(userService.getUserById(2));
        teamUser.setTeam(teamService.getTeamById(1));

        TeamUser teamUser1 = new TeamUser();
        teamUser1.setId(1L);
        teamUser1.setUser(userService.getUserById(3));
        teamUser1.setTeam(teamService.getTeamById(1));

        when(teamUserRepository.findById(5L)).thenReturn(Optional.of(teamUser));

        teamUserService.editGroupUser(5, teamUser1);

        verify(teamUserRepository, times(1)).save(teamUser);

        assertEquals(1L, teamUser1.getId());
        assertEquals(userService.getUserById(2), teamUser.getTeam());
        assertEquals(teamService.getTeamById(1), teamUser.getUser());
    }

    @Test
    void testDeleteTemplate() {
        TeamUser teamUser = new TeamUser();
        teamUser.setId(1L);
        teamUser.setUser(userService.getUserById(2));
        teamUser.setTeam(teamService.getTeamById(1));

        teamUserService.deleteGroupUser(1);

        verify(teamUserRepository, times(1)).deleteById(1L);
    }
}
