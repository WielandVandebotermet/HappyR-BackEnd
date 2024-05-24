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

/**
 * Service class for managing operations related to team users.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class TeamUserService {
    private final TeamUserRepository teamUserRepository;

    @Autowired
    private DataLoader dataLoader;

    /**
     * Loads data using the DataLoader after the bean is constructed.
     */
    @PostConstruct
    public void loadData() {
        dataLoader.loadData();
    }

    /**
     * Creates a new team user.
     *
     * @param team The team to which the user belongs.
     * @param user The user to be associated with the team.
     */
    public void createGroupUser(Team team, User user) {
        TeamUser teamUser = TeamUser.builder()
                .team(team)
                .user(user)
                .build();

        teamUserRepository.save(teamUser);
    }

    /**
     * Retrieves all team users associated with a specific team.
     *
     * @param teamId The ID of the team.
     * @return A list of team users associated with the specified team.
     */
    public List<TeamUser> getAllGroupUsersByTeamId(Long teamId) {
        return teamUserRepository.findByTeamId(teamId);
    }

    /**
     * Retrieves all teams associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of teams associated with the specified user.
     */
    public List<TeamUser> getAllTeamsByUserId(Long userId) {
        return teamUserRepository.findByUserId(userId);
    }

    /**
     * Edits an existing team user.
     *
     * @param id            The ID of the team user to be edited.
     * @param teamUserRequest The updated team user object.
     */
    public void editGroupUser(int id, TeamUser teamUserRequest) {
        TeamUser teamUser = teamUserRepository.findById((long) id).orElse(null);

        if (teamUser != null) {
            teamUser.setId(teamUser.getId());
            teamUser.setUser(teamUserRequest.getUser());
            teamUser.setTeam(teamUserRequest.getTeam());

            teamUserRepository.save(teamUser);
        }
    }

    /**
     * Deletes a team user by its ID.
     *
     * @param id The ID of the team user to be deleted.
     */
    public void deleteGroupUser(int id) {
        teamUserRepository.deleteById((long) id);
    }

    /**
     * Retrieves all team users.
     *
     * @return A list of all team users.
     */
    public List<TeamUser> getAllGroupUsers() {
        List<TeamUser> teamUsers = teamUserRepository.findAll();
        return teamUsers.stream().map(this::mapToGroupGroupUserResponse).toList();
    }

    /**
     * Retrieves a team user by its ID.
     *
     * @param id The ID of the team user.
     * @return The team user object if found, otherwise null.
     */
    public TeamUser getGroupUserById(int id) {
        return teamUserRepository.findById((long) id).orElse(null);
    }

    /**
     * Maps a team user object to a response object.
     *
     * @param teamUser The team user object to be mapped.
     * @return The mapped team user response object.
     */
    private TeamUser mapToGroupGroupUserResponse(TeamUser teamUser) {
        return TeamUser.builder()
                .id(teamUser.getId())
                .team(teamUser.getTeam())
                .user(teamUser.getUser())
                .build();
    }
}
