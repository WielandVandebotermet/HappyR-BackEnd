package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.ManagerRepository;
import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.Service.GroupService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private UserService userService;
    private GroupService groupService;

    @PostConstruct
    public void LoadData() {
        if (managerRepository.count() <= 0) {
            Manager manager = new Manager();
            manager.setId(1L);
            manager.setUser(userService.getUserById(1));
            manager.setGroup(groupService.getGroupById(1));
            managerRepository.save(manager);
        }
    }

    public void createManager(Manager ManagerRequest){
        Manager manager = ManagerRequest.builder()
                .group(ManagerRequest.getGroup())
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
            manager.setGroup(managerRequest.getGroup());

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
                .group(groupManagers.getGroup())
                .user(groupManagers.getUser())
                .build();
    }
}
