package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
@Entity
@Table(name = "Result")
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int SurveyId;
    private int UserId;
    private int TotalResult;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultScoreList> scoreList;

    public Result() {
    }

    public Result(Long id, int surveyId, int userId, int totalResult, List<ResultScoreList> scoreList) {
        this.id = id;
        SurveyId = surveyId;
        UserId = userId;
        TotalResult = totalResult;
        this.scoreList = scoreList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<ResultScoreList> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ResultScoreList> scoreList) {
        this.scoreList = scoreList;
    }
}
