    package RealDolmen.HappyR.Service;

    import RealDolmen.HappyR.Repository.ManagerRepository;
    import RealDolmen.HappyR.Repository.TeamRepository;
    import RealDolmen.HappyR.Repository.UserRepository;
    import RealDolmen.HappyR.model.Manager;
    import RealDolmen.HappyR.model.Team;
    import RealDolmen.HappyR.model.User;
    import jakarta.annotation.PostConstruct;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.util.List;


    @Service
    @RequiredArgsConstructor
    public class TeamService {
        private final TeamRepository teamRepository;
        private final UserRepository userRepository;
        private final ManagerService managerService;
        private final ManagerRepository managerRepository;


        @PostConstruct
        public void LoadData() {
            if (teamRepository.count() <= 0) {
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
            }
        }

        public void createTeam(Team teamRequest,User user){
            Team team = Team.builder()
                    .GroupName(teamRequest.getGroupName())
                    .build();

            teamRepository.save(team);

            if (user != null) {
                Manager manager = new Manager();
                manager.setUser(user);
                manager.setTeam(team);
                managerService.createManager(manager);
            } else {

            }
        }

        public void editTeam(int id, Team teamRequest){
            Team team = teamRepository.findById((long) id).orElse(null);

            if(team != null)
            {
                team.setId(team.getId());
                team.setGroupName(teamRequest.getGroupName());

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
