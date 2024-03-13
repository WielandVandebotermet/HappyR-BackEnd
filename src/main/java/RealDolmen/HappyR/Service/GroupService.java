package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupService {
    private List<Group> groupList= new ArrayList<>();

    public GroupService() {
        groupList.add(new Group(1, "Development"));
        groupList.add(new Group(2, "Operations"));
        groupList.add(new Group(3, "Quality Control"));
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public Optional<Group> getOptionalUserById(int groupId){
        return getGroupList().stream().filter(g-> g.getId()==groupId).findFirst();
    }
    public Group getGroupById(Optional<Group> optionalGroup){
        return optionalGroup.orElse(null);
    }

    public Group addGroup(Group newGroup) {
        newGroup.setId(groupList.size()+1);
        groupList.add(newGroup);
        return groupList.get(groupList.size()-1);
    }

    public Group updateUserById(Group updateGroup, int groupId) {
        Optional<Group> productOptional = getOptionalUserById(groupId);
        if (productOptional.isPresent()){
            Group group = productOptional.get();
            group.setGroupName(updateGroup.getGroupName());
            return group;
        }
        return null;
    }
}
