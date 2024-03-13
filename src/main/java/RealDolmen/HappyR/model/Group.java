package RealDolmen.HappyR.model;

public class Group {
    private int id;
    private String GroupName;

    public Group(int id, String groupName) {
        this.id = id;
        GroupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", GroupName='" + GroupName + '\'' +
                '}';
    }
}
