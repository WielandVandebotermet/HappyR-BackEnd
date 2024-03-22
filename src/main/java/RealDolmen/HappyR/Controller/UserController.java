package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(Integer.parseInt(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser
            (@PathVariable("id") String id) {
        userService.deleteUser(Integer.parseInt(id));
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editUser
            (@PathVariable("id") String id, @RequestBody User user) {
        userService.editUser(Integer.parseInt(id), user);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser
            (@RequestBody User user) {
        userService.createUser(user);
    }
}
