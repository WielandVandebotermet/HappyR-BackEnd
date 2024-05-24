package RealDolmen.HappyR.Data;

import java.util.List;

public class SurveyQuestionAndCategorieRequest {

    private int surveyId; // The ID of the survey
    private String Question; // The question text
    private String Text; // The question subtext
    private String TemplateId; // The ID of the template
    private List<SurveyQuestionOptionRequest> options; // The options for the question
    private List<SurveyQuestionSettingRequest> settings; // The settings for the question
    private String CategoryName; // The name of the category
    private int ScoreImpact; // The impact score of the category

    /**
     * Default constructor.
     */
    public SurveyQuestionAndCategorieRequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param surveyId     The ID of the survey
     * @param question     The question text
     * @param text         The question subtext
     * @param templateId   The ID of the template
     * @param options      The options for the question
     * @param settings     The settings for the question
     * @param categoryName The name of the category
     * @param scoreImpact  The impact score of the category
     */
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

