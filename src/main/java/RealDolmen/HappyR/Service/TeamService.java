    package RealDolmen.HappyR.Service;

    import RealDolmen.HappyR.Data.TeamRequest;
    import RealDolmen.HappyR.Repository.TeamRepository;
    import RealDolmen.HappyR.model.Team;
    import RealDolmen.HappyR.model.User;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import java.util.List;


    /**
     * Service class for managing team-related operations.
     */
    @Service
    @RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
    public class TeamService {
        private final TeamRepository teamRepository;
        private final UserService userService;
        private final ManagerService managerService;

        /**
         * Creates a new team based on the provided team request and user ID.
         *
         * @param request The team request object.
         * @param userId  The ID of the user creating the team.
         */
        public void createTeam(TeamRequest request, int userId) {
            Team team = Team.builder()
                    .GroupName(request.getGroupName())
                    .build();

            teamRepository.save(team);

            User user = userService.getUserById(userId);
            if (user != null) {
                managerService.createManager(team, user);
            }
        }

        /**
         * Edits an existing team based on the provided team request and team ID.
         *
         * @param request The updated team request object.
         * @param id      The ID of the team to be edited.
         */
        public void editTeam(TeamRequest request, int id) {
            Team team = teamRepository.findById((long) id).orElse(null);

            if (team != null) {
                team.setGroupName(request.getGroupName());
                teamRepository.save(team);
            }
        }

        /**
         * Deletes a team by its ID.
         *
         * @param id The ID of the team to be deleted.
         */
        public void deleteTeam(int id) {
            teamRepository.deleteById((long) id);
        }

        /**
         * Retrieves all teams.
         *
         * @return A list of all teams.
         */
        public List<Team> getAllTeams() {
            List<Team> teams = teamRepository.findAll();
            return teams.stream().map(this::mapToTeamResponse).toList();
        }

        /**
         * Retrieves all teams associated with a specific user.
         *
         * @param userId The ID of the user.
         * @return A list of teams associated with the specified user.
         */
        public List<Team> getAllTeamsByUserId(int userId) {
            List<Team> teams = teamRepository.findTeamsByUserId((long) userId);
            return teams.stream().map(this::mapToTeamResponse).toList();
        }

        /**
         * Retrieves all teams associated with a specific survey.
         *
         * @param surveyId The ID of the survey.
         * @return A list of teams associated with the specified survey.
         */
        public List<Team> getAllTeamsBySurveyId(int surveyId) {
            List<Team> teams = teamRepository.findAllBySurveyId((long) surveyId);
            return teams.stream().map(this::mapToTeamResponse).toList();
        }

        /**
         * Retrieves a team by its ID.
         *
         * @param id The ID of the team.
         * @return The team object if found, otherwise null.
         */
        public Team getTeamById(int id) {
            return teamRepository.findById((long) id).orElse(null);
        }

        /**
         * Maps a team object to a response object.
         *
         * @param team The team object to be mapped.
         * @return The mapped team response object.
         */
        private Team mapToTeamResponse(Team team) {
            return Team.builder()
                    .id(team.getId())
                    .GroupName(team.getGroupName())
                    .build();
        }
    }
