package RealDolmen.HappyR.model;

import org.apache.catalina.User;

public class Manager {
    private int id;
    private int Userid;
    private int Groupid;

    public Manager(int id, int userid, int groupid) {
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
        return "Manager{" +
                "id=" + id +
                ", Userid=" + Userid +
                ", Groupid=" + Groupid +
                '}';
    }
}
