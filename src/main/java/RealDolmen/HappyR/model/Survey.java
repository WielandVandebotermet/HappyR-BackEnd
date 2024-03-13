package RealDolmen.HappyR.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Survey {
    private int id;
    private int[] GroupList;
    private String TestName;
    private Date StartDate;
    private Boolean Started;
    private List<Question> Questions;

    public Survey(int id, int[] groupList, String testName, Date startDate, Boolean started, List<Question> questions) {
        this.id = id;
        GroupList = groupList;
        TestName = testName;
        StartDate = startDate;
        Started = started;
        Questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getGroupList() {
        return GroupList;
    }

    public void setGroupList(int[] groupList) {
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

    public Boolean getStarted() {
        return Started;
    }

    public void setStarted(Boolean started) {
        Started = started;
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
                ", GroupList=" + Arrays.toString(GroupList) +
                ", TestName='" + TestName + '\'' +
                ", StartDate=" + StartDate +
                ", Started=" + Started +
                ", Questions=" + Questions +
                '}';
    }
}
