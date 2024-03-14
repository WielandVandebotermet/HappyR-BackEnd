package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.Repository.GroupRepository;
import RealDolmen.HappyR.model.Group;
import RealDolmen.HappyR.model.Group;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    @PostConstruct
    public void LoadData() {
        if (groupRepository.count() <= 0) {
            Group group = new Group();
            group.setId(1L);
            group.setGroupName("Development");
            groupRepository.save(group);

            Group group1 = new Group();
            group1.setId(2L);
            group1.setGroupName("Operations");
            groupRepository.save(group1);

            Group group2 = new Group();
            group2.setId(3L);
            group2.setGroupName("Quality Control");
            groupRepository.save(group2);
        }
    }

    public void createGroup(Group groupRequest){
        Group group = Group.builder()
                .GroupName(groupRequest.getGroupName())
                .build();

        groupRepository.save(group);
    }

    public void editGroup(int id, Group groupRequest){
        Group group = groupRepository.findById((long) id).orElse(null);

        if(group != null)
        {
            group.setId(group.getId());
            group.setGroupName(groupRequest.getGroupName());

            groupRepository.save(group);
        }
    }
    public void deleteGroup(int id){
        groupRepository.deleteById((long) id);
    }

    public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();

        return groups.stream().map(this::mapToGroupResponse).toList();
    }

    public Group getGroupById(int id){
        return groupRepository.findById((long) id).orElse(null);
    }

    private Group mapToGroupResponse(Group group) {
        return Group.builder()
                .id(group.getId())
                .GroupName(group.getGroupName())
                .build();
    }

}
