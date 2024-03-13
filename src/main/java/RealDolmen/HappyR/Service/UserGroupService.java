package RealDolmen.HappyR.Service;

import RealDolmen.HappyR.model.GroupUser;
import RealDolmen.HappyR.model.Manager;
import RealDolmen.HappyR.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserGroupService {
    private List<GroupUser> guList= new ArrayList<>();
    private List<Manager> MList= new ArrayList<>();

    public UserGroupService() {
        guList.add(new GroupUser(1, 2, 1));
        guList.add(new GroupUser(2, 3, 1));
        MList.add(new Manager(3, 1, 1));
    }

    public List<GroupUser> getGroupUserList() {
        return guList;
    }

    public void setGroupUserList(List<GroupUser> groupUserList) {
        this.guList = groupUserList;
    }

    public Optional<GroupUser> getOptionalGroupUserById(int groupUserId){
        return getGroupUserList().stream().filter(gu-> gu.getId()==groupUserId).findFirst();
    }
    public GroupUser getGroupUserById(Optional<GroupUser> optionalGroupUser){
        return optionalGroupUser.orElse(null);
    }

    public GroupUser addGroupUser(GroupUser newGroupUser) {
        newGroupUser.setId(guList.size()+1);
        guList.add(newGroupUser);
        return guList.get(guList.size()-1);
    }

    public GroupUser updateUserById(GroupUser updateGroupUser, int groupUserId) {
        Optional<GroupUser> groupUserOptional = getOptionalGroupUserById(groupUserId);
        if (groupUserOptional.isPresent()){
            GroupUser groupUser = groupUserOptional.get();
            groupUser.setGroupid(updateGroupUser.getGroupid());
            groupUser.setUserid(updateGroupUser.getUserid());
            return groupUser;
        }
        return null;
    }

    public List<Manager> getManagerList() {
        return MList;
    }

    public void setManagerList(List<Manager> MList) {
        this.MList = MList;
    }

    public Optional<Manager> getOptionalManagerById(int managerId){
        return getManagerList().stream().filter(u-> u.getId()==managerId).findFirst();
    }
    public Manager getManagerById(Optional<Manager> optionalManager){
        return optionalManager.orElse(null);
    }

    public Manager addManager(Manager newManager) {
        newManager.setId(MList.size()+1);
        MList.add(newManager);
        return MList.get(MList.size()-1);
    }

    public Manager updateUserById(Manager managerUser, int managerId) {
        Optional<Manager> managerOptional = getOptionalManagerById(managerId);
        if (managerOptional.isPresent()){
            Manager manager = managerOptional.get();
            manager.setGroupid(managerUser.getGroupid());
            manager.setUserid(managerUser.getUserid());
            return manager;
        }
        return null;
    }

}
