    package RealDolmen.HappyR.Service;

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

        public void createTeam(String GroupName, int userId){
            Team team = Team.builder()
                    .GroupName(GroupName)
                    .build();

            teamRepository.save(team);

            User user = userService.getUserById(userId);
            if (user != null) {

                managerService.createManager(Math.toIntExact(team.getId()), Math.toIntExact(user.getId()));
            } else {

            }
        }

        public void editTeam(String GroupName, int id){
            Team team = teamRepository.findById((long) id).orElse(null);

            if(team != null)
            {
                team.setGroupName(GroupName.strip());
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
