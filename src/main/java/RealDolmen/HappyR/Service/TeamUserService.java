package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.TeamUserRepository;
import RealDolmen.HappyR.model.TeamUser;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamUserService {
    private final TeamUserRepository teamUserRepository;
    @Autowired
    private  UserService userService;
    @Autowired
    private TeamService teamService;

    @PostConstruct
    public void LoadData() {
        if (teamUserRepository.count() <= 0) {
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
        }
    }

    public void createGroupUser(TeamUser teamUserRequest){
        TeamUser teamUser = TeamUser.builder()
                .team(teamUserRequest.getTeam())
                .user(teamUserRequest.getUser())
                .build();

        teamUserRepository.save(teamUser);
    }

    public void editGroupUser(int id, TeamUser teamUserRequest){
        TeamUser teamUser = teamUserRepository.findById((long) id).orElse(null);

        if(teamUser != null)
        {
            teamUser.setId(teamUser.getId());
            teamUser.setUser(teamUserRequest.getUser());
            teamUser.setTeam(teamUserRequest.getTeam());

            teamUserRepository.save(teamUser);
        }
    }
    public void deleteGroupUser(int id){
        teamUserRepository.deleteById((long) id);
    }

    public List<TeamUser> getAllGroupUsers() {
        List<TeamUser> teamUsers = teamUserRepository.findAll();

        return teamUsers.stream().map(this::mapToGroupGroupUserResponse).toList();
    }

    public TeamUser getGroupUserById(int id){
        return teamUserRepository.findById((long) id).orElse(null);
    }

    private TeamUser mapToGroupGroupUserResponse(TeamUser teamUsers) {
        return TeamUser.builder()
                .id(teamUsers.getId())
                .team(teamUsers.getTeam())
                .user(teamUsers.getUser())
                .build();
    }


}
