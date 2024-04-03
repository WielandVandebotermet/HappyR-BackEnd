package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.ResultScoreList;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ResultRequest {
    private int SurveyId;
    private int UserId;
    private int TotalResult;
    private List<resultList> scoreList;

    public ResultRequest() {
    }

    public ResultRequest(int surveyId, int userId, int totalResult, List<resultList> scoreList) {
        SurveyId = surveyId;
        UserId = userId;
        TotalResult = totalResult;
        this.scoreList = scoreList;
    }

    public int getSurveyId() {
        return SurveyId;
    }

    public void setSurveyId(int surveyId) {
        SurveyId = surveyId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getTotalResult() {
        return TotalResult;
    }

    public void setTotalResult(int totalResult) {
        TotalResult = totalResult;
    }

    public List<resultList> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<resultList> scoreList) {
        this.scoreList = scoreList;
    }

    public class resultList {
        private int QuestionId;
        private int CategoryId;
        private int Score;

        public resultList() {
        }

        public resultList(int questionId, int categoryId, int score) {
            QuestionId = questionId;
            CategoryId = categoryId;
            Score = score;
        }

        public int getQuestionId() {
            return QuestionId;
        }

        public void setQuestionId(int questionId) {
            QuestionId = questionId;
        }

        public int getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(int categoryId) {
            CategoryId = categoryId;
        }

        public int getScore() {
            return Score;
        }

        public void setScore(int score) {
            Score = score;
        }
    }
}
