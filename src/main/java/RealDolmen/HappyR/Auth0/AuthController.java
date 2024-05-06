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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PermitAll
    public void createUsers
            (@RequestBody UserRequest userRequest) {
        logger.info("Received request to create user: {}", userRequest);
        userService.createUser(userRequest);
    }

    @GetMapping("/Auth/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUserId(@PathVariable("id") String id) {
        return userService.getUserByAuthId(id);
    }
}
