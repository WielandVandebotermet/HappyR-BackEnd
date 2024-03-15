package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.User;
import RealDolmen.HappyR.Repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @PostConstruct
    public void LoadData() {
        if (userRepository.count() <= 0) {
            User user = new User();
            user.setId(1L);
            user.setFirstName("Wieland");
            user.setLastName("Vandebotermet");
            userRepository.save(user);

            User user1 = new User();
            user1.setId(2L);
            user1.setFirstName("Hugh");
            user1.setLastName("Hargraves");
            userRepository.save(user1);

            User user2 = new User();
            user2.setId(3L);
            user2.setFirstName("Jeff");
            user2.setLastName("Burrows");
            userRepository.save(user2);

            User user3 = new User();
            user3.setId(4L);
            user3.setFirstName("Tilda");
            user3.setLastName("Miles");
            userRepository.save(user3);
        }
    }

    public void createUser(User userRequest){
        User user = User.builder()
                .FirstName(userRequest.getFirstName())
                .LastName(userRequest.getLastName())
                .build();

        userRepository.save(user);
    }

    public void editUser(int id, User userRequest){
        User user = userRepository.findById((long) id).orElse(null);

        if(user != null)
        {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());

            userRepository.save(user);
        }
    }
    public void deleteUser(int id){
        userRepository.deleteById((long) id);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    public User getUserById(int id){
        return userRepository.findById((long) id).orElse(null);
    }

    private User mapToUserResponse(User user) {
        return User.builder()
                .id(user.getId())
                .FirstName(user.getFirstName())
                .LastName(user.getLastName())
                .build();
    }
}
