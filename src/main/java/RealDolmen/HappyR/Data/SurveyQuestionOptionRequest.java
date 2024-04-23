package RealDolmen.HappyR.Data;

public class SurveyQuestionOptionRequest {
        private String setting;
        private boolean settingValue;

        public SurveyQuestionOptionRequest() {
        }

        public SurveyQuestionOptionRequest(String setting, boolean settingValue) {
            this.setting = setting;
            this.settingValue = settingValue;
        }

        public String getSetting() {
            return setting;
        }

        public void setSetting(String setting) {
            this.setting = setting;
        }

        public boolean isSettingValue() {
            return settingValue;
        }

        public void setSettingValue(boolean settingValue) {
            this.settingValue = settingValue;
        }
    }
