package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.ResultScoreList;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ResultRequest {
    private int surveyId;
    private int userId;
    private int totalResult;
    private List<ResultRequest.resultList> scoreList; // Modified type

    public ResultRequest() {
    }

    public ResultRequest(int surveyId, int userId, int totalResult, List<resultList> scoreList) {
        this.surveyId = surveyId;
        this.userId = userId;
        this.totalResult = totalResult;
        this.scoreList = scoreList;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<resultList> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<resultList> scoreList) {
        this.scoreList = scoreList;
    }

    public static class resultList { // Made static
        private int questionId;
        private int categoryId;
        private int score;

        public resultList() {
        }

        public resultList(int questionId, int categoryId, int score) {
            this.questionId = questionId;
            this.categoryId = categoryId;
            this.score = score;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
