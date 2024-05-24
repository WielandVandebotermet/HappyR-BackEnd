package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.SurveyQuestion;
import RealDolmen.HappyR.model.SurveyQuestionOption;
import RealDolmen.HappyR.model.SurveyQuestionSetting;

import java.util.List;

/**
 * Data class representing a request to create or edit a survey question.
 */
public class SurveyQuestionRequest {
    private int surveyId; // The ID of the survey associated with the question
    private String Question; // The question text
    private String Text; // Additional text (if any) related to the question
    private String TemplateId; // The ID of the template associated with the question
    private List<SurveyQuestionOptionRequest> options; // The options (if any) associated with the question
    private List<SurveyQuestionSettingRequest> settings; // The settings (if any) associated with the question

    /**
     * Default constructor.
     */
    public SurveyQuestionRequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param surveyId   The ID of the survey associated with the question
     * @param question   The question text
     * @param text       Additional text related to the question
     * @param templateId The ID of the template associated with the question
     * @param options    The options associated with the question
     * @param settings   The settings associated with the question
     */
    public SurveyQuestionRequest(int surveyId, String question, String text, String templateId, List<SurveyQuestionOptionRequest> options, List<SurveyQuestionSettingRequest> settings) {
        this.surveyId = surveyId;
        Question = question;
        Text = text;
        TemplateId = templateId;
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

