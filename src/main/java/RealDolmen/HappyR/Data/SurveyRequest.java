package RealDolmen.HappyR.Data;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class SurveyRequest {
    private String testName;
    private Calendar startDate;
    private List<String> questions;
    private List<Long> groupList;
    private boolean started;

    public SurveyRequest() {
    }

    public SurveyRequest(String testName, Calendar startDate, List<String> questions, List<Long> groupList, boolean started) {
        this.testName = testName;
        this.startDate = startDate;
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

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
