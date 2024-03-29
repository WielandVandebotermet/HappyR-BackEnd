package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class SurveyQuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_question_id")
    @JsonIgnore
    private SurveyQuestion surveyQuestion;

    private String Setting;

    private boolean SettingValue;

    public SurveyQuestionOption() {
    }

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
