package RealDolmen.HappyR.model;

import java.util.Dictionary;
import java.util.List;

public class Result {
    private int id;
    private int SurveyId;
    private int UserId;
    private int TotalResult;
    private Dictionary ScoreList;

    public Result(int id, int surveyId, int userId, int totalResult, Dictionary scoreList) {
        this.id = id;
        SurveyId = surveyId;
        UserId = userId;
        TotalResult = totalResult;
        ScoreList = scoreList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Dictionary getScoreList() {
        return ScoreList;
    }

    public void setScoreList(Dictionary scoreList) {
        ScoreList = scoreList;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", SurveyId=" + SurveyId +
                ", UserId=" + UserId +
                ", TotalResult=" + TotalResult +
                ", ScoreList=" + ScoreList +
                '}';
    }
}
