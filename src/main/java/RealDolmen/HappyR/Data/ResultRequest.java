package RealDolmen.HappyR.Data;

import RealDolmen.HappyR.model.ResultScoreList;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Data class representing a result request.
 */
public class ResultRequest {
    private int surveyId; // The ID of the survey
    private int userId; // The ID of the user
    private int groupId; // The ID of the group
    private int totalResult; // The total result
    private List<resultList> scoreList; // The list of result scores

    /**
     * Default constructor.
     */
    public ResultRequest() {
    }

    /**
     * Parameterized constructor to initialize result request data.
     *
     * @param surveyId    The ID of the survey
     * @param userId      The ID of the user
     * @param totalResult The total result
     * @param scoreList   The list of result scores
     */
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    /**
     * Static inner class representing a result list.
     */
    public static class resultList {
        private int questionId; // The ID of the question
        private int categoryId; // The ID of the category
        private int score; // The score

        /**
         * Default constructor.
         */
        public resultList() {
        }

        /**
         * Parameterized constructor to initialize result list data.
         *
         * @param questionId The ID of the question
         * @param categoryId The ID of the category
         * @param score      The score
         */
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
