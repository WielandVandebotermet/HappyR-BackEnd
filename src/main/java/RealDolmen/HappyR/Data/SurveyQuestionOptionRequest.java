package RealDolmen.HappyR.Data;
/**
 * Data class representing an option for a survey question.
 */
public class SurveyQuestionOptionRequest {
    private String setting; // The setting name
    private boolean settingValue; // The setting value

    /**
     * Default constructor.
     */
    public SurveyQuestionOptionRequest() {
    }

    /**
     * Parameterized constructor.
     *
     * @param setting      The setting name
     * @param settingValue The setting value
     */
    public SurveyQuestionOptionRequest(String setting, boolean settingValue) {
        this.setting = setting;
        this.settingValue = settingValue;
    }

    /**
     * Getter for the setting name.
     *
     * @return The setting name
     */
    public String getSetting() {
        return setting;
    }

    /**
     * Setter for the setting name.
     *
     * @param setting The setting name to set
     */
    public void setSetting(String setting) {
        this.setting = setting;
    }

    /**
     * Getter for the setting value.
     *
     * @return The setting value
     */
    public boolean isSettingValue() {
        return settingValue;
    }

    /**
     * Setter for the setting value.
     *
     * @param settingValue The setting value to set
     */
    public void setSettingValue(boolean settingValue) {
        this.settingValue = settingValue;
    }
}
