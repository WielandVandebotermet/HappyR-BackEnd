package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * Model class representing a survey.
 */
@Entity // Specifies that this class is an entity
@Table(name = "Survey") // Specifies the table name in the database
@Builder // Provides a builder pattern for creating instances of the class
public class Survey {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the survey

    @ElementCollection // Specifies a collection of basic types or embeddable objects
    private List<Long> groupList; // List of group IDs associated with the survey

    private String testName; // Name of the survey

    @Temporal(TemporalType.DATE) // Specifies the type for date and/or time data
    private Calendar startDate; // Start date of the survey

    private Boolean started; // Indicates if the survey has started

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestion> questions; // List of survey questions associated with the survey

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private List<Result> results; // List of results associated with the survey

    /**
     * Default constructor.
     */
    public Survey() {
    }

    /**
     * Parameterized constructor to initialize survey data.
     *
     * @param id          The ID of the survey
     * @param groupList   The list of group IDs associated with the survey
     * @param testName    The name of the survey
     * @param startDate   The start date of the survey
     * @param started     Indicates if the survey has started
     * @param questions   The list of survey questions associated with the survey
     * @param results     The list of results associated with the survey
     */
    public Survey(Long id, List<Long> groupList, String testName, Calendar startDate, Boolean started, List<SurveyQuestion> questions, List<Result> results) {
        this.id = id;
        this.groupList = groupList;
        this.testName = testName;
        this.startDate = startDate;
        this.started = started;
        this.questions = questions;
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Long> groupList) {
        this.groupList = groupList;
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

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public List<Result> getResults() {
        return results;
    }
}
