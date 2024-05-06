package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Data.UserRequest;
import RealDolmen.HappyR.model.User;
import RealDolmen.HappyR.Repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void createUser(UserRequest userRequest){
        User userTest = userRepository.findUserByAuthId(userRequest.getUserId()).orElse(null);
        System.out.println("Received UserRequest: " + userRequest);
        System.out.println("Received getFullName: " + userRequest.getFullName());
        System.out.println("Received getLastName: " + userRequest.getLastName());
        System.out.println("Received getLastName: " + userRequest.getLastName());
        System.out.println("Received getEmail: " + userRequest.getEmail());
        System.out.println("Received getUserId: " + userRequest.getUserId());
        System.out.println("Received getProfileImage: " + userRequest.getProfileImage());

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

    public void editUser(int id, UserRequest userRequest){
        User user = userRepository.findById((long) id).orElse(null);

        if(user != null)
        {
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setProfileImage(userRequest.getProfileImage());

            userRepository.save(user);
        }
    }

    public User getUserById(int id){
        return userRepository.findById((long) id).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    public User getUserByAuthId(String id){
        return userRepository.findUserByAuthId(id).orElse(null);
    }


    private User mapToUserResponse(User user) {
        return User.builder()
                .id(user.getId())
                .authId(user.getAuthId())
                .email(user.getEmail())
                .FirstName(user.getFirstName())
                .LastName(user.getLastName())
                .profileImage(user.getProfileImage())
                .build();
    }
}
