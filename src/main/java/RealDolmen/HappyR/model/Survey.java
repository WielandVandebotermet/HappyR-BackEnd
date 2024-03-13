package RealDolmen.HappyR.model;

import java.util.Date;
import java.util.List;

public class Survey {
    private int id;
    private List<Group> GroupList;
    private String TestName;
    private Date StartDate;
    private List<Question> Questions;

    public Survey(int id, List<Group> groupList, String testName, Date startDate, List<Question> questions) {
        this.id = id;
        GroupList = groupList;
        TestName = testName;
        StartDate = startDate;
        Questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Group> getGroupList() {
        return GroupList;
    }

    public void setGroupList(List<Group> groupList) {
        GroupList = groupList;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        Questions = questions;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", Groupid=" + GroupList +
                ", TestName='" + TestName + '\'' +
                ", StartDate=" + StartDate +
                ", BuildList=" + Questions +
                '}';
    }
}
