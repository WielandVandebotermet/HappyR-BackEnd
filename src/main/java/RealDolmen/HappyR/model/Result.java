package RealDolmen.HappyR.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
/**
 * Model class representing a result.
 */
@Builder // Lombok annotation to generate builder methods
@Entity // Specifies that this class is an entity
@Table(name = "Result") // Specifies the table name in the database
public class Result {
    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies the generation strategy for the primary key
    private Long id; // ID of the result

    @ManyToOne // Specifies a many-to-one relationship
    private Survey survey; // Survey associated with the result

    private int userId; // ID of the user associated with the result

    private int groupId; // ID of the group associated with the result

    private int totalResult; // Total result of the survey

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true) // Specifies a one-to-many relationship
    private List<ResultScoreList> scoreList; // List of score lists associated with the result

    /**
     * Default constructor.
     */
    public Result() {
    }

    /**
     * Parameterized constructor to initialize result data.
     *
     * @param id          The ID of the result
     * @param survey      The survey associated with the result
     * @param userId      The ID of the user associated with the result
     * @param groupId     The ID of the group associated with the result
     * @param totalResult The total result of the survey
     * @param scoreList   The list of score lists associated with the result
     */
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
