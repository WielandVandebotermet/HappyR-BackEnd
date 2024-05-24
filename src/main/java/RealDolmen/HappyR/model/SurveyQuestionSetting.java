package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
/**
 * Model class representing a setting for a survey question.
 */
@Entity // Specifies that this class is an entity
@Builder // Provides a builder pattern for creating instances of the class
public class SurveyQuestionSetting {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the setting

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "survey_question_id") // Specifies the foreign key column name
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private SurveyQuestion surveyQuestion; // The survey question associated with the setting

    private String Question; // The question associated with the setting

    private String Text; // The text of the setting

    /**
     * Default constructor.
     */
    public SurveyQuestionSetting() {
    }

    /**
     * Parameterized constructor to initialize survey question setting data.
     *
     * @param id              The ID of the setting
     * @param surveyQuestion  The survey question associated with the setting
     * @param question        The question associated with the setting
     * @param text            The text of the setting
     */
    public SurveyQuestionSetting(Long id, SurveyQuestion surveyQuestion, String question, String text) {
        this.id = id;
        this.surveyQuestion = surveyQuestion;
        Question = question;
        Text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
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
