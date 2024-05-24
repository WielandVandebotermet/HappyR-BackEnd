package RealDolmen.HappyR.Data;
/**
 * Data class representing a setting request for a survey question.
 */
public class SurveyQuestionSettingRequest {
    private String question; // The setting question
    private String text; // The setting text

    /**
     * Default constructor.
     */
    public SurveyQuestionSettingRequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param question The setting question
     * @param text     The setting text
     */
    public SurveyQuestionSettingRequest(String question, String text) {
        this.question = question;
        this.text = text;
    }

    /**
     * Getter for the setting question.
     *
     * @return The setting question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter for the setting question.
     *
     * @param question The setting question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getter for the setting text.
     *
     * @return The setting text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for the setting text.
     *
     * @param text The setting text
     */
    public void setText(String text) {
        this.text = text;
    }
}
