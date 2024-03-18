package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.ManagerRepository;
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
public class ManagerService {
    private final ManagerRepository managerRepository;

    @PostConstruct
    public void LoadData() {
        if (managerRepository.count() <= 0) {
            User user = new User();
            user.setId(1L);
            user.setFirstName("Wieland");
            user.setLastName("Vandebotermet");

            Team team = new Team();
            team.setId(1L);
            team.setGroupName("Development");

            Manager manager = new Manager();
            manager.setId(1L);
            manager.setUser(user);
            manager.setTeam(team);
            managerRepository.save(manager);
        }
    }

    public void createManager(Manager ManagerRequest){
        Manager manager = ManagerRequest.builder()
                .team(ManagerRequest.getTeam())
                .user(ManagerRequest.getUser())
                .build();

        managerRepository.save(manager);
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
