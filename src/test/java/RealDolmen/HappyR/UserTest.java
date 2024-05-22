package RealDolmen.HappyR;

import RealDolmen.HappyR.Data.UserRequest;
import RealDolmen.HappyR.Repository.UserRepository;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
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
    void testCreateUser_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId("test_user_id");
        userRequest.setEmail("test@example.com");
        userRequest.setFullName("Test User");
        userRequest.setFirstName("Test");
        userRequest.setLastName("User");
        userRequest.setProfileImage("profile.jpg");

        when(userRepository.findUserByAuthId(any())).thenReturn(Optional.empty());

        userService.createUser(userRequest);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testEditUser_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("updated@example.com");
        userRequest.setFullName("Updated User");
        userRequest.setFirstName("Updated");
        userRequest.setLastName("User");
        userRequest.setProfileImage("updated_profile.jpg");

        User existingUser = new User();
        existingUser.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));

        userService.editUser(1, userRequest);

        assertEquals("updated@example.com", existingUser.getEmail());
        assertEquals("Updated User", existingUser.getFullName());
        assertEquals("Updated", existingUser.getFirstName());
        assertEquals("updated_profile.jpg", existingUser.getProfileImage());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById_Success() {
        User user = new User();
        user.setId(1L);
        user.setAuthId("test_user_id");
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setProfileImage("profile.jpg");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(1);

        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getAuthId(), retrievedUser.getAuthId());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getFullName(), retrievedUser.getFullName());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getProfileImage(), retrievedUser.getProfileImage());
    }

    @Test
    void testGetAllUsers_Success() {
        User user = new User();
        user.setId(1L);
        user.setAuthId("test_user_id");
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setProfileImage("profile.jpg");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        User retrievedUser = users.getFirst();
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getAuthId(), retrievedUser.getAuthId());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getFullName(), retrievedUser.getFullName());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getProfileImage(), retrievedUser.getProfileImage());
    }

    @Test
    void testGetUserByAuthId_Success() {
        User user = new User();
        user.setId(1L);
        user.setAuthId("test_user_id");
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setProfileImage("profile.jpg");

        when(userRepository.findUserByAuthId(anyString())).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserByAuthId("test_user_id");

        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getAuthId(), retrievedUser.getAuthId());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getFullName(), retrievedUser.getFullName());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getProfileImage(), retrievedUser.getProfileImage());
    }

}
