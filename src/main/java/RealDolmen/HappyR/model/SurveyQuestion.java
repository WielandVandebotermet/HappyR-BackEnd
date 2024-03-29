package RealDolmen.HappyR.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    private Survey survey;

    @OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestionOption> options;

    @OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestionSetting> settings;

    private String Question;

    private String TemplateId;

    private String Text;

    public SurveyQuestion() {
    }

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
