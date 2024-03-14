package RealDolmen.HappyR.model;

import jakarta.persistence.*;

@Entity
public class SurveyReoccuring {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Survey survey;

    private int Time;

    private String TimeMultiplier;

    public SurveyReoccuring() {
    }

    public SurveyReoccuring(Long id, Survey survey, int time, String timeMultiplier) {
        this.id = id;
        this.survey = survey;
        Time = time;
        TimeMultiplier = timeMultiplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public String getTimeMultiplier() {
        return TimeMultiplier;
    }

    public void setTimeMultiplier(String timeMultiplier) {
        TimeMultiplier = timeMultiplier;
    }
}
