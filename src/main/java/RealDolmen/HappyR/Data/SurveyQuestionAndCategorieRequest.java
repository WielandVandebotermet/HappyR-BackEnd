package RealDolmen.HappyR.Data;

import java.util.List;

public class SurveyQuestionAndCategorieRequest {

    private int surveyId;
    private String Question;
    private String Text;
    private String TemplateId;
    private List<SurveyQuestionOptionRequest> options;
    private List<SurveyQuestionSettingRequest> settings;
    private String CategoryName;
    private int ScoreImpact;


    public SurveyQuestionAndCategorieRequest() {
    }

    public SurveyQuestionAndCategorieRequest(int surveyId, String question, String text, String templateId, List<SurveyQuestionOptionRequest> options, List<SurveyQuestionSettingRequest> settings, String categoryName, int scoreImpact) {
        this.surveyId = surveyId;
        Question = question;
        Text = text;
        TemplateId = templateId;
        this.options = options;
        this.settings = settings;
        CategoryName = categoryName;
        ScoreImpact = scoreImpact;
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getScoreImpact() {
        return ScoreImpact;
    }

    public void setScoreImpact(int scoreImpact) {
        ScoreImpact = scoreImpact;
    }
}

