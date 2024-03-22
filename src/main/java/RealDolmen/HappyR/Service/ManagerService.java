package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.ManagerRepository;
import RealDolmen.HappyR.Repository.TeamRepository;
import RealDolmen.HappyR.model.Manager;

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
public class ManagerService {
    private final ManagerRepository managerRepository;

    public void createManager(Team team, User user){
        Manager manager = Manager.builder()
                .team(team)
                .user(user)
                .build();

        managerRepository.save(manager);
    }

    public List<Manager> getAllManagersByTeamId(Long teamId) {
        return managerRepository.findByTeamId(teamId);
    }

    public List<Manager> getAllTeamsByUserId(Long teamId) {
        return managerRepository.findByUserId(teamId);
    }

    public void editManager(int id, Manager managerRequest){
        Manager manager = managerRepository.findById((long) id).orElse(null);

        if(manager != null)
        {
            manager.setId(manager.getId());
            manager.setUser(managerRequest.getUser());
            manager.setTeam(managerRequest.getTeam());

            managerRepository.save(manager);
        }
    }
    public void deleteManager(int id){
        managerRepository.deleteById((long) id);
    }

    public List<Manager> getAllManagers() {
        List<Manager> groupManagers = managerRepository.findAll();

        return groupManagers.stream().map(this::mapToManagerResponse).toList();
    }

    public Manager getManagerById(int id){
        return managerRepository.findById((long) id).orElse(null);
    }

    private Manager mapToManagerResponse(Manager groupManagers) {
        return Manager.builder()
                .id(groupManagers.getId())
                .team(groupManagers.getTeam())
                .user(groupManagers.getUser())
                .build();
    }
}
