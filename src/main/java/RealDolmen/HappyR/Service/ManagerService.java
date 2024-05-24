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

/**
 * Service class for managing manager-related operations.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class ManagerService {
    private final ManagerRepository managerRepository;

    /**
     * Creates a new manager associated with the specified team and user.
     *
     * @param team The team the manager belongs to.
     * @param user The user who is the manager.
     */
    public void createManager(Team team, User user){
        // Construct a new manager object based on the provided team and user
        Manager manager = Manager.builder()
                .team(team)
                .user(user)
                .build();

        // Save the new manager to the repository
        managerRepository.save(manager);
    }

    /**
     * Retrieves all managers associated with the specified team ID.
     *
     * @param teamId The ID of the team to retrieve managers for.
     * @return A list of managers associated with the specified team ID.
     */
    public List<Manager> getAllManagersByTeamId(Long teamId) {
        return managerRepository.findByTeamId(teamId);
    }

    /**
     * Retrieves all managers associated with the specified user ID.
     *
     * @param userId The ID of the user to retrieve managers for.
     * @return A list of managers associated with the specified user ID.
     */
    public List<Manager> getAllTeamsByUserId(Long userId) {
        return managerRepository.findByUserId(userId);
    }

    /**
     * Edits an existing manager with the specified ID based on the provided manager details.
     *
     * @param id            The ID of the manager to edit.
     * @param managerRequest The updated manager details.
     */
    public void editManager(int id, Manager managerRequest){
        // Retrieve the existing manager from the repository
        Manager manager = managerRepository.findById((long) id).orElse(null);

        // If the manager exists, update its attributes and save the changes
        if(manager != null)
        {
            manager.setUser(managerRequest.getUser());
            manager.setTeam(managerRequest.getTeam());

            managerRepository.save(manager);
        }
    }

    /**
     * Deletes the manager with the specified ID.
     *
     * @param id The ID of the manager to delete.
     */
    public void deleteManager(int id){
        managerRepository.deleteById((long) id);
    }

    /**
     * Retrieves all managers from the repository.
     *
     * @return A list of all managers.
     */
    public List<Manager> getAllManagers() {
        // Retrieve all managers from the repository
        List<Manager> managers = managerRepository.findAll();

        // Map each manager to a response object and collect them into a list
        return managers.stream().map(this::mapToManagerResponse).toList();
    }

    /**
     * Retrieves the manager with the specified ID.
     *
     * @param id The ID of the manager to retrieve.
     * @return The manager with the specified ID, or null if not found.
     */
    public Manager getManagerById(int id){
        return managerRepository.findById((long) id).orElse(null);
    }

    /**
     * Maps a manager entity to a manager response object.
     *
     * @param manager The manager entity to map.
     * @return A manager response object.
     */
    private Manager mapToManagerResponse(Manager manager) {
        return Manager.builder()
                .id(manager.getId())
                .team(manager.getTeam())
                .user(manager.getUser())
                .build();
    }
}