package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.UserRequest;
import RealDolmen.HappyR.model.User;
import RealDolmen.HappyR.Repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to users.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments for the final fields
public class UserService {
    private final UserRepository userRepository;

    /**
     * Creates a new user if the user with the given authentication ID doesn't already exist.
     *
     * @param userRequest The user object to be created.
     */
    public void createUser(UserRequest userRequest) {
        User userTest = userRepository.findUserByAuthId(userRequest.getUserId()).orElse(null);

        String userId = userRequest.getUserId();
        if (userId == null || userTest == null) {
            User user = User.builder()
                    .authId(userId)
                    .email(userRequest.getEmail())
                    .FullName(userRequest.getFullName())
                    .FirstName(userRequest.getFirstName())
                    .LastName(userRequest.getLastName())
                    .profileImage(userRequest.getProfileImage())
                    .build();

            userRepository.save(user);
        }
    }

    /**
     * Edits an existing user.
     *
     * @param id           The ID of the user to be edited.
     * @param userRequest  The updated user object.
     */
    public void editUser(int id, UserRequest userRequest) {
        User user = userRepository.findById((long) id).orElse(null);

        if (user != null) {
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setProfileImage(userRequest.getProfileImage());

            userRepository.save(user);
        }
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id The ID of the user.
     * @return The user object if found, otherwise null.
     */
    public User getUserById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    /**
     * Retrieves a user by their authentication ID.
     *
     * @param id The authentication ID of the user.
     * @return The user object if found, otherwise null.
     */
    public User getUserByAuthId(String id) {
        return userRepository.findUserByAuthId(id).orElse(null);
    }

    /**
     * Maps a user object to a response object.
     *
     * @param user The user object to be mapped.
     * @return The mapped user response object.
     */
    private User mapToUserResponse(User user) {
        return User.builder()
                .id(user.getId())
                .authId(user.getAuthId())
                .email(user.getEmail())
                .FirstName(user.getFirstName())
                .LastName(user.getLastName())
                .FullName(user.getFullName())
                .profileImage(user.getProfileImage())
                .build();
    }
}