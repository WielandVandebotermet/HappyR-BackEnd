package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
/**
 * Model class representing an option for a survey question.
 */
@Entity // Specifies that this class is an entity
@Builder // Provides a builder pattern for creating instances of the class
public class SurveyQuestionOption {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the option

    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "survey_question_id") // Specifies the foreign key column name
    @JsonIgnore // Ignores this property during JSON serialization/deserialization
    private SurveyQuestion surveyQuestion; // The survey question associated with the option

    private String Setting; // The setting associated with the option

    private boolean SettingValue; // The value of the setting

    /**
     * Default constructor.
     */
    public SurveyQuestionOption() {
    }

    /**
     * Parameterized constructor to initialize survey question option data.
     *
     * @param id              The ID of the option
     * @param surveyQuestion  The survey question associated with the option
     * @param setting         The setting associated with the option
     * @param settingValue    The value of the setting
     */
    public SurveyQuestionOption(Long id, SurveyQuestion surveyQuestion, String setting, boolean settingValue) {
        this.id = id;
        this.surveyQuestion = surveyQuestion;
        Setting = setting;
        SettingValue = settingValue;
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

    public String getSetting() {
        return Setting;
    }

    public void setSetting(String setting) {
        Setting = setting;
    }

    public boolean isSettingValue() {
        return SettingValue;
    }

    public void setSettingValue(boolean settingValue) {
        SettingValue = settingValue;
    }
}
