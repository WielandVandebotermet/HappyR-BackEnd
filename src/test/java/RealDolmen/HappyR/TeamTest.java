package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.Service.TeamService;
import RealDolmen.HappyR.model.Team;
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
public class TeamTest {
    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Test
    void testGetAllTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setGroupName("Development");
        teamRepository.save(team);

        Team team1 = new Team();
        team1.setId(2L);
        team1.setGroupName("Operations");
        teamRepository.save(team1);

        Team team2 = new Team();
        team2.setId(3L);
        team2.setGroupName("Quality Control");
        teamRepository.save(team2);

        List<Team> teamList = new ArrayList<>();
        teamList.add(team);
        teamList.add(team1);
        teamList.add(team2);

        when(teamRepository.findAll()).thenReturn(teamList);
        List<Team> teams = teamService.getAllTeams();


        assertEquals(3, teams.size());

        assertEquals("Development", teams.get(0).getGroupName());

        assertEquals("Operations", teams.get(1).getGroupName());

        assertEquals("Quality Control", teams.get(2).getGroupName());
    }

    @Test
    void testCreateTeam() {
        Team team = new Team();
        team.setGroupName("Development");

        teamService.createTeam(team);

        // Verify that save method was called with the correct arguments
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void testEditTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setGroupName("Development");

        Team team1 = new Team();
        team1.setId(1L);
        team1.setGroupName("Operations");


        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        teamService.editTeam(1, team1);

        verify(teamRepository, times(1)).save(team);

        assertEquals(1L, team1.getId());
        assertEquals("Operations", team1.getGroupName());
    }

    @Test
    void testDeleteTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setGroupName("Operations");

        teamService.deleteTeam(1);

        verify(teamRepository, times(1)).deleteById(1L);
    }
}
