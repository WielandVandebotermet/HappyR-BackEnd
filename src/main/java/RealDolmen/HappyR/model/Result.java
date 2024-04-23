package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
@Entity
@Table(name = "Result")
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Survey survey;

    private int userId;

    private int groupId;

    private int totalResult;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultScoreList> scoreList;

    public Result() {
    }

    public Result(Long id, Survey survey, int userId, int groupId, int totalResult, List<ResultScoreList> scoreList) {
        this.id = id;
        this.survey = survey;
        this.userId = userId;
        this.groupId = groupId;
        this.totalResult = totalResult;
        this.scoreList = scoreList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<ResultScoreList> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ResultScoreList> scoreList) {
        this.scoreList = scoreList;
    }
}
