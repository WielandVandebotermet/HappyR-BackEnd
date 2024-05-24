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
// Import statements for required classes and libraries

// Annotations to indicate the use of Mockito for testing
@ExtendWith(MockitoExtension.class)
public class UserTest {

    // Injecting service under test and mocking repository
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    // Testing method for creating a user (successful case)
    @Test
    void testCreateUser_Success() {
        // Creating a sample user request
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId("test_user_id");
        userRequest.setEmail("test@example.com");
        userRequest.setFullName("Test User");
        userRequest.setFirstName("Test");
        userRequest.setLastName("User");
        userRequest.setProfileImage("profile.jpg");

        // Mocking behavior of user repository
        when(userRepository.findUserByAuthId(any())).thenReturn(Optional.empty());

        // Invoking the method under test
        userService.createUser(userRequest);

        // Verifying that the save method is called on the user repository
        verify(userRepository, times(1)).save(any(User.class));
    }

    // Testing method for editing a user (successful case)
    @Test
    void testEditUser_Success() {
        // Creating a sample user request
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("updated@example.com");
        userRequest.setFullName("Updated User");
        userRequest.setFirstName("Updated");
        userRequest.setLastName("User");
        userRequest.setProfileImage("updated_profile.jpg");

        // Creating a sample existing user
        User existingUser = new User();
        existingUser.setId(1L);

        // Mocking behavior of user repository
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));

        // Invoking the method under test
        userService.editUser(1, userRequest);

        // Verifying that the user attributes are updated correctly and the save method is called on the user repository
        assertEquals("updated@example.com", existingUser.getEmail());
        assertEquals("Updated User", existingUser.getFullName());
        assertEquals("Updated", existingUser.getFirstName());
        assertEquals("updated_profile.jpg", existingUser.getProfileImage());
        verify(userRepository, times(1)).save(any(User.class));
    }

    // Testing method for retrieving a user by ID (successful case)
    @Test
    void testGetUserById_Success() {
        // Creating a sample user
        User user = new User();
        user.setId(1L);
        user.setAuthId("test_user_id");
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setProfileImage("profile.jpg");

        // Mocking behavior of user repository
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Invoking the method under test
        User retrievedUser = userService.getUserById(1);

        // Verifying that the retrieved user matches the expected one
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getAuthId(), retrievedUser.getAuthId());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getFullName(), retrievedUser.getFullName());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getProfileImage(), retrievedUser.getProfileImage());
    }
    // Testing method for retrieving all users (successful case)
    @Test
    void testGetAllUsers_Success() {
        // Creating a sample user
        User user = new User();
        user.setId(1L);
        user.setAuthId("test_user_id");
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setProfileImage("profile.jpg");

        // Mocking behavior of user repository
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // Invoking the method under test
        List<User> users = userService.getAllUsers();

        // Verifying that the retrieved list of users contains the expected user
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

    // Testing method for retrieving a user by authentication ID (successful case)
    @Test
    void testGetUserByAuthId_Success() {
        // Creating a sample user
        User user = new User();
        user.setId(1L);
        user.setAuthId("test_user_id");
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setProfileImage("profile.jpg");

        // Mocking behavior of user repository
        when(userRepository.findUserByAuthId(anyString())).thenReturn(Optional.of(user));

        // Invoking the method under test
        User retrievedUser = userService.getUserByAuthId("test_user_id");

        // Verifying that the retrieved user matches the expected one
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getAuthId(), retrievedUser.getAuthId());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getFullName(), retrievedUser.getFullName());
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
        assertEquals(user.getProfileImage(), retrievedUser.getProfileImage());
    }
}
