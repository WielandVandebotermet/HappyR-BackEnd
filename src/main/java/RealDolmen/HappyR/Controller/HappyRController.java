package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HappyRController {

    @GetMapping("/users")
    public List<User> getAllUsers(UserService userService) {
        return userService.getUserList();
    }
}