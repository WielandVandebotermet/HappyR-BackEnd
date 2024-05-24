package RealDolmen.HappyR.Data;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Data class representing a survey request.
 */
public class SurveyRequest {
    private String testName; // The name of the survey
    private Calendar startDate; // The start date of the survey
    private List<String> questions; // List of survey questions
    private List<Long> groupList; // List of group IDs
    private boolean started; // Indicates if the survey has started

    /**
     * Default constructor.
     */
    public SurveyRequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param testName   The name of the survey
     * @param startDate  The start date of the survey
     * @param questions  List of survey questions
     * @param groupList  List of group IDs
     * @param started    Indicates if the survey has started
     */
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
