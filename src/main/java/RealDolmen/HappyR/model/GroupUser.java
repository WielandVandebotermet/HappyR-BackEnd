package RealDolmen.HappyR.model;

public class GroupUser {
    private int id;
    private int Userid;
    private int Groupid;

    public GroupUser(int id, int userid, int groupid) {
        this.id = id;
        Userid = userid;
        Groupid = groupid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public int getGroupid() {
        return Groupid;
    }

    public void setGroupid(int groupid) {
        Groupid = groupid;
    }

    @Override
    public String toString() {
        return "GroupUser{" +
                "id=" + id +
                ", Userid=" + Userid +
                ", Groupid=" + Groupid +
                '}';
    }
}
