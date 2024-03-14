package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.GroupUserRepository;
import RealDolmen.HappyR.model.GroupUser;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupUserService {
    private final GroupUserRepository groupUserRepository;
    private  UserService userService;
    private  GroupService groupService;

    @PostConstruct
    public void LoadData() {
        if (groupUserRepository.count() <= 0) {
            GroupUser groupUser = new GroupUser();
            groupUser.setId(1L);
            groupUser.setUser(userService.getUserById(2));
            groupUser.setGroup(groupService.getGroupById(1));
            groupUserRepository.save(groupUser);

            GroupUser groupUser1 = new GroupUser();
            groupUser1.setId(2L);
            groupUser1.setUser(userService.getUserById(3));
            groupUser1.setGroup(groupService.getGroupById(1));
            groupUserRepository.save(groupUser1);
        }
    }

    public void createGroupUser(GroupUser GroupUserRequest){
        GroupUser groupUser = GroupUser.builder()
                .group(GroupUserRequest.getGroup())
                .user(GroupUserRequest.getUser())
                .build();

        groupUserRepository.save(groupUser);
    }

    public void editGroupUser(int id, GroupUser GroupUserRequest){
        GroupUser groupUser = groupUserRepository.findById((long) id).orElse(null);

        if(groupUser != null)
        {
            groupUser.setId(groupUser.getId());
            groupUser.setUser(GroupUserRequest.getUser());
            groupUser.setGroup(GroupUserRequest.getGroup());

            groupUserRepository.save(groupUser);
        }
    }
    public void deleteGroupUser(int id){
        groupUserRepository.deleteById((long) id);
    }

    public List<GroupUser> getAllGroupUsers() {
        List<GroupUser> groupUsers = groupUserRepository.findAll();

        return groupUsers.stream().map(this::mapToGroupGroupUserResponse).toList();
    }

    public GroupUser getGroupUserById(int id){
        return groupUserRepository.findById((long) id).orElse(null);
    }

    private GroupUser mapToGroupGroupUserResponse(GroupUser groupUsers) {
        return GroupUser.builder()
                .id(groupUsers.getId())
                .group(groupUsers.getGroup())
                .user(groupUsers.getUser())
                .build();
    }


}
