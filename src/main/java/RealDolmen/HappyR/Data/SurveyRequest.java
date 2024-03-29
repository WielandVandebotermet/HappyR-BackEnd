package RealDolmen.HappyR.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SurveyRequest {
    private String testName;
    private Date startDate;
    private Map<String, Object> reoccuring;
    private List<String> questions;
    private List<Long> groupList;
    private boolean started;

    public SurveyRequest() {
    }

    public SurveyRequest(String testName, Date startDate, Map<String, Object> reoccuring, List<String> questions, List<Long> groupList, boolean started) {
        this.testName = testName;
        this.startDate = startDate;
        this.reoccuring = reoccuring;
        this.questions = questions;
        this.groupList = groupList;
        this.started = started;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Map<String, Object> getReoccuring() {
        return reoccuring;
    }

    public void setReoccuring(Map<String, Object> reoccuring) {
        this.reoccuring = reoccuring;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<Long> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Long> groupList) {
        this.groupList = groupList;
    }

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
