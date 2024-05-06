    package RealDolmen.HappyR.Service;

    import RealDolmen.HappyR.Data.TeamRequest;
    import RealDolmen.HappyR.Repository.TeamRepository;
    import RealDolmen.HappyR.model.Team;
    import RealDolmen.HappyR.model.User;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import java.util.List;


    @Service
    @RequiredArgsConstructor
    public class TeamService {
        private final TeamRepository teamRepository;
        private final UserService userService;
        private final ManagerService managerService;

        public void createTeam(TeamRequest request, int userId){
            Team team = Team.builder()
                    .GroupName(request.getGroupName())
                    .build();

            teamRepository.save(team);

            User user = userService.getUserById(userId);
            if (user != null) {
                managerService.createManager(team, user);
            }
        }

        public void editTeam(TeamRequest request, int id){
            Team team = teamRepository.findById((long) id).orElse(null);

            if(team != null)
            {
                team.setGroupName(request.getGroupName());
                teamRepository.save(team);
            }
        }
        public void deleteTeam(int id){
            teamRepository.deleteById((long) id);
        }

        public List<Team> getAllTeams() {
            List<Team> teams = teamRepository.findAll();

            return teams.stream().map(this::mapToTeamResponse).toList();
        }

        public List<Team> getAllTeamsByUserId(int userId) {
            List<Team> teams = teamRepository.findTeamsByUserId((long) userId);

            return teams.stream().map(this::mapToTeamResponse).toList();
        }

        public List<Team> getAllTeamsBySurveyId(int surveyId) {
            List<Team> teams = teamRepository.findAllBySurveyId((long) surveyId);

            return teams.stream().map(this::mapToTeamResponse).toList();
        }

        public Team getTeamById(int id){
            return teamRepository.findById((long) id).orElse(null);
        }

        private Team mapToTeamResponse(Team team) {
            return Team.builder()
                    .id(team.getId())
                    .GroupName(team.getGroupName())
                    .build();
        }

    }
