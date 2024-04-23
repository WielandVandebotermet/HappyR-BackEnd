package RealDolmen.HappyR.Data;

public class SurveyQuestionSettingRequest {
        private String question;
        private String text;

        public SurveyQuestionSettingRequest() {
        }

        public SurveyQuestionSettingRequest(String question, String text) {
            this.question = question;
            this.text = text;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }
