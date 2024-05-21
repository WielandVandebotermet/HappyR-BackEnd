package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Survey")
@Builder
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<Long> groupList;

    private String testName;

    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    private Boolean started;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestion> questions;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Result> results;

    public Survey() {
    }

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
