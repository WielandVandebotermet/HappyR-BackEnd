package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.User;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList= new ArrayList<>();

    public UserService() {
        userList.add(new User(1, "Wieland", "Vandebotermet"));
        userList.add(new User(2, "Hugh", "Hargraves"));
        userList.add(new User(3, "Jeff", "Burrows"));
        userList.add(new User(4, "Tilda", "Miles"));
        userList.add(new User(5, "Salena", "Becker"));
        userList.add(new User(6, "Christine", "Kemp"));
        userList.add(new User(7, "Stewart", "Abbott"));
        userList.add(new User(8, "Harrison", "Knight"));
        userList.add(new User(9, "Wade", "Allen"));
        userList.add(new User(10, "Todd", "Carter"));
    }
}
