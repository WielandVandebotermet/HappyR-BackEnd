package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.SurveyQuestion;
import RealDolmen.HappyR.model.SurveyQuestionOption;
import RealDolmen.HappyR.model.SurveyQuestionSetting;

import java.util.List;

public class SurveyQuestionRequest {

    private int surveyId;
    private String Question;
    private String TemplateId;
    private String Text;
    private List<SurveyQuestionOptionRequest> options;
    private List<SurveyQuestionSettingRequest> settings;

    public SurveyQuestionRequest() {
    }

    public SurveyQuestionRequest(int surveyId, String question, String templateId, String text, List<SurveyQuestionOptionRequest> options, List<SurveyQuestionSettingRequest> settings) {
        this.surveyId = surveyId;
        Question = question;
        TemplateId = templateId;
        Text = text;
        this.options = options;
        this.settings = settings;
    }


    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
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

    public List<SurveyQuestionOptionRequest> getOptions() {
        return options;
    }

    public void setOptions(List<SurveyQuestionOptionRequest> options) {
        this.options = options;
    }

    public List<SurveyQuestionSettingRequest> getSettings() {
        return settings;
    }

    public void setSettings(List<SurveyQuestionSettingRequest> settings) {
        this.settings = settings;
    }
}

