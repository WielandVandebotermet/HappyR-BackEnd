package RealDolmen.HappyR.Auth0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RealDolmen.HappyR.Data.UserRequest;
import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// Import statements for required classes and libraries

// Annotations to define the controller and request mappings
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    // Logger for logging information
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Service for user-related operations
    private final UserService userService;

    // Endpoint for creating users
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PermitAll // Annotation to allow access to all users
    public void createUsers(@RequestBody UserRequest userRequest) {
        // Logging the received request
        logger.info("Received request to create user: {}", userRequest);
        // Invoking the service method to create a user
        userService.createUser(userRequest);
    }

    // Endpoint for retrieving a user by authentication ID
    @GetMapping("/Auth/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUserId(@PathVariable("id") String id) {
        // Invoking the service method to retrieve a user by authentication ID
        return userService.getUserByAuthId(id);
    }
}
