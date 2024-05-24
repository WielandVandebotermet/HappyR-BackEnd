package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Data.UserRequest;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// Controller for managing user-related endpoints
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService; // Service for handling user-related operations

    // Endpoint to retrieve all users
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint to retrieve a user by their ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(Integer.parseInt(id));
    }

    // Endpoint to edit a user by their ID
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editUser(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
        userService.editUser(Integer.parseInt(id), userRequest);
    }
}
