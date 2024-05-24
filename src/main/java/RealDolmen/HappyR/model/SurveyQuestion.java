package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * Model class representing a survey question.
 */
@Setter // Lombok annotation to generate setter methods
@Getter // Lombok annotation to generate getter methods
@Entity // Specifies that this class is an entity
@Builder // Provides a builder pattern for creating instances of the class
public class SurveyQuestion {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the survey question

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "survey_id") // Specifies the foreign key column name
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private Survey survey; // The survey associated with the question

    @OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestionOption> options; // List of options associated with the question

    @OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestionSetting> settings; // List of settings associated with the question

    private String Question; // The question text

    private String TemplateId; // The template ID associated with the question

    private String Text; // Additional text related to the question

    /**
     * Default constructor.
     */
    public SurveyQuestion() {
    }

    /**
     * Parameterized constructor to initialize survey question data.
     *
     * @param id         The ID of the survey question
     * @param survey     The survey associated with the question
     * @param options    The list of options associated with the question
     * @param settings   The list of settings associated with the question
     * @param question   The question text
     * @param templateId The template ID associated with the question
     * @param text       Additional text related to the question
     */
    public SurveyQuestion(Long id, Survey survey, List<SurveyQuestionOption> options, List<SurveyQuestionSetting> settings, String question, String templateId, String text) {
        this.id = id;
        this.survey = survey;
        this.options = options;
        this.settings = settings;
        Question = question;
        TemplateId = templateId;
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

    public List<SurveyQuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<SurveyQuestionOption> options) {
        this.options = options;
    }

    public List<SurveyQuestionSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<SurveyQuestionSetting> settings) {
        this.settings = settings;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getTemplateId() {
        return TemplateId;
    }

    public void setTemplateId(String templateId) {
        TemplateId = templateId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
