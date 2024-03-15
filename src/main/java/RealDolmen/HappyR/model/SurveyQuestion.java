package RealDolmen.HappyR.model;

import jakarta.persistence.*;

@Entity
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Survey survey;

    private String Question;
    private String Text;

    public SurveyQuestion() {
    }

    public SurveyQuestion(Long id, Survey survey, String question, String text) {
        this.id = id;
        this.survey = survey;
        Question = question;
        Text = text;
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
