package RealDolmen.HappyR;

import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testGetAllUser() {
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

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userRepository.findAll()).thenReturn(userList);
        List<User> users = userService.getAllUsers();


        assertEquals(4, users.size());

        assertEquals("Wieland", users.get(0).getFirstName());
        assertEquals("Vandebotermet", users.get(0).getLastName());

        assertEquals("Hugh", users.get(1).getFirstName());
        assertEquals("Hargraves", users.get(1).getLastName());


        assertEquals("Jeff", users.get(2).getFirstName());
        assertEquals("Burrows", users.get(2).getLastName());


        assertEquals("Tilda", users.get(3).getFirstName());
        assertEquals("Miles", users.get(3).getLastName());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");

        userService.createUser(user);

        // Verify that save method was called with the correct arguments
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testEditUser() {
        User user = new User();
        user.setId(5L);
        user.setFirstName("Wieland");
        user.setLastName("Vandebotermet");

        User user1 = new User();
        user1.setId(5L);
        user1.setFirstName("Alex");
        user1.setLastName("Jones");

        when(userRepository.findById(5L)).thenReturn(Optional.of(user));

        userService.editUser(5, user1);

        verify(userRepository, times(1)).save(user);

        assertEquals(5L, user1.getId());
        assertEquals("Alex", user1.getFirstName());
        assertEquals("Jones", user1.getLastName());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Wieland");
        user.setLastName("Vandebotermet");

        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
