package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.DataLoader;
import RealDolmen.HappyR.Repository.TeamUserRepository;
import RealDolmen.HappyR.model.Team;
import RealDolmen.HappyR.model.TeamUser;
import RealDolmen.HappyR.model.User;
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
    private DataLoader dataLoader;

    @PostConstruct
    public void loadData() {
        dataLoader.loadData();
    }


    public void createGroupUser(Team team, User user){
        TeamUser teamUser = TeamUser.builder()
                .team(team)
                .user(user)
                .build();

        teamUserRepository.save(teamUser);
    }

    public List<TeamUser> getAllGroupUsersByTeamId(Long teamId) {
        return teamUserRepository.findByTeamId(teamId);
    }

    public List<TeamUser> getAllTeamsByUserId(Long teamId) {
        return teamUserRepository.findByUserId(teamId);
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
