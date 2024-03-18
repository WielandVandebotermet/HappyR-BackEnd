package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/User")
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

    @DeleteMapping("/Delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteKoers
            (@PathVariable("id") String id) {
        userService.deleteUser(Integer.parseInt(id));
    }

    @PutMapping("/Edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void EditKoers
            (@PathVariable("id") String id, @RequestBody User user) {
        userService.editUser(Integer.parseInt(id), user);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createKoers
            (@RequestBody User user) {
        userService.createUser(user);
    }
}
