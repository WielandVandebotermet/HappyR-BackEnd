package RealDolmen.HappyR.Controller;

import RealDolmen.HappyR.Service.UserService;
import RealDolmen.HappyR.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HappyRController {

    @GetMapping("/users")
    public List<User> getAllUsers(UserService userService) {
        return userService.getUserList();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getResultById(@PathVariable("id") int userId, UserService userService) {
        Optional<User> user = userService.getOptionalUserById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User newUser, UserService userService){
        return userService.addUser(newUser);
    }

    @PutMapping("user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User updateUser, @PathVariable("id") int userId, UserService userService){
        User user = userService.updateUserById(updateUser, userId);
        if (user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("id") int userId, UserService userService){
        Optional<User> user = userService.getOptionalUserById(userId);
        if (user.isPresent()){
            userService.getUserList().remove(user.get());
            return new ResponseEntity<>(userService.getUserList().size(), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.getUserList().size(), HttpStatus.NOT_FOUND);
    }
}